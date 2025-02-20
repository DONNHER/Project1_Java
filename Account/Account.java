package Account;

import Accounts.*;
import Bank.Bank;

import java.util.ArrayList;

/*
An abstract account class that has comparators to compare itself with different account objects.
 */

public abstract class Account {
    // A constant bank object associated to this account.
    protected Bank bank;
    // Account number of this account object. Cannot be modified once set
    protected final String accountNumber ;
    // Account OWNER FIRSTNAME of this account object.
    protected String ownerFirstName ;
    // Account OWNER LASTNAME of this account object. Cannot be modified once set
    protected String ownerLastName ;
    // Account OWNER EMAIL of this account object. Cannot be modified once set
    protected String ownerEmail ;
    // Transactions refer to the transaction logs recorded in this account.
    // A successful withdrawal; deposit; payment; transfer.
    protected ArrayList<Transaction>transactions;

    // Constructor of Abstract class
    public Account(Bank bank, String accountNumber, ArrayList<Transaction> transactions,String firstName,String lastName, String email){
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.transactions = transactions;
        this.ownerFirstName = firstName;
        this.ownerLastName = lastName;
        this.ownerEmail = email;
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
    }

    /*
    Get all information for every transaction that has been logged into this account.
     */
    public  ArrayList<Transaction> getTransactionsInfo(){
        return transactions;
    }
    public String toString(){
        //Complete this method
        return "";
    }
}
