package enumtype.java;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("CONTACT")
class InviteToContact implements InviteTo {
  private String name = null;

  public String getName() {
    return name;
  }
  public void setName(final String name) {
    this.name = name;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    InviteToContact that = (InviteToContact) o;

    return name != null ? name.equals(that.name) : that.name == null;

  }
  @Override
  public int hashCode() {
    return name != null ? name.hashCode() : 0;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("InviteToContact{");
    sb.append("name='").append(name).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
