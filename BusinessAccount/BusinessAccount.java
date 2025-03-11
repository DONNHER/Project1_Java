package BusinessAccount;

import Account.Account;
import Accounts.*;
import Bank.Bank;
import CreditAccount.CreditAccount;
import SavingsAccount.SavingsAccount;

public class BusinessAccount extends Account implements Deposit, Withdrawal, FundTransfer, Payment {
    private double balance = 0;
    private final String company;
    public BusinessAccount(Bank bank,String company, String accountNumber, String firstName ,String lastName, String email, String pin,double balance) {
        super(bank, accountNumber, firstName, lastName, email, pin);
        this.balance = balance;
        this.company = company;
    }
    public String getCompany(){
        return this.company;
    }
    public double getBalance(){
        return this.balance;
    }
    /*
    Get the account balance statement of this savings account.
Returns:
String balance statement.
     */
    @Override
    public void getAccountBalanceStatement() {
        //Complete this method
        String s = "Account Number: "+getAccountNumber()+"\n";
        s += "Company: " +getCompany() +"\n";
        s += "Name: " +getOwnerFullName() +"\n";
        s += "Account Balance: " + this.balance;
        System.out.println(s);
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
    Adjust the owner’s current loan. Result of adjustment cannot be less than 0.
    @Param amountAdjustment – Amount to be adjusted to the loan of this credit account.
     */
    private synchronized void adjustAccountBalance(double amountAdjustment){
        //Complete this method
        this.balance += amountAdjustment;
        if (balance < 0){
            this.balance = 0.0;
        }
    }

    @Override
    public synchronized boolean cashDeposit(double amount) {
        //Complete this method
        if (amount < getBank().getDepositLimit()) {
            adjustAccountBalance(amount);
            return true;
        }
        return false;
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
        if (account instanceof CreditAccount) {
            throw new IllegalAccountType("Cannot transfer funds to a non-savings account.");
        }
        if (hasEnoughBalance(amount)) {
            adjustAccountBalance(-amount);
            ((BusinessAccount) account ).adjustAccountBalance(amount);
            return true;
        } else {
            insufficientBalance();
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
    public boolean transfer(Bank bank,Account account, double amount) throws IllegalAccountType {
        //Complete this method
        if (account instanceof CreditAccount) {
            throw new IllegalAccountType("Cannot fund transfer when the other account is of type CreditAccount.");
        }
        Account toTransfer = getBank().getBankAccount(bank, account.getAccountNumber());
        if (hasEnoughBalance(amount)) {
            adjustAccountBalance(-amount);
            ((BusinessAccount) toTransfer).adjustAccountBalance(amount);
            return true;
        } else {
            insufficientBalance();
            return false;
        }
    }

    @Override
    public synchronized boolean withdrawal(double amount) {
        if (hasEnoughBalance(amount)) {
            if(amount <= this.getBank().getWithdrawLimit()) {
                adjustAccountBalance(-amount);
                return true;
            }
            System.out.println("Withdraw Failed: The amount to be withdraw exceeds to Bank withdrawal limit.");
            return false;
        }
        insufficientBalance();
        return false;
    }


    public String toString(){
        return "Name: " + this.getOwnerFullName();
    }

    @Override
    public synchronized boolean pay(Account account, double amount) throws IllegalAccountType {
        if (account instanceof CreditAccount) {
            // Throw an exception if it's a CreditAccount
            throw new IllegalAccountType("Credit Accounts cannot pay to other Credit Accounts as they do not hold account money balance.");
        }
        if (hasEnoughBalance(amount)) {
            // If it's a valid SavingsAccount (or other type), perform the payment
            // Assuming there's a method to deposit the money in the account
            SavingsAccount payed = (SavingsAccount) account;
            return payed.cashDeposit(amount);
        }
        return false;
    }
}
