package BankLauncher;

import Account.Account;
import Bank.Bank;
import java.util.ArrayList;
import java.util.Comparator;

public class BankLauncher {
    /*
    List of banks currently registered in this session.
     */
    private ArrayList<Bank> banks = new ArrayList<Bank>();

    /*
    The Bank object currently logged in. Null by default, or when no bank is currently logged in.
     */
    private Bank loggedBank = null;

    //Getters
    public ArrayList<Bank> getBanks() {
        return banks;
    }
    public Bank getLoggedBank() {
        return loggedBank;
    }

    //Setters
    public void setBanks(ArrayList<Bank> banks) {
        this.banks = banks;
    }
    public void setLoggedBank(Bank loggedBank) {
        this.loggedBank = loggedBank;
    }


    /*
    Return Flag
     */
    public boolean isLogged(){
        //Complete this method
        return false;
    }

    /*
    Bank interaction initialization. Utilized only when logged in.
     */
    public void bankInit(){
        //Complete this method
    }

    /*
    Show the accounts registered to this bank. Must prompt the user to select which type of
    accounts to show: (1) Credit Accounts, (2) Savings Accounts, and (3) All.
     */
    private void showAccounts(){
        //Complete this method
    }

    /*
    Bank interaction when creating new accounts for the currently logged in bank.
     */
    private void newAccounts(){
        //Complete this method
    }

    /*
    Bank interaction when attempting to login to the banking module using a bank user’s credentials.
     */
    public void bankLogin(){
        //Complete this method
    }

    /*
    Creates a new login session for the logged in bank user. Sets up a new value for the loggedBank field.
    Params:
        b – Bank user that successfully logged in.
     */
    private void setLoggedSession(Bank b){
        //Complete this method
    }

    /*
    Destroys the login session for the current user.
     */
    private void logout(){
        //Complete this method
    }

    /*
    Creates a new bank record. Utilized separately from the rest of the methods of this class.
    Throws:
        NumberFormatException – May happen when inputting deposit, withdraw, and credit limit,
        and processing fee.
     */
    public void createNewBank(){
        //Complete this method
    }

    /*
    Output a menu of all registered or created banks in this session
     */
    public void showBanksMenu(){
        //Complete this method
    }

    /*
    Adds new bank to array list of banks.
    Params:
        b – Bank object to be added.
     */
    private void addBank(Bank b){
        //Complete this method
    }

    /*
    Checks if a bank exists based on some criteria.
    Params:
        bankComparator – Criteria for searching.
        bank – Bank object to be compared.
    Returns:
        Bank object if it passes the criteria. Null if none.
     */
    public <T> Bank getBank(Comparator<T> comparator, Bank bank ){
        //Complete this method
        return null;
    }

    /*
    Finds the Account object based on some account number on all registered banks.
    Params:
        accountNumber – Account number of target Account.
    Returns:
        Account object if it exists. Null if not found.
     */
    public Account findAccount(String accountNum){
        //Complete this method
        return null;
    }

    /*
    Get the number of currently registered banks.
     */
    public int bankSize(){
        //Complete this method
        return -1;
    }
}
