// package Accounts;

// public interface Deposit {


//     /**
//      * Deposit an amount of money to some given account.
//      * @param amount Amount to be deposited.
//      * @return Flag if transaction is successful or not.
//      */
//     public boolean cashDeposit(double amount);

package Accounts;

// Interface defining deposit operation
public interface Deposit {
    void deposit(double amount);
}

// Class implementing the Deposit interface
class BankAccount implements Deposit {
    private double balance;

    // Constructor to initialize balance
    public BankAccount(double balance) {
        this.balance = balance;
    }

    // Corrected deposit method
    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
    /**
     * Deposit an amount of money to the account.
     * @param amount Amount to be deposited.
     * @return Flag if transaction is successful or not.
     */
    public boolean cashDeposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        } else {
            return false;
        }
    }
}
