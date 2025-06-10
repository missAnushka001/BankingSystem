package model;

public class Account {
    private int accNumber;
    private String name;
    private String accType;
    private double balance;

    public int getAccNumber() { return accNumber; }
    public void setAccNumber(int accNumber) { this.accNumber = accNumber; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAccType() { return accType; }
    public void setAccType(String accType) { this.accType = accType; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}
