package StudentAccount;

import Account.Account;
import Accounts.Deposit;
import Accounts.Withdrawal;
import Bank.Bank;

public class StudentAccount extends Account implements Deposit, Withdrawal {
    private double balance = 0;
    private final String program;
    public StudentAccount(Bank bank,String program, String accountNumber, String firstName, String lastName, String email, String pin,double balance) {
        super(bank, accountNumber, firstName, lastName, email, pin);
        this.balance = balance;
        this.program = program;
    }
    public double getBalance(){
        return  this.balance;
    }
    public String getProgram(){
        return  this.program;
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
    private synchronized void adjustAccountBalance(double amount) {
        //Complete this method
        this.balance += amount;
        if (this.balance < 0.0) {
            this.balance = 0.0;
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
        return false;
    }

    /*
    Withdraw an amount of money from this savings account. Cannot proceed if account does not have sufficient balance.
    @Params: amount – Amount of money to be withdrawn.
     */
    @Override
    public boolean withdrawal(double amount) {
        //Complete this method
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

    @Override
    public void getAccountBalanceStatement() {
        //Complete this method
        String s = "Account Number: "+getAccountNumber()+"\n";
        s += "Program: " +getProgram() +"\n";
        s += "Name: " +getOwnerFullName() +"\n";
        s += "Account Balance: " + this.balance;
        System.out.println(s);
    }
    public String toString(){
        return "Name: " + this.getOwnerFullName();
    }
}
