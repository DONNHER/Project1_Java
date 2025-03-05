package CreditAccount;

import Account.Account;
import Accounts.*;
import Bank.Bank;
import SavingsAccount.SavingsAccount;

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
    public double getLoanStatement(){
        //Complete this method
        return this.loan;
    }

    /*
    Checks if this credit account can do additional credit transactions if the amount to credit will not exceed the credit limit set by the bank associated to this Credit Account.
    @Param amountAdjustment – The amount of credit to be adjusted once the said transaction is processed.
    @Return - Flag if this account can continue with the credit transaction.
     */
    private boolean canCredit(double amountAdjustment){
        //Complete this method
        return false;
    }

    /*
    Adjust the owner’s current loan. Result of adjustment cannot be less than 0.
    @Param amountAdjustment – Amount to be adjusted to the loan of this credit account.
     */
    private void adjustLoanAmount(double amountAdjustment){
        //Complete this method
    }

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
            addNewTransaction(this.getAccountNumber(), Transaction.Transactions.Payment,"Credit Accounts cannot pay to other Credit Accounts as they do not hold account money balance.");
            // Throw an exception if it's a CreditAccount
            throw new IllegalAccountType("Credit Accounts cannot pay to other Credit Accounts as they do not hold account money balance.");
        }

        // If it's a valid SavingsAccount (or other type), perform the payment
        // Assuming there's a method to deposit the money in the account
        SavingsAccount payed = (SavingsAccount) account;
        boolean payment = payed.cashDeposit(amount);
        if (payment){
            addNewTransaction(this.getAccountNumber(), Transaction.Transactions.Payment,"Credit Accounts cannot pay to other Credit Accounts as they do not hold account money balance.");

        }
        return payed.cashDeposit(amount);
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
            addNewTransaction(this.getAccountNumber(), Transaction.Transactions.Recompense, "Recompense failed: Amount exceeds current loan balance. Current loan balance: " + loan);
            return false;
        }
        this.loan -= amount;
            addNewTransaction(this.getAccountNumber(), Transaction.Transactions.Recompense, "Recompense successful. Amount: " + amount + ". Remaining loan balance: " + loan);
        return true;
    }
    @Override
    public double loan_balance() {
        return this.loan;
    }

    public String toString(){
        //Complete this method
        return "Name: "+ this.getOwnerFullName() +"\nLoan statement: "+this.getLoanStatement() + "\n";
    }
}
