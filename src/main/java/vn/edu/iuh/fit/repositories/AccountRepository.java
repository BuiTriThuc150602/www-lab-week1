package vn.edu.iuh.fit.repositories;

import java.util.ArrayList;
import java.util.List;
import vn.edu.iuh.fit.models.Account;

import java.sql.*;

public class AccountRepository {

  private final Connection connection;

  public AccountRepository() throws Exception {
    connection = ConnectionDB.getInstance().getConnection();
  }

  public boolean insert_account(Account acc) throws SQLException {
    String sql = "INSERT INTO account VALUES (?,?,?,?,?,?)";
    int rs;
    PreparedStatement ps = connection.prepareStatement(sql);
    ps.setNString(1, acc.getAccount_id());
    ps.setNString(2, acc.getFull_name());
    ps.setNString(3, acc.getPassword());
    ps.setNString(4, acc.getEmail());
    ps.setNString(5, acc.getPhone());
    ps.setInt(6, acc.getStatus());
    rs = ps.executeUpdate();
    return rs > 0;
  }

  public List<Account> getAllAccount() {
    String sql = "select * from account";
    List<Account> list = new ArrayList<>();
    try {
      PreparedStatement ps = connection.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        String id = rs.getString(1);
        String name = rs.getString(2);
        String pass = rs.getString(3);
        String email = rs.getString(4);
        String phone = rs.getString(5);
        int status = rs.getInt(6);
        Account acc = new Account(id, name, pass, email, phone, status);
        list.add(acc);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return list;
  }


}
