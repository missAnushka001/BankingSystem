package ui;

import dao.AccountDAO;
import model.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BankingAppUI extends JFrame {
    private JComboBox<String> senderDropdown;
    private JComboBox<String> receiverDropdown;
    private JTextField amountField;
    private JButton transferButton;
    private JTextArea messageArea;

    private AccountDAO accountDAO;

    public BankingAppUI() {
        setTitle("Banking System");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        accountDAO = new AccountDAO();

        // Top Panel: Transfer Controls
        JPanel topPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        topPanel.add(new JLabel("Sender Account:"));
        senderDropdown = new JComboBox<>();
        topPanel.add(senderDropdown);

        topPanel.add(new JLabel("Receiver Account:"));
        receiverDropdown = new JComboBox<>();
        topPanel.add(receiverDropdown);

        topPanel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        topPanel.add(amountField);

        transferButton = new JButton("Transfer");
        topPanel.add(transferButton);

        add(topPanel, BorderLayout.NORTH);

        // Bottom Panel: Messages
        messageArea = new JTextArea(5, 40);
        messageArea.setEditable(false);
        add(new JScrollPane(messageArea), BorderLayout.CENTER);

        loadAccounts();
        setupListeners();
    }

    private void loadAccounts() {
        List<Account> accounts = accountDAO.getAllAccounts();
        for (Account acc : accounts) {
            String item = acc.getAccNumber() + " - " + acc.getName();
            senderDropdown.addItem(item);
            receiverDropdown.addItem(item);
        }
    }

    private void setupListeners() {
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleTransfer();
            }
        });
    }

    private void handleTransfer() {
        try {
            String senderStr = (String) senderDropdown.getSelectedItem();
            String receiverStr = (String) receiverDropdown.getSelectedItem();
            String amountStr = amountField.getText().trim();

            if (senderStr == null || receiverStr == null || amountStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int senderAcc = Integer.parseInt(senderStr.split(" - ")[0]);
            int receiverAcc = Integer.parseInt(receiverStr.split(" - ")[0]);

            if (senderAcc == receiverAcc) {
                JOptionPane.showMessageDialog(this, "Sender and receiver cannot be the same.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double amount = Double.parseDouble(amountStr);
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Amount must be positive.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Account sender = accountDAO.getAccount(senderAcc);
            Account receiver = accountDAO.getAccount(receiverAcc);

            if (sender == null || receiver == null) {
                JOptionPane.showMessageDialog(this, "Account not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (sender.getBalance() < amount) {
                JOptionPane.showMessageDialog(this, "Insufficient balance.", "Transaction Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean success = accountDAO.transferAmount(senderAcc, receiverAcc, amount);
            if (success) {
                messageArea.append("Transfer successful: ₹" + amount + " from " + sender.getName() + " to " + receiver.getName() + "\n");
            } else {
                messageArea.append("Transfer failed due to DB error.\n");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Unexpected error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BankingAppUI().setVisible(true);
        });
    }
}
