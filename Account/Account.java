package Account;
import Accounts.*;
import Bank.Bank;

import java.util.ArrayList;

/*
An abstract account class that has comparators to compare itself with different account objects.
 */

public abstract class Account {
    // A constant bank object associated to this account.
    private Bank bank;
    // Account number of this account object. Cannot be modified once set
    private final String accountNumber ;
    // Account OWNER FIRSTNAME of this account object.
    private String ownerFirstName ;
    // Account OWNER LASTNAME of this account object. Cannot be modified once set
    private String ownerLastName ;
    // Account OWNER EMAIL of this account object. Cannot be modified once set
    private String ownerEmail ;
    //Password of owner
    private String pin;
    // Transactions refer to the transaction logs recorded in this account.
    // A successful withdrawal; deposit; payment; transfer.
    private ArrayList<Transaction>transactions = new ArrayList<>();
    //Utilize when updating
    private String isNew = "New";

    // Constructor of Abstract class
    public Account( Bank bank,String accountNumber,String firstName,String lastName, String email,String pin){
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.ownerFirstName = firstName;
        this.ownerLastName = lastName;
        this.ownerEmail = email;
        this.pin = pin;
    }

    //Getters

    public String getAccountNumber() {
        return accountNumber;
    }
    public String getOwnerLastName(){
        return this.ownerLastName;
    }
    public String getOwnerFirstName(){
        return this.ownerFirstName;
    }
    public Bank getBank() {
        return bank;
    }
    public String getPin(){return this.pin;}
    public String getOwnerEmail(){
        return this.ownerEmail;
    }
    public String getIsNew(){
        return this.isNew;
    }

    //Setters
    public void setBank(Bank bank) {
        this.bank = bank;
    }
    public void setOwnerFirstName(String fname){
        this.ownerFirstName = fname;
    }
    public void setOwnerLastName(String lname){
        this.ownerLastName = lname;
    }
    public void setOwnerEmail(String email){
        this.ownerEmail = email;
    }
    public void setIsNew(String newState){
        this.isNew = newState;
    }

    /*
    @Return Fullname of owner
     */
    public String getOwnerFullName(){
        return this.ownerFirstName +", "+ this.ownerLastName;
    }

    /*
     Add new transaction log to this account.
     @param account - Account number of source account that triggered this transaction.
     @param type - Type of transaction triggered.
     @param description - Description of the Transaction.
     */
    public void addNewTransaction(String account, Transaction.Transactions type, String description){
        Transaction newTransaction = new Transaction(account,type,description);
        transactions.add(newTransaction);
    }

    /*
    Get all information for every transaction that has been logged into this account.
     */
    public  ArrayList<Transaction> getTransactionsInfo(){
        return transactions;
    }

    public String toString(){
        //Complete this method
        return getOwnerFullName();
    }
}
