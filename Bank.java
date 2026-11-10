//this holds all accounts and transactions in arrays

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Bank {
    // arrays
    private Account[] accounts = new Account[100];
    private Transaction[] transactions = new Transaction[1000];

    // counters
    private int accountCount = 0;
    private int transactionCount = 0;

    //for giving unique trnsaction ID
    private int nextTransactionId = 1;

    // makes a date String
    private String getCurrentDate() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            return LocalDateTime.now().format(formatter);
        }

    //1. add account
    public boolean addAccount(int accountNumber, String fullName, String phone, String address, double initialBalance, String accountType) {
        if (accountCount >= 100) {
            return false;
        }

        if (findAccount(accountNumber) != null) {
            return false;
        }

        if (initialBalance < 0) {
            return false;
        }


        Customer customer = new Customer(accountNumber, fullName, phone, address);
        Account newAccount = new Account(accountNumber, customer, initialBalance, accountType);

        accounts[accountCount] = newAccount;
        accountCount++;

        if (initialBalance > 0) {
                    transactions[transactionCount] = new Transaction(nextTransactionId, accountNumber, "Deposit", initialBalance, getCurrentDate());
                    nextTransactionId++;
                    transactionCount++;
                }
                return true;
            }
        //2. find account
            public Account findAccount(int accountNumber) {
                for (int i = 0; i < accountCount; i++) {
                    if (accounts[i].getAccountNumber() == accountNumber) {
                        return accounts[i];
                    }
                }
                return null;
            }

        // 3. deposit
        public boolean deposit(int accountNumber, double amount) {
                Account acc = findAccount(accountNumber);
                if (acc == null) {
                    return false; // Account does not exist
                }
             
                boolean success = acc.deposit(amount);
                if (success) {
                    // Only if it worked, create a receipt
                    transactions[transactionCount] = new Transaction(nextTransactionId, accountNumber, "Deposit", amount, getCurrentDate());
                    nextTransactionId++;
                    transactionCount++;
                }
                return success;
            }

            //4. withdraw
            public boolean withdraw(int accountNumber, double amount) {
                    Account acc = findAccount(accountNumber);
                    if (acc == null) {
                        return false;
                    }
                    boolean success = acc.withdraw(amount);
                    if (success) {
                        transactions[transactionCount] = new Transaction(nextTransactionId, accountNumber, "Withdraw", amount, getCurrentDate());
                        nextTransactionId++;
                        transactionCount++;
                    }
                    return success;
                }

                //5. transfer
                public boolean transfer(int fromAccountNumber, int toAccountNumber, double amount) {
                        Account fromAcc = findAccount(fromAccountNumber);
                        Account toAcc = findAccount(toAccountNumber);

                    //both accounts must exist
                        if (fromAcc == null || toAcc == null) {
                            return false;
                        }

                    //cannot transfer to yourself
                        if (fromAccountNumber == toAccountNumber) {
                            return false;
                        }

                    //try to withdraw from receiver, if it fails stop
                        boolean withdrawn = fromAcc.withdraw(amount);
                        if (!withdrawn) {
                            return false;
                        }

                    //withdraw worked, now deposit to receiver
                        toAcc.deposit(amount);

                        transactions[transactionCount] = new Transaction(nextTransactionId, fromAccountNumber, "Transfer Out", amount, getCurrentDate());
                                nextTransactionId++;
                                transactionCount++;
                                transactions[transactionCount] = new Transaction(nextTransactionId, toAccountNumber, "Transfer In", amount, getCurrentDate());
                                nextTransactionId++;
                                transactionCount++;
                                return true;
                }          
            
                // 6. view all accounts
                public String viewAllAccounts() {
                        if (accountCount == 0) {
                            return "No accounts yet.";
                        }
                        String result = "";
                        for (int i = 0; i < accountCount; i++) {
                            result = result + accounts[i].toString() + "\n";
                        }
                        return result;
                    }

                //7. view all transactions
            public String viewTransactions(int accountNumber) {
                    if (transactionCount == 0) {
                        return "No transactions yet.";
                    }
                    String result = "";
                    for (int i = 0; i < transactionCount; i++) {
                        if (accountNumber == -1 || transactions[i].getAccountNumber() == accountNumber) {
                            result = result + transactions[i].toString() + "\n";
                        }
                    }
                    if (result.equals("")) {
                        return "No transactions found for this account.";
                    }
                    return result;
                }

            //8. 
            public double getBalance(int accountNumber) {
                    Account acc = findAccount(accountNumber);
                    if (acc == null) {
                        return -1; // -1 means "not found" because balance can never be -1
                    }
                    return acc.getBalance();
                }
        }
