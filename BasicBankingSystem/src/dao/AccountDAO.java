package dao;

import model.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/banking_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT * FROM accounts";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                accounts.add(new Account(
                        rs.getInt("acc_number"),
                        rs.getString("name"),
                        rs.getString("acc_type"),
                        rs.getDouble("balance")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public Account getAccount(int accNumber) {
        String query = "SELECT * FROM accounts WHERE acc_number = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, accNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Account(
                        rs.getInt("acc_number"),
                        rs.getString("name"),
                        rs.getString("acc_type"),
                        rs.getDouble("balance")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean transferAmount(int fromAcc, int toAcc, double amount) {
        String debitQuery = "UPDATE accounts SET balance = balance - ? WHERE acc_number = ?";
        String creditQuery = "UPDATE accounts SET balance = balance + ? WHERE acc_number = ?";
        String insertTransaction = "INSERT INTO transactions (from_acc, to_acc, amount) VALUES (?, ?, ?)";

        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false); // Begin transaction

            // Debit sender
            try (PreparedStatement debitStmt = conn.prepareStatement(debitQuery)) {
                debitStmt.setDouble(1, amount);
                debitStmt.setInt(2, fromAcc);
                int affected = debitStmt.executeUpdate();
                if (affected != 1) {
                    conn.rollback();
                    return false;
                }
            }

            // Credit receiver
            try (PreparedStatement creditStmt = conn.prepareStatement(creditQuery)) {
                creditStmt.setDouble(1, amount);
                creditStmt.setInt(2, toAcc);
                int affected = creditStmt.executeUpdate();
                if (affected != 1) {
                    conn.rollback();
                    return false;
                }
            }

            // Log transaction
            try (PreparedStatement logStmt = conn.prepareStatement(insertTransaction)) {
                logStmt.setInt(1, fromAcc);
                logStmt.setInt(2, toAcc);
                logStmt.setDouble(3, amount);
                logStmt.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
