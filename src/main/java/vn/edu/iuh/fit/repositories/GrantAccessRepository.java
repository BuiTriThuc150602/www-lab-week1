package vn.edu.iuh.fit.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vn.edu.iuh.fit.models.GrantAccess;

public class GrantAccessRepository {

  private final Connection connection;

  public GrantAccessRepository() throws SQLException, ClassNotFoundException {
    connection = ConnectionDB.getInstance().getConnection();
  }

  public GrantAccess grantAccess(String account_id) throws SQLException {
    GrantAccess grantAccess = null;
    String sql = "Select * from grant_access where account_id = ?";
    PreparedStatement ps = connection.prepareStatement(sql);
    ps.setString(1, account_id);
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
      String role = rs.getString(1);
      String acc = rs.getString(2);
      int is_grant = rs.getInt(3);
      String note = rs.getString(4);
      grantAccess = new GrantAccess(role, acc, is_grant, note);

    }
    return grantAccess;
  }

  public boolean setRoleAccount(String account_id, String role_id) {
    String sqlInsert = "INSERT INTO grant_access (role_id,account_id) VALUE(?,?)";
    String sqlUpdate = "UPDATE grant_access SET role_id = ? WHERE account_id = ?";
    System.out.println(role_id);
    System.out.println(account_id);
    try {
      PreparedStatement ps;
      if(grantAccess(account_id)!= null){
        ps = connection.prepareStatement(sqlUpdate);
        ps.setString(1, role_id);
        ps.setString(2, account_id);
      }else{
        ps= connection.prepareStatement(sqlInsert);
        ps.setString(1, role_id);
        ps.setString(2, account_id);
      }
      return ps.executeUpdate() > 0;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}
