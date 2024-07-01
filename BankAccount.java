public class BankAccount {
    private double balance;

    // Constructor to initialize the balance
    public BankAccount(double balance) {
        this.balance = balance;
    }

    // Method to get current balance
    public double getBalance() {
        return balance;
    }

    // Method to deposit funds into the account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid amount for deposit.");
        }
    }

    // Method to withdraw funds from the account
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Insufficient funds or invalid amount for withdrawal.");
        }
    }
}

