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

  //delete
  public boolean delete_account(String id) throws SQLException {
    String sql = "DELETE FROM account WHERE account_id = ?";
    int rs;
    PreparedStatement ps = connection.prepareStatement(sql);
    ps.setNString(1, id);
    rs = ps.executeUpdate();
    return rs > 0;
  }

  //update
  public boolean update_account(Account acc)  {
    String sql = "UPDATE account SET full_name = ?, password = ?, email = ?, phone = ?, status = ? WHERE account_id = ?";
    int rs;
    PreparedStatement ps;
    try {
      ps= connection.prepareStatement(sql);
      ps.setNString(1, acc.getFull_name());
      ps.setNString(2, acc.getPassword());
      ps.setNString(3, acc.getEmail());
      ps.setNString(4, acc.getPhone());
      ps.setInt(5, acc.getStatus());
      ps.setNString(6, acc.getAccount_id());
      rs = ps.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

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


  public Account getAccount(String us, String pwd) {
    String sql = "select * from account where full_name = ? and password = ?";
    Account acc = null;
    try {
      PreparedStatement ps = connection.prepareStatement(sql);
      ps.setNString(1, us);
      ps.setNString(2, pwd);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        String id = rs.getString(1);
        String name = rs.getString(2);
        String pass = rs.getString(3);
        String email = rs.getString(4);
        String phone = rs.getString(5);
        int status = rs.getInt(6);
        acc = new Account(id, name, pass, email, phone, status);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return acc;
  }
}
