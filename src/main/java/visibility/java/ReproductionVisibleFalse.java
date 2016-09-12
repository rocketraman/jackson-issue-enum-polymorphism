package visibility.java;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

class ReproductionVisibleFalse {
  public static void main(String[] args) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    Invite invite = mapper.readValue("{ \"kind\": \"CONTACT\", \"kindForMapper\": \"CONTACT\", \"to\": { \"name\": \"Foo\" } }", Invite.class);

    // this should be Invite(kind=CONTACT, kindForMapper=null, to=InviteToContact(name=Foo))
    // but it will be Invite(kind=CONTACT, kindForMapper=CONTACT, to=InviteToContact(name=Foo))
    System.out.println(invite);
  }
}