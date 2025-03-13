package BusinessAccount;

import Account.Account;
import Accounts.*;
import Bank.Bank;
import CreditAccount.CreditAccount;
import SavingsAccount.SavingsAccount;

/*
  A BusinessAccount that supports deposits, withdrawals, transfers, and payments.
 */
public class BusinessAccount extends Account implements Deposit, Withdrawal, FundTransfer, Payment {
    private double balance = 0;
    private final String company;

    /*
     Creates a BusinessAccount with the given details.
     */
    public BusinessAccount(Bank bank, String company, String accountNumber, String firstName, String lastName, String email, String pin, double balance) {
        super(bank, accountNumber, firstName, lastName, email, pin);
        this.balance = balance;
        this.company = company;
    }

    /*
     Gets the name of the company linked to this account.
     @return The name of the company.
     */
    public String getCompany() {
        return this.company;
    }

    /*
     Gets the current balance of the account.
     @return The account balance.
     */
    public double getBalance() {
        return this.balance;
    }

    /*
     Shows the account balance statement.
     */
    @Override
    public void getAccountBalanceStatement() {
        String s = "Account Number: " + getAccountNumber() + "\n";
        s += "Company: " + getCompany() + "\n";
        s += "Name: " + getOwnerFullName() + "\n";
        s += "Account Balance: " + this.balance;
        System.out.println(s);
    }

    /*
     Checks if there is enough balance for a transaction.
     @param amount The amount to check against the current balance.
     @return True if there is enough balance, false otherwise.
     */
    private boolean hasEnoughBalance(double amount) {
        return this.balance >= amount;
    }

    /*
     Displays a message when there is not enough balance.
     */
    private void insufficientBalance() {
        System.out.println("Insufficient balance for this transaction.");
    }

    /*
     Adjusts the account balance by a specified amount.
     @param amountAdjustment The amount to be added or subtracted from the balance.
     */
    private synchronized void adjustAccountBalance(double amountAdjustment) {
        this.balance += amountAdjustment;
        if (balance < 0) {
            this.balance = 0.0;
        }
    }

    /*
     Deposits money into the account if it's below the deposit limit.
     @param amount The amount of money to be deposited.
     @return True if the deposit was successful, false otherwise.
     */
    @Override
    public synchronized boolean cashDeposit(double amount) {
        if (amount < getBank().getDepositLimit()) {
            adjustAccountBalance(amount);
            return true;
        }
        return false;
    }

    /*
     Transfers money to another account within the same bank.
     @param account The recipient account.
     @param amount The amount of money to transfer.
     @return True if the transfer was successful, false otherwise.
     @throws IllegalAccountType If the recipient account is a CreditAccount.
     */
    @Override
    public synchronized boolean transfer(Account account, double amount) throws IllegalAccountType {
        if (account instanceof CreditAccount) {
            throw new IllegalAccountType("Cannot transfer funds to a CreditAccount.");
        }
        if (hasEnoughBalance(amount)) {
            adjustAccountBalance(-amount);
            ((BusinessAccount) account).adjustAccountBalance(amount);
            return true;
        } else {
            insufficientBalance();
            return false;
        }
    }

    /*
     Transfers money to an account in a different bank.
     @param bank The recipient's bank.
     @param account The recipient account.
     @param amount The amount of money to transfer.
     @return True if the transfer was successful, false otherwise.
     @throws IllegalAccountType If the recipient account is a CreditAccount.
     */
    @Override
    public boolean transfer(Bank bank, Account account, double amount) throws IllegalAccountType {
        if (account instanceof CreditAccount) {
            throw new IllegalAccountType("Cannot transfer to a CreditAccount.");
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

    /*
     Withdraws money from the account if there is enough balance and it's within the bank's limit.
     @param amount The amount of money to withdraw.
     @return True if the withdrawal was successful, false otherwise.
     */
    @Override
    public synchronized boolean withdrawal(double amount) {
        if (hasEnoughBalance(amount)) {
            if (amount <= this.getBank().getWithdrawLimit()) {
                adjustAccountBalance(-amount);
                return true;
            }
            System.out.println("Withdraw Failed: The amount exceeds the bank's withdrawal limit.");
            return false;
        }
        insufficientBalance();
        return false;
    }

    /*
     Makes a payment to another account if there is enough balance.
     @param account The recipient account to be paid.
     @param amount The amount to pay.
     @return True if the payment was successful, false otherwise.
     @throws IllegalAccountType If the recipient account is a CreditAccount.
     */
    @Override
    public synchronized boolean pay(Account account, double amount) throws IllegalAccountType {
        if (account instanceof CreditAccount) {
            throw new IllegalAccountType("Cannot pay to a CreditAccount.");
        }
        if (hasEnoughBalance(amount)) {
            SavingsAccount payed = (SavingsAccount) account;
            return payed.cashDeposit(amount);
        }
        return false;
    }

    /*
     Returns the owner's full name as a string.
     @return The owner's full name.
     */
    public String toString() {
        return "Name: " + this.getOwnerFullName();
    }
}
