package Bank;

import Account.Account;
import CreditAccount.CreditAccount;
import Main.Field;

import java.util.ArrayList;
import java.util.Comparator;

public class Bank {
    // ID number of bank
    private final int ID;
    // name of the bank
    private String name;
    // passcode of the bank;
    private final String passcode;
    /*
    The amount of money each Savings Account registered to this bank can deposit at every transaction. Defaults to 50,000.0
     */
    private double depositLimit = 50000.0;
    /*
    The amount of money withdrawable / transferrable at once, restricted to every Savings Account registered to this bank. Defaults to 50,000.0
     */
    private double withdrawLimit = 50000.0;
    /*
    Limits the amount of credit or loan that all Credit Accounts, registered on this bank, can handle all at once. Defaults to 100,000.0
     */
    private double creditLimit = 100000.0;
    /*
    Processing fee added when some transaction is involved with another bank. Cannot be lower than 0.0. Defaults to 10.00
     */
    private double processingFee = 10.00;
    //Arraylist of BANK ACCOUNTS
    private ArrayList<Account> bankAccounts;

    public Bank(int ID, String name, String passcode, double depositLimit, double withdrawLimit, double creditLimit, double processingFee ,ArrayList<Account> bankAccounts){
        this.ID = ID;
        this.name = name;
        this.passcode = passcode;
        this.depositLimit = depositLimit;
        this.withdrawLimit = withdrawLimit;
        this.creditLimit = creditLimit;
        this.bankAccounts = new ArrayList<>(bankAccounts);
    }
    //Getters
    public int getID(){
        return this.ID;
    }
    public String getName(){
        return this.name;
    }
    public String getPasscode(){
        return this.passcode;
    }
    public double getDepositLimit(){
        return this.depositLimit;
    }
    public double getWithdrawLimit(){
        return this.withdrawLimit;
    }
    public double getCreditLimit(){
        return this.creditLimit;
    }
    public double getProcessingFee(){
        return this.processingFee;
    }
    public ArrayList<Account> getBankAccounts() {
        return new ArrayList<>(bankAccounts);
    }

    //Setters
    public void setName(String name){
        this.name = name;
    }
    public void setDepositLimit(double newLimit){
        this.depositLimit = newLimit;
    }
    public void setWithdrawLimit(double newLimit){
        this.withdrawLimit = newLimit;
    }
    public void setCreditLimit(double newLimit){
        this.creditLimit = newLimit;
    }
    public void setProcessingFee(double newLimit){
        this.processingFee = newLimit;
    }
    public void setBankAccounts(ArrayList<Account> newBankAccounts){
        this.bankAccounts = new ArrayList<>(newBankAccounts);
    }

    /*
        A comparator that compares if two bank objects are the same.
         */
    class BankComparator implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            //Complete this method
            return 0;
        }
    }

    /*
    A comparator that compares if two bank objects have the same bank id.
     */
    class BankIdComparator implements Comparator{
        @Override
        public int compare(Object o1, Object o2) {
            //Complete this method
            return 0;
        }
    }

    /*
    A comparator that compares if two bank objects share the same set of credentials.
     */
    class BankCredetialsComparator implements Comparator{
        @Override
        public int compare(Object o1, Object o2) {
            //Complete this method
            return 0;
        }
    }

    /*
    Show accounts based on option.
    @Params accountType – Type of account to be shown
     */
    public <T> void  showAccount( Class<T> accountType){
        //Complete this code
    }

    /*
    Get the Account object (if it exists) from a given bank.
    @Params bank - Bank to search from.
    @Params accountNum – Account number of target account.
     */
    public Account getBankAccount(Bank bank, String accountNum){
        //Complete this code
        return null;
    }

    /*
    Handles the processing of inputting the basic information of the account.
    @Returns Array list of Field objects, which are the basic account information of the account user
     */
    public ArrayList<Field<String, ?>> createNewAccount(){
        //Complete this code
        return null;
    }

    /*
    Create a new credit account. Utilizes the createNewAccount() method.
    @Returns New credit account.
     */
    public CreditAccount createCreditAccount(){
        //Complete this method
        return null;
    }

    /*
    Adds a new account to this bank, if the account number of the new account does not exist inside the bank.
    @Params account – Account object to be added into this bank.
     */
    public void addNewAccount(Account account){
        //Complete this method
    }

    /*
    Checks if an account object exists into a given bank based on some account number.
    @Params bank – Bank to check if account exists.
    @Params accountNum – Account number of target account to check.
     */
    public boolean accountExist(Bank bank, String accountNum){
        //Complete this method
        return false;
    }

    /*
    Return string
     */
    public String toString(){
        //Complete this method
        return null;
    }
}