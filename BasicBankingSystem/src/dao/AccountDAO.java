package dao;

import model.Account;
import utils.DBConnection;
import java.sql.*;

public class AccountDAO {
    public void deposit(int accNumber, double amount) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE acc_number = ?");
        ps.setDouble(1, amount);
        ps.setInt(2, accNumber);
        ps.executeUpdate();
    }

    public void withdraw(int accNumber, double amount) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("UPDATE accounts SET balance = balance - ? WHERE acc_number = ? AND balance >= ?");
        ps.setDouble(1, amount);
        ps.setInt(2, accNumber);
        ps.setDouble(3, amount);
        ps.executeUpdate();
    }

    public double getBalance(int accNumber) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT balance FROM accounts WHERE acc_number = ?");
        ps.setInt(1, accNumber);
        ResultSet rs = ps.executeQuery();
        return rs.next() ? rs.getDouble("balance") : 0;
    }
}
