package Accounts;

import Bank.Bank;

import java.util.ArrayList;

/*
An abstract account class that has comparators to compare itself with different account objects.
 */

public abstract class Account extends Bank{
    // A constant bank object associated to this account.
    Bank bank = new Bank();

    // Account number of this account object. Cannot be modified once set
    final String accountNumber = "" ;

    // Transactions refer to the transaction logs recorded in this account.
    // A successful withdrawal; deposit; payment; transfer.
    ArrayList<Transaction>transactions = new ArrayList<>();

    /*
     Add new transaction log to this account.
     @param account - Account number of source account that triggered this transaction.
     @param type - Type of transaction triggered.
     @param description - Description of the Transaction.
     */
    public abstract void addNewTransaction();

    /*
    Get all information for every transaction that has been logged into this account.
     */
    public abstract String getTransactionsInfo();

}
