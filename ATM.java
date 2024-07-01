import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ATM extends JFrame {
    private BankAccount bankAccount;
    private JTextField amountField;
    private JTextArea outputArea;

    public ATM(BankAccount bankAccount) {
        super("ATM");
        this.bankAccount = bankAccount;

        // Initialize components
        amountField = new JTextField(10);
        amountField.setFont(new Font("Arial", Font.PLAIN, 16));
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 14));
        outputArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JButton checkBalanceButton = new JButton("Check Balance");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");

        // Customize buttons
        customizeButton(checkBalanceButton);
        customizeButton(depositButton);
        customizeButton(withdrawButton);

        // Add action listeners
        checkBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        // Layout components using GridBagLayout
        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        topPanel.add(new JLabel("Amount:"), gbc);

        gbc.gridx = 1;
        topPanel.add(amountField, gbc);

        gbc.gridx = 2;
        topPanel.add(checkBalanceButton, gbc);

        gbc.gridx = 3;
        topPanel.add(depositButton, gbc);

        gbc.gridx = 4;
        topPanel.add(withdrawButton, gbc);

        JScrollPane scrollPane = new JScrollPane(outputArea);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame on screen
        setVisible(true);
    }

    private void customizeButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }

    private void checkBalance() {
        double balance = bankAccount.getBalance();
        outputArea.append("Current Balance: $" + balance + "\n");
    }

    private void deposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            bankAccount.deposit(amount);
            outputArea.append("Deposited: $" + amount + "\n");
        } catch (NumberFormatException ex) {
            outputArea.append("Invalid amount for deposit.\n");
        }
    }

    private void withdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            bankAccount.withdraw(amount);
            outputArea.append("Withdrawn: $" + amount + "\n");
        } catch (NumberFormatException ex) {
            outputArea.append("Invalid amount for withdrawal.\n");
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000); // Initial balance $1000
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ATM(account);
            }
        });
    }
}



