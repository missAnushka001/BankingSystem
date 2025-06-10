package ui;

import dao.AccountDAO;

import javax.swing.*;
import java.awt.*;

public class BankingAppUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankingAppUI().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Banking Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);

        // Create split pane
        JSplitPane splitPane = new JSplitPane();
        splitPane.setDividerLocation(300);
        splitPane.setResizeWeight(0.3);

        // Left Panel - Input Fields
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(new Color(40, 44, 52));
        leftPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Account Details", 0, 0, new Font("Arial", Font.BOLD, 14), Color.WHITE));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel accLabel = new JLabel("Account Number:");
        accLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        leftPanel.add(accLabel, gbc);

        JTextField accField = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 1;
        leftPanel.add(accField, gbc);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        leftPanel.add(amountLabel, gbc);

        JTextField amountField = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 3;
        leftPanel.add(amountField, gbc);

        // Right Panel - Buttons and Output
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(Color.WHITE);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton balanceBtn = new JButton("Check Balance");

        depositBtn.setToolTipText("Deposit amount to account");
        withdrawBtn.setToolTipText("Withdraw amount from account");
        balanceBtn.setToolTipText("View current balance");

        buttonPanel.add(depositBtn);
        buttonPanel.add(withdrawBtn);
        buttonPanel.add(balanceBtn);

        JTextArea resultArea = new JTextArea();
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        rightPanel.add(buttonPanel, BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(rightPanel);

        frame.add(splitPane);
        frame.setVisible(true);

        AccountDAO dao = new AccountDAO();

        depositBtn.addActionListener(e -> {
            try {
                int accNo = Integer.parseInt(accField.getText().trim());
                double amt = Double.parseDouble(amountField.getText().trim());
                dao.deposit(accNo, amt);
                resultArea.setText("✅ Deposit successful.");
            } catch (Exception ex) {
                resultArea.setText("❌ Error: " + ex.getMessage());
            }
        });

        withdrawBtn.addActionListener(e -> {
            try {
                int accNo = Integer.parseInt(accField.getText().trim());
                double amt = Double.parseDouble(amountField.getText().trim());
                dao.withdraw(accNo, amt);
                resultArea.setText("✅ Withdrawal successful.");
            } catch (Exception ex) {
                resultArea.setText("❌ Error: " + ex.getMessage());
            }
        });

        balanceBtn.addActionListener(e -> {
            try {
                int accNo = Integer.parseInt(accField.getText().trim());
                double balance = dao.getBalance(accNo);
                resultArea.setText("💰 Current Balance: " + balance);
            } catch (Exception ex) {
                resultArea.setText("❌ Error: " + ex.getMessage());
            }
        });
    }
}


