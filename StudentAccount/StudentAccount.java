package StudentAccount;

import Account.Account;
import Accounts.Deposit;
import Accounts.FundTransfer;
import Accounts.IllegalAccountType;
import Accounts.Withdrawal;
import Bank.Bank;

public class StudentAccount extends Account implements Deposit, Withdrawal, FundTransfer {
    private double balance = 0;
    public StudentAccount(Bank bank, String accountNumber, String firstName, String lastName, String email, String pin,double balance) {
        super(bank, accountNumber, firstName, lastName, email, pin);
        this.balance = balance;
    }

    @Override
    public double loan_balance() {
        return this.balance;
    }

    @Override
    public boolean cashDeposit(double amount) {
        return false;
    }

    @Override
    public boolean transfer(Bank bank, Account account, double amount) throws IllegalAccountType {
        return false;
    }

    @Override
    public boolean transfer(Account account, double amount) throws IllegalAccountType {
        return false;
    }

    @Override
    public boolean withdrawal(double amount) {
        return false;
    }

    @Override
    public void getAccountBalanceStatement() {

    }
    public String toString(){
        return "Name: " + this.getOwnerFullName();
    }
}
