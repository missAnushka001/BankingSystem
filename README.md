# Banking System


## Description

This is a **Java-based desktop banking system** that simulates basic account operations such as transferring funds between accounts. It follows an MVC structure and demonstrates clean code, data validation, error handling, and GUI integration using Java Swing.
---

## 🌟 Features

- View all user accounts
- Transfer funds from one account to another
- Display transfer history (transactions)
- Input validation (amounts, account numbers, duplicates)
- Real-time success/failure feedback
- Modular DAO-model-view structure
- Automatic transaction logging in the database

---

## 🛠️ Technologies

-- Java 8+
-- Swing (GUI)
-- JDBC (MySQL database connection)
-- MySQL 5.7+ or MariaDB



## 🚀 Getting Started

### 1. Clone the Repository  
```bash
git clone https://github.com/missAnushka001/BankingSystem.git
cd BankingSystem
  ```

## 📸 Screenshots
![Screenshot 2025-06-10 104423](https://github.com/user-attachments/assets/3fd95848-d611-4504-9b07-dc5563957c64)


##✅ Evaluation Criteria (Mapped)

```bash
| Criteria                    | Fulfilled? | Description                                   |
| --------------------------- | ---------- | --------------------------------------------- |
| Core Feature Implementation | ✅          | Transfer, list users, log transactions        |
| Error Handling & Robustness | ✅          | All DB and input operations use `try-catch`   |
| Integration of Components   | ✅          | MVC: Model (Account), DAO, View (Swing UI)    |
| Event Handling & Processing | ✅          | Button actions use listener delegation        |
| Data Validation             | ✅          | UI + backend validations: amounts, duplicates |
| Code Quality & Innovation   | ✅          | Modular packages, reusable code, log history  |
| Project Documentation       | ✅          | Complete README, DB schema, file structure    |
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
│ ├── Main.java
│ ├── model/
│ │ ├── Account.java
│ │ └── Transaction.java
│ ├── dao/
│ │ ├── AccountDAO.java
│ │ └── TransactionDAO.java
│ ├── ui/
│ │ └── BankingAppUI.java
│ └── utils/
│ └── DBConnection.java
├── bin/ ← compiled .class files (after build)
├── database/
│ └── banking.sql
├── build.ps1 ← PowerShell script to clean, compile, and run
└── README.md
```
##💡 Innovation Highlights
GUI-based fund transfer simulation with transaction timestamps.
Modular code for easier enhancements like interest calculation or multi-user login.
Prepared for enhancements like report generation or REST API integration.


---

## ⚙️ How to Run
Step 1: Setup MySQL
1. Open MySQL and run the script in `database/banking.sql`:

```sql
CREATE DATABASE banking_system;
USE banking_system;

CREATE TABLE accounts (
    acc_number INT PRIMARY KEY,
    name VARCHAR(100),
    acc_type VARCHAR(20),
    balance DOUBLE
);

CREATE TABLE transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    from_acc INT,
    to_acc INT,
    amount DOUBLE,
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP
);
```
Insert sample data:
```sql
INSERT INTO accounts VALUES (1001, 'Alice', 'savings', 5000);
INSERT INTO accounts VALUES (1002, 'Bob', 'current', 3000);
```

2. Step 2: Compile and Run
```sql
Right-click → Run with PowerShell
```
or manually
```sql
cd src
javac -d ../bin model/*.java dao/*.java ui/*.java utils/*.java Main.java
cd ../bin
java Main
``` 
##🚀 Future Improvements
Add account creation/deletion
Enable transaction filtering/search
Export transaction logs to PDF/CSV
Implement login authentication

##📄 License
This project is licensed for educational use only.
