package enumtype

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue

data class Invite(
  val kind: InviteKind,
  @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "kind", visible = true)
  @JsonSubTypes(
    JsonSubTypes.Type(InviteToContact::class),
    JsonSubTypes.Type(InviteToUser::class)
  )
  val to: InviteTo
)

interface InviteTo

@JsonTypeName("CONTACT")
data class InviteToContact(
  val name: String? = null
): InviteTo

@JsonTypeName("USER")
data class InviteToUser(
  val user: String
): InviteTo

enum class InviteKind {
  CONTACT,
  USER
}

fun main(args: Array<String>) {
  val mapper = ObjectMapper().registerModule(KotlinModule())
  val invite = mapper.readValue<Invite>(
    """|{
       |  "kind": "CONTACT",
       |  "to": {
       |    "name": "Foo"
       |  }
       |}""".trimMargin()
  )
  // throws exception com.fasterxml.jackson.databind.JsonMappingException: Can not construct instance of enum.Invite, problem: argument type mismatch
  println(invite)
}