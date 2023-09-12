package vn.edu.iuh.fit.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

  private static ConnectionDB instance;
  private final Connection connection;
  private ConnectionDB() throws ClassNotFoundException, SQLException {
    Class.forName("org.mariadb.jdbc.Driver");
    String url = "jdbc:mariadb://localhost:3306/mydb";
    connection = DriverManager.getConnection(url, "root", "sapassword");
  }

  public static ConnectionDB getInstance() throws SQLException, ClassNotFoundException {
    if(instance == null)
      instance = new ConnectionDB();
    return instance;
  }

  public Connection getConnection() {
    return connection;
  }
  public void closeConnection() throws SQLException {
    connection.close();
  }

}
