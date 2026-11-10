// Duku Henry Mugume 26/E/26521/PS
// Mayen Ayiik Mayen 26/E/26534/PS 

//This class represents one bank account

public class Account {
    
    private int accountNumber;
    private Customer customer; 
    private double balance;
    private String accountType;

    //constructor
        public Account(int accountNumber, Customer customer, double balance, String accountType) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.balance = balance;
        this.accountType = accountType;
    }

    //deposit
        public boolean deposit(double amount) {
        if (amount > 0) {
            balance = balance + amount; 
            return true; 
        } else {
            return false; 
        }
    }

    //withdraw
        public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance = balance - amount; 
            return true; 
        } else {
            return false; 
        }
    }

    //getters
    
    public int getAccountNumber() {
        return accountNumber;
    }
    public Customer getCustomer() {
        return customer;
    }
    public double getBalance() {
        return balance;
    }
    public String getAccountType() {
        return accountType;
    }

    // toString
        @Override
    public String toString() {
        return "Acc No: " + accountNumber +
               "Type: " + accountType +
               "Balance: " + balance +
               "Owner: " + customer.getFullName();
    }
}

