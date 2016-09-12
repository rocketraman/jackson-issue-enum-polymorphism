package visibility.java;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("USER")
class InviteToUser implements InviteTo {
  private String user = null;

  public String getUser() {
    return user;
  }
  public void setUser(final String user) {
    this.user = user;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    InviteToUser that = (InviteToUser) o;

    return user != null ? user.equals(that.user) : that.user == null;

  }
  @Override
  public int hashCode() {
    return user != null ? user.hashCode() : 0;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("InviteToUser{");
    sb.append("user='").append(user).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
