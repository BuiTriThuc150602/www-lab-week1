package vn.edu.iuh.fit.models;

public class GrantAccess {
  private  String role_id;
  private  String account_id;

  private  int is_grant;
  private  String note;

  public GrantAccess(String role_id, String account_id, int is_grant, String note) {
    this.role_id = role_id;
    this.account_id = account_id;
    this.is_grant = is_grant;
    this.note = note;
  }

  public String getRole_id() {
    return role_id;
  }

  public void setRole_id(String role_id) {
    this.role_id = role_id;
  }

  public String getAccount_id() {
    return account_id;
  }

  public void setAccount_id(String account_id) {
    this.account_id = account_id;
  }

  public int getIs_grant() {
    return is_grant;
  }

  public void setIs_grant(int is_grant) {
    this.is_grant = is_grant;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  @Override
  public String toString() {
    return "GrantAccess{" +
        "role_id='" + role_id + '\'' +
        ", account_id='" + account_id + '\'' +
        ", is_grant=" + is_grant +
        ", note='" + note + '\'' +
        '}';
  }
}
