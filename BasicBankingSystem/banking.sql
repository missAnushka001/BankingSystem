CREATE DATABASE banking_system;

USE banking_system;

CREATE TABLE accounts (
    acc_number INT PRIMARY KEY,
    name VARCHAR(100),
    acc_type VARCHAR(20),
    balance DOUBLE
);
