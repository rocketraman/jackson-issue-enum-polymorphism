package visibility

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.*

data class Invite(
  val kind: InviteKind,
  // workaround for https://github.com/FasterXML/jackson-databind/issues/999 (should be fixed in 2.8.x)
  val kindForMapper: String? = null,
  @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "kindForMapper", visible = false)
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
       |  "kindForMapper": "CONTACT",
       |  "to": {
       |    "name": "Foo"
       |  }
       |}""".trimMargin()
  )

  // this should be Invite(kind=CONTACT, kindForMapper=null, to=InviteToContact(name=Foo))
  // but it will be Invite(kind=CONTACT, kindForMapper=CONTACT, to=InviteToContact(name=Foo))

  println(invite)
}