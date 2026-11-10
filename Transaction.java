//this class represents one receipt

public class Transaction {
    
    //attributes
    private int transactionId;
    private int accountNumber;
    private String type;
    private double amount;
    private String date;

    // constructor
        public Transaction(int transactionId, int accountNumber, String type, double amount, String date) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    // getters
    
    public int getTransactionId() {
        return transactionId;
    }
    public int getAccountNumber() {
        return accountNumber;
    }
    public String getType() {
        return type;
    }
    public double getAmount() {
        return amount;
    }
    public String getDate() {
        return date;
    }

        @Override
    public String toString() {
        return "ID: " + transactionId +
               "Acc No: " + accountNumber +
               "Type: " + type +
               "Amount: " + amount +
               "Date: " + date;
    }
}
