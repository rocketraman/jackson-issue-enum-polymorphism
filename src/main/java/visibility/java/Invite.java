package visibility.java;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

class Invite {
  private InviteKind kind;
  // workaround for https://github.com/FasterXML/jackson-databind/issues/999 (should be fixed in 2.8.x)
  private String kindForMapper = null;

  @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "kindForMapper", visible = false)
  @JsonSubTypes(value = {
    @JsonSubTypes.Type(InviteToContact.class),
    @JsonSubTypes.Type(InviteToUser.class)
  })
  private InviteTo to;

  public InviteKind getKind() {
    return kind;
  }
  public void setKind(final InviteKind kind) {
    this.kind = kind;
  }
  public String getKindForMapper() {
    return kindForMapper;
  }
  public void setKindForMapper(final String kindForMapper) {
    this.kindForMapper = kindForMapper;
  }
  public InviteTo getTo() {
    return to;
  }
  public void setTo(final InviteTo to) {
    this.to = to;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Invite invite = (Invite) o;

    if (kind != invite.kind) return false;
    if (kindForMapper != null ? !kindForMapper.equals(invite.kindForMapper) : invite.kindForMapper != null)
      return false;
    return to != null ? to.equals(invite.to) : invite.to == null;

  }
  @Override
  public int hashCode() {
    int result = kind != null ? kind.hashCode() : 0;
    result = 31 * result + (kindForMapper != null ? kindForMapper.hashCode() : 0);
    result = 31 * result + (to != null ? to.hashCode() : 0);
    return result;
  }
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Invite{");
    sb.append("kind=").append(kind);
    sb.append(", kindForMapper='").append(kindForMapper).append('\'');
    sb.append(", to=").append(to);
    sb.append('}');
    return sb.toString();
  }
}
