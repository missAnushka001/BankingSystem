# Banking System


## Description

A Java-based banking system built using Object-Oriented Programming principles, Swing GUI, and JDBC/MySQL. This project simulates core banking operations such as account creation, deposits, withdrawals, and transfers via a desktop application.

---

## 🌟 Features

- **Account Management**  
  Create and manage multiple bank account types (e.g., Savings, Checking).

- **Transactions**  
  Perform secure deposit, withdrawal, and inter-account transfer operations.

- **User Authentication**  
  Simple login/logout screens for admin and/or customer access (customize as needed).

- **Account Activity Logs**  
  View transaction history with timestamps for each account.

- **Database Integration**  
  Uses MySQL for persistent data storage (accounts, balances, transactions).

- **Graphical User Interface**  
  Friendly desktop interface built with Swing.

---

## 🛠️ Technologies

- Java SE (8 or above)  
- Swing for GUI  
- JDBC for database connectivity  
- MySQL (or compatible) database



## 🚀 Getting Started

### 1. Clone the Repository  
```bash
git clone https://github.com/missAnushka001/BankingSystem.git
cd BankingSystem
  ```

## Screenshots
![Screenshot 2025-06-10 104423](https://github.com/user-attachments/assets/3fd95848-d611-4504-9b07-dc5563957c64)

## Setup MySQL Database

Create a database named banking_system.
Run the included SQL script (e.g., schema.sql) to create tables:

```bash
CREATE TABLE accounts (
  account_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  type VARCHAR(20),
  balance DECIMAL(15,2),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE transactions (
  txn_id INT AUTO_INCREMENT PRIMARY KEY,
  account_id INT,
  type VARCHAR(20),
  amount DECIMAL(15,2),
  timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);

```
##Configure Database Credentials

```bash
private static final String DB_URL = "jdbc:mysql://localhost:3306/banking_system";
private static final String USER = "root";
private static final String PASS = "your_password";

```

##Build & Run

Open the project in your favorite IDE (e.g., IntelliJ IDEA, NetBeans, Eclipse).
Ensure JDBC & MySQL connector libraries are in the classpath (e.g., via Maven/Gradle or manually).
Run the Main.java (or your app launcher class).
The Swing GUI window will open, allowing you to log in and perform banking operations.

## 📋 Usage

Login: Sign in as an admin or user (if implemented).
Create Account: Enter details for a new account (name, type, initial deposit).
Deposit/Withdraw: Choose an account and perform transactions using the GUI.
Transfer Funds: Move money from one account to another.
View History: Select an account to see its full transaction history.

##🧩 Project Structure

```bash
├── src/
│   ├── model/            # Domain classes (Account, Transaction, etc.)
│   ├── dao/              # Database access objects (JDBC logic)
│   ├── ui/               # Swing-based GUI classes
│   ├── util/             # Utils (DB connection, helpers)
│   └── Main.java         # Application entry point
├── lib/                  # External libraries (MySQL connector, etc.)
├── sql/
│   └── schema.sql        # SQL scripts for DB schema
├── README.md             # This file
└── .gitignore
```
##💡 Innovation Highlights
GUI-based fund transfer simulation with transaction timestamps.
Modular code for easier enhancements like interest calculation or multi-user login.
Prepared for enhancements like report generation or REST API integration.

## 🙌 Contributions

Want to improve the project?
Fork this repo
Create your feature branch: git checkout -b feature-name
Commit changes: git commit -m "Add feature"
Push: git push origin feature-name
Submit a Pull Request 🎉

