// Duku Henry Mugume 26/E/26521/PS
// Mayen Ayiik Mayen 26/E/26534/PS 


//only class the user sees

import javax.swing.*;
import java.awt.*;

public class BankSystemGUI extends JFrame {

        //bank object
        private Bank bank = new Bank();

        //input fields
             private JTextField accNoField = new JTextField(10);
            private JTextField nameField = new JTextField(10);
            private JTextField phoneField = new JTextField(10);
            private JTextField addressField = new JTextField(10);
            private JTextField amountField = new JTextField(10);
            private JTextField toAccField = new JTextField(10); // For Transfer: destination account
            private JTextField typeField = new JTextField(10);

            //output area
            private JTextArea outputArea = new JTextArea(12, 45);

          public BankSystemGUI() {
                  setTitle("Simple Banking System");
                  setSize(650, 550);
                  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close program when X is clicked
                  setLocationRelativeTo(null); // Center window on screen
                  setLayout(new BorderLayout()); 

                // ----- TOP PANEL: Input fields -----
                JPanel inputPanel = new JPanel(new GridLayout(7, 2, 5, 5));
                inputPanel.add(new JLabel("Account Number:"));
                inputPanel.add(accNoField);
                inputPanel.add(new JLabel("Full Name (for Create):"));
                inputPanel.add(nameField);
                inputPanel.add(new JLabel("Phone (for Create):"));
                inputPanel.add(phoneField);
                inputPanel.add(new JLabel("Address (for Create):"));
                inputPanel.add(addressField);
                inputPanel.add(new JLabel("Amount:"));
                inputPanel.add(amountField);
                inputPanel.add(new JLabel("To Account (for Transfer):"));
                inputPanel.add(toAccField);
                inputPanel.add(new JLabel("Account Type (Savings/Current):"));
                inputPanel.add(typeField);

                        // ----- MIDDLE PANEL: Buttons -----
                        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 5, 5));
                        JButton createBtn = new JButton("Create Account");
                        JButton depositBtn = new JButton("Deposit");
                        JButton withdrawBtn = new JButton("Withdraw");
                        JButton transferBtn = new JButton("Transfer");
                        JButton balanceBtn = new JButton("Check Balance");
                        JButton viewAllBtn = new JButton("View All Accounts");
                        JButton viewTransBtn = new JButton("View Transactions");
                        JButton clearBtn = new JButton("Clear");

                        buttonPanel.add(createBtn);
                        buttonPanel.add(depositBtn);
                        buttonPanel.add(withdrawBtn);
                        buttonPanel.add(transferBtn);
                        buttonPanel.add(balanceBtn);
                        buttonPanel.add(viewAllBtn);
                        buttonPanel.add(viewTransBtn);
                        buttonPanel.add(clearBtn);

                        // ----- BOTTOM PANEL: Output -----
                        outputArea.setEditable(false); // User can read but not type here
                        JScrollPane scrollPane = new JScrollPane(outputArea); // Adds scrollbar
                        JPanel outputPanel = new JPanel();
                        outputPanel.add(scrollPane);

                    // Add panels to the JFrame
                    add(inputPanel, BorderLayout.NORTH);
                    add(buttonPanel, BorderLayout.CENTER);
                    add(outputPanel, BorderLayout.SOUTH);

                    // ---------- BUTTON ACTIONS (ActionListener) ----------
                           // Each button listens for a click, then asks the Bank to do work.
                           // 1. CREATE ACCOUNT
                           createBtn.addActionListener(e -> {
                               try {
                                   int accNo = Integer.parseInt(accNoField.getText());
                                   String name = nameField.getText();
                                   String phone = phoneField.getText();
                                   String address = addressField.getText();
                                   double amount = Double.parseDouble(amountField.getText()); // Initial balance
                                   String type = typeField.getText();
                                   if (type.isEmpty()) type = "Savings"; // Default if user leaves it empty
                                   boolean ok = bank.addAccount(accNo, name, phone, address, amount, type);
                                   if (ok) {
                                       JOptionPane.showMessageDialog(this, "Account created successfully!");
                                       outputArea.setText(bank.viewAllAccounts());
                                   } else {
                                       JOptionPane.showMessageDialog(this, "Failed! Account number may exist, or balance is negative, or bank is full.");
                                   }
                               } catch (NumberFormatException ex) {
                                   JOptionPane.showMessageDialog(this, "Please enter valid numbers for Account Number and Amount.");
                               }
                           });
                           // 2. DEPOSIT
                           depositBtn.addActionListener(e -> {
                               try {
                                   int accNo = Integer.parseInt(accNoField.getText());
                                   double amount = Double.parseDouble(amountField.getText());
                                   boolean ok = bank.deposit(accNo, amount);
                                   if (ok) {
                                       JOptionPane.showMessageDialog(this, "Deposit successful!");
                                   } else {
                                       JOptionPane.showMessageDialog(this, "Deposit failed! Check account number and amount > 0.");
                                   }
                               } catch (NumberFormatException ex) {
                                   JOptionPane.showMessageDialog(this, "Please enter valid numbers.");
                               }
                           });
                           // 3. WITHDRAW
                           withdrawBtn.addActionListener(e -> {
                               try {
                                   int accNo = Integer.parseInt(accNoField.getText());
                                   double amount = Double.parseDouble(amountField.getText());
                                   boolean ok = bank.withdraw(accNo, amount);
                                   if (ok) {
                                       JOptionPane.showMessageDialog(this, "Withdraw successful!");
                                   } else {
                                       JOptionPane.showMessageDialog(this, "Withdraw failed! Check account, amount > 0, and enough balance.");
                                   }
                               } catch (NumberFormatException ex) {
                                   JOptionPane.showMessageDialog(this, "Please enter valid numbers.");
                               }
                           });
                           // 4. TRANSFER
                           transferBtn.addActionListener(e -> {
                               try {
                                   int fromAcc = Integer.parseInt(accNoField.getText());
                                   int toAcc = Integer.parseInt(toAccField.getText());
                                   double amount = Double.parseDouble(amountField.getText());
                                   boolean ok = bank.transfer(fromAcc, toAcc, amount);
                                   if (ok) {
                                       JOptionPane.showMessageDialog(this, "Transfer successful!");
                                   } else {
                                       JOptionPane.showMessageDialog(this, "Transfer failed! Check both accounts, amount, and balance.");
                                   }
                               } catch (NumberFormatException ex) {
                                   JOptionPane.showMessageDialog(this, "Please enter valid numbers for both accounts and amount.");
                               }
                           });
                           // 5. CHECK BALANCE
                           balanceBtn.addActionListener(e -> {
                               try {
                                   int accNo = Integer.parseInt(accNoField.getText());
                                   double bal = bank.getBalance(accNo);
                                   if (bal == -1) {
                                       JOptionPane.showMessageDialog(this, "Account not found!");
                                   } else {
                                       JOptionPane.showMessageDialog(this, "Balance for Account " + accNo + " is: " + bal);
                                   }
                               } catch (NumberFormatException ex) {
                                   JOptionPane.showMessageDialog(this, "Please enter a valid account number.");
                               }
                           });
                           // 6. VIEW ALL ACCOUNTS
                           viewAllBtn.addActionListener(e -> {
                               outputArea.setText(bank.viewAllAccounts());
                           });
                           // 7. VIEW TRANSACTIONS
                           viewTransBtn.addActionListener(e -> {
                               try {
                                   // If user typed an account number, show only for that account.
                                   // If empty, show ALL transactions.
                                   String text = accNoField.getText().trim();
                                   if (text.isEmpty()) {
                                       outputArea.setText(bank.viewTransactions(-1));
                                   } else {
                                       int accNo = Integer.parseInt(text);
                                       outputArea.setText(bank.viewTransactions(accNo));
                                   }
                               } catch (NumberFormatException ex) {
                                   JOptionPane.showMessageDialog(this, "Please enter a valid account number or leave it empty for all.");
                               }
                           });
                           // 8. CLEAR
                           clearBtn.addActionListener(e -> {
                               accNoField.setText("");
                               nameField.setText("");
                               phoneField.setText("");
                               addressField.setText("");
                               amountField.setText("");
                               toAccField.setText("");
                               typeField.setText("");
                               outputArea.setText("");
                           });
                       }
                       // ---------- MAIN METHOD: START THE PROGRAM ----------
                       public static void main(String[] args) {
                           // SwingUtilities.invokeLater makes sure the GUI starts safely on the correct thread
                           SwingUtilities.invokeLater(() -> {
                               BankSystemGUI gui = new BankSystemGUI();
                               gui.setVisible(true); // Shows the window. 
                           });
                       } 
                        
    }

