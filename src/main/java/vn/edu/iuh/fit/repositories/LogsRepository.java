package vn.edu.iuh.fit.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogsRepository {

  private final Connection connection;

  public LogsRepository() throws SQLException, ClassNotFoundException {
    connection = ConnectionDB.getInstance().getConnection();
  }

  public void setLogTime(String account_id, String notes) {
    String sql = "INSERT INTO `log`(account_id,notes) VALUE(?,?)";
    PreparedStatement ps = null;
    try {
      ps = connection.prepareStatement(sql);
      ps.setString(1, account_id);
      ps.setString(2, notes);
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

  public void setLogoutTime(String account_id) {

    String sql = "UPDATE log SET logout_time = CURRENT_TIMESTAMP() WHERE account_id = ?";
    PreparedStatement ps = null;
    try {
      ps = connection.prepareStatement(sql);
      ps.setString(1, account_id);
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }
}
