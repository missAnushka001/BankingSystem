package model;

import java.time.LocalDateTime;

public class Transaction {
    private int id;
    private int fromAcc;
    private int toAcc;
    private double amount;
    private LocalDateTime timestamp;

    public Transaction(int id, int fromAcc, int toAcc, double amount, LocalDateTime timestamp) {
        this.id = id;
        this.fromAcc = fromAcc;
        this.toAcc = toAcc;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public int getFromAcc() {
        return fromAcc;
    }

    public int getToAcc() {
        return toAcc;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Txn#" + id + " | From: " + fromAcc + " → To: " + toAcc + " | ₹" + amount + " | " + timestamp;
    }
}

