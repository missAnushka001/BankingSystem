package dao;

import model.Transaction;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/banking_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions ORDER BY timestamp DESC";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Transaction txn = new Transaction(
                        rs.getInt("id"),
                        rs.getInt("from_acc"),
                        rs.getInt("to_acc"),
                        rs.getDouble("amount"),
                        rs.getTimestamp("timestamp").toLocalDateTime()
                );
                transactions.add(txn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }
}
