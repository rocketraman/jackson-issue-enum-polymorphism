package enumtype.java;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

class ReproductionKindEnum {
  public static void main(String[] args) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    Invite invite = mapper.readValue("{ \"kind\": \"CONTACT\", \"to\": { \"name\": \"Foo\" } }", Invite.class);

    // unlike with Kotlin, this works
    System.out.println(invite);
  }
}