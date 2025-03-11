package CreditAccount;

import Account.Account;
import Accounts.*;
import Bank.Bank;
import SavingsAccount.SavingsAccount;

import java.time.LocalDateTime;

public class CreditAccount extends Account implements Payment, Recompense {
    private double loan = 0.0;

    public CreditAccount(Bank bank, String accountNumber, String firstName, String lastName, String email,String pin,double loan) {
        super(bank, accountNumber, firstName, lastName, email,pin);
        this.loan = loan;
    }

    // Getters
    public double getLoan(){
        return this.loan;
    }

    //Setters
    public void setLoan(Double newLoan){
        this.loan = newLoan;
    }

    /*
        Loan statement of this credit account.
        @Returns - String loan statement.
         */
    public void getLoanStatement(){
        String s = "Account Number: "+getAccountNumber()+"\n";
        s += "Name: " +getOwnerFullName() +"\n";
        s += "Loan Statement: ₱" +this.loan;
        System.out.println(s);
    }

    /*
    Checks if this credit account can do additional credit transactions if the amount to credit will not exceed the credit limit set by the bank associated to this Credit Account.
    @Param amountAdjustment – The amount of credit to be adjusted once the said transaction is processed.
    @Return - Flag if this account can continue with the credit transaction.
     */
    private boolean canCredit(double amountAdjustment){
        //Complete this method
        if(amountAdjustment > getBank().getCreditLimit()){
            return false;
        }
        adjustLoanAmount(amountAdjustment);
        return true;
    }
    /*
    Adjust the owner’s current loan. Result of adjustment cannot be less than 0.
    @Param amountAdjustment – Amount to be adjusted to the loan of this credit account.
     */
    private void adjustLoanAmount(double amountAdjustment){
        //Complete this method
        this.loan += amountAdjustment;
        if (loan < 0){
            this.loan = 0.0;
        }
    }
//ISSUE HERE
    /*
    Pay an amount of money to a selected account. Such an account cannot be of type CreditAccount.
    @Param account – Target account to pay money into.
    @Return - True if pay transaction was successful. False otherwise.
    @Throws IllegalAccountType – Credit Accounts cannot pay to other Credit Accounts as they do not hold account money balance but rather, credits. As such, in this scenario, only Savings Accounts can receive payment from Credit Accounts.
     */
    @Override
    public synchronized boolean pay(Account account, double amount) throws IllegalAccountType {
        //Complete this method
        if (account instanceof CreditAccount) {
            // Throw an exception if it's a CreditAccount
            throw new IllegalAccountType("Credit Accounts cannot pay to other Credit Accounts as they do not hold account money balance.");
        }
        if (canCredit(amount)) {
            // If it's a valid SavingsAccount (or other type), perform the payment
            // Assuming there's a method to deposit the money in the account
            SavingsAccount payed = (SavingsAccount) account;
            return payed.cashDeposit(amount);
        }
        return false;
    }
    /*
    Recompense some amount of money to the bank and reduce the value of loan recorded in this account. Must not be greater than the current credit.
    @Param amount – Amount of money to be recompensed.
    @Return - Flag if compensation was successful.
     */
    @Override
    public synchronized boolean recompense(double amount) {
        //Complete this method
        if (amount > this.loan) {
            return false;
        }
        adjustLoanAmount(-amount);
        return true;
    }

    public String toString(){
        //Complete this method
        return "Name: "+ this.getOwnerFullName()+"\nLoan: $"+this.loan+"\n";
    }
}
