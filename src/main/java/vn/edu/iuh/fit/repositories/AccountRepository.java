package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.models.Account;

import java.sql.*;

public class AccountRepository {
    private final Connection connection;

    public AccountRepository() throws Exception {
        String url = "jdbc:mariadb://localhost:3306/mydb";
        connection = DriverManager.getConnection(url,"root","sapassword");
    }

    public boolean insert_account(Account acc) throws SQLException {
        String sql = "INSERT INTO account VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setNString(1,acc.getAccount_id());
        ps.setNString(2,acc.getFull_name());
        ps.setNString(3,acc.getPassword());
        ps.setNString(4,acc.getEmail());
        ps.setNString(5,acc.getPhone());
        ps.setInt(6,acc.getStatus());

        int rs = ps.executeUpdate();



        return rs>0;
    }


}
