package SavingsAccount;

import Account.Account;
import Accounts.*;
import Bank.Bank;

public class SavingsAccount extends Account implements Withdrawal, Deposit, FundTransfer {
    // Balance of this bank account
    private double balance = 0.0;

    // Constructor call
    public SavingsAccount(Bank bank, String accountNumber,
                          String firstname, String lastname, String email,String pin, double balance) {
        super(bank, accountNumber, firstname, lastname, email,pin);
        this.balance = Math.max(balance, 0.0); // Ensure non-negative balance
    }

    // Getters
    public double getBalance(){
        return this.balance;
    }
    // Setters
    public double setBalance(double newBalance){
        return this.balance = newBalance;
    }

    /*
    Get the account balance statement of this savings account.
    @Returns String balance statement.
     */
    @Override
    public synchronized void getAccountBalanceStatement() {
        //Complete this method

    }

    /*
    Validates whether this savings account has enough balance to proceed with such a transaction based on the amount that is to be adjusted.
    @Params amount – Amount of money to be supposedly adjusted from this account’s balance.
    @Returns Flag if transaction can proceed by adjusting the account balance by the amount to be changed.
     */
    private boolean hasEnoughBalance(double amount){
        //Complete this method
        adjustAccountBalance(amount);
        return balance == 0;
    }

    /*
    Warns the account owner that their balance is not enough for the transaction to proceed
successfully.
     */
    private void insufficientBalance() {
        //Complete this method
        if (balance < 0){
            System.out.println("Insufficient Balance!");
        }
    }

    /*
    Adjust the account balance of this savings account based on the amount to be adjusted. If it
results to the account balance going less than 0.0, then it is forcibly reset to 0.0.
Params:
amount – Amount to be added or subtracted from the account balance.
     */
    private void adjustAccountBalance(double amount){
        //Complete this method
        if  ((balance -= amount) < 0){
            balance += 0.0;
        }else {
            balance -= amount;
        }
    }

    /*
    Transfers an amount of money from this account to another savings account. Is extensively used
by the other transfer() method.
Params:
account – Account number of recipient.
amount – Amount of money to be supposedly adjusted from this account’s balance.
Returns:
Flag if fund transfer transaction is successful or not.
Throws:
IllegalAccountType – Cannot fund transfer when the other account is of type
CreditAccount.
     */
    @Override
    public synchronized boolean transfer(Account account, double amount) throws IllegalAccountType {
        //Complete this method
        return false;
    }
    /*
    Transfers an amount of money from this account to another savings account. Should be used
when transferring to other banks.
Params:
bank – Bank object of the recipient.
account – Account number of recipient.
amount – Amount of money to be supposedly adjusted from this account’s balance.
Returns:
Flag if fund transfer transaction is successful or not.
Throws:
IllegalAccountType – Cannot fund transfer when the other account is of type
CreditAccount.
     */

    @Override
    public synchronized boolean transfer(Bank bank, Account account, double amount) throws IllegalAccountType {
        //Complete this method
        return false;
    }

    /*
    Deposit some cash into this account. Cannot be greater than the bank’s deposit limit.
    Params:
amount – Amount of money to be deposited.
     */
    @Override
    public synchronized boolean cashDeposit(double amount) {
        //Complete this method
        return false;
    }

    /*
Withdraw an amount of money from this savings account. Cannot proceed if account does not
have sufficient balance.
Params:
amount – Amount of money to be withdrawn.
     */
    @Override
    public synchronized boolean withdrawal(double amount) {
        //Complete this method
        String s = "";
//        with self.lock:  # Ensures only one thread modifies the balance at a time
//        print(f"{user} is attempting to withdraw ${amount}...")
//        time.sleep(random.uniform(0.1, 0.5))  # Simulate processing delay
//
//        if self.balance >= amount:
//        self.balance -= amount
//        print(f"{user} successfully withdrew ${amount}. Remaining balance: ${self.balance}")
//            else:
//        print(f"{user} failed to withdraw ${amount}. Insufficient funds!")

    }
    @Override
    public synchronized double loan_balance() {
        return this.balance;
    }

    /*
    Return Str
     */
    public String toString(){
        //Complete this method
        return "Name: " + this.getOwnerFullName() +"\nBalance: " + this.getBalance() +"\n";
    }
}
