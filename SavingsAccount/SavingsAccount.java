package SavingsAccount;

import Account.Account;
import Accounts.*;
import Bank.Bank;
import CreditAccount.CreditAccount;

public class SavingsAccount extends Account implements Withdrawal, Deposit, FundTransfer {
    // Balance of this bank account
    private double balance = 0.0;

    // Constructor call
    public SavingsAccount(Bank bank, String accountNumber,
                          String firstname, String lastname, String email,String pin, double balance) {
        super(bank, accountNumber, firstname, lastname, email,pin);
        this.balance = balance; // Ensure non-negative balance
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
Returns:
String balance statement.
     */
    @Override
    public void getAccountBalanceStatement() {
        //Complete this method
        System.out.println("Account Balance: " + this.balance);
    }

    /*
    Validates whether this savings account has enough balance to proceed with such a transaction based on the amount that is to be adjusted.
    @Params amount – Amount of money to be supposedly adjusted from this account’s balance.
    @Returns Flag if transaction can proceed by adjusting the account balance by the amount to be changed.
     */
    private boolean hasEnoughBalance(double amount){
        //Complete this method
        return this.balance >= amount;
    }

    /*
    Warns the account owner that their balance is not enough for the transaction to proceed
successfully.
     */
    private void insufficientBalance() {
        //Complete this method
        System.out.println("Insufficient balance for this transaction.");

    }

    /*
    Adjust the account balance of this savings account based on the amount to be adjusted. If it
results to the account balance going less than 0.0, then it is forcibly reset to 0.0.
Params:
amount – Amount to be added or subtracted from the account balance.
     */
    private void adjustAccountBalance(double amount){
        //Complete this method
        this.balance = amount;
        if (this.balance < 0.0) {
            this.balance = 0.0;
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
        if (!(account instanceof SavingsAccount)) {
            throw new IllegalAccountType("Cannot transfer funds to a non-savings account.");
        }
        if (hasEnoughBalance(amount)) {
            adjustAccountBalance(-amount);
            ((SavingsAccount) account).adjustAccountBalance(amount);
            addNewTransaction(account.getAccountNumber(), Transaction.Transactions.FundTransfer,"Success Fund Transfer $"+amount+" to "+ account.getOwnerFullName());
            System.out.println("Success Fund Transfer $"+amount+" to "+ account.getOwnerFullName());
            return true;
        } else {
            insufficientBalance();
            System.out.println("Unsuccessful Fund Transfer $"+amount+" to "+ account.getOwnerFullName());
            return false;
        }
    }
    /*
    Transfers an amount of money from this account to another savings account. Should be used when transferring to other banks.
    @Params bank – Bank object of the recipient.
    @Params account – Account number of recipient.
    @Params amount – Amount of money to be supposedly adjusted from this account’s balance.
    @Returns Flag if fund transfer transaction is successful or not.
    @Throws IllegalAccountType – Cannot fund transfer when the other account is of type CreditAccount.
     */

    @Override
    public synchronized boolean transfer(Bank bank, Account account, double amount) throws IllegalAccountType {
        //Complete this method
        if (account instanceof CreditAccount){
            throw new IllegalAccountType("Cannot fund transfer when the other account is of type CreditAccount.");
        }
        Account toTransfer =  getBank().getBankAccount(bank,account.getAccountNumber());
        if (hasEnoughBalance(amount)) {
            adjustAccountBalance(-amount);
            ((SavingsAccount) toTransfer).adjustAccountBalance(amount);
            return true;
        } else {
            insufficientBalance();
            return false;
        }
    }

    /*
    Deposit some cash into this account. Cannot be greater than the bank’s deposit limit.
    @Params amount – Amount of money to be deposited.
     */
    @Override
    public boolean cashDeposit(double amount) {
        //Complete this method
        if (amount < getBank().getDepositLimit()) {
            adjustAccountBalance(amount);
            return true;
        }
        System.out.println("Deposit Failed. Amount exceeds bank deposit limit.");
        return false;
    }

    /*
    Withdraw an amount of money from this savings account. Cannot proceed if account does not have sufficient balance.
    @Params: amount – Amount of money to be withdrawn.
     */
    @Override
    public  synchronized boolean withdrawal(double amount) {
        //Complete this method
        if (hasEnoughBalance(amount)) {
            adjustAccountBalance(-amount);

            return true;
        } else {
            insufficientBalance();
            return false;
        }
    }
    @Override
    public double loan_balance() {
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
