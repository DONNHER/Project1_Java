package AccountLauncher;

import Account.Account;
import Bank.Bank;

public class AccountLauncher {

    /*
    Account object of logged account user
     */
    private Account loggedAccount = null;

    /*
    Selected associated bank when attempting to log-in in the account module
     */
    private Bank assocBank = null;

    //Getters
    protected Account getLoggedAccount(){
        return this.loggedAccount;
    }
    public Bank getAssocBank(){
        return this.assocBank;
    }

    //Setters
    public void setLoggedAccount(Account newAccount){
        this.loggedAccount = newAccount;
    }
    public void setAssocBank(Bank newAssoc){
        this.assocBank = newAssoc;
    }

    /*
    Verifies if some account is currently logged in.
     */
    private boolean isLoggedIn(){
        //Complete this method
        return false;
    }

    /*
    Login an account. Bank must be selected first before logging in. Account existence will depend
    on the selected bank.
     */
    public void accountLogin(){
        //Complete this method
    }

    /*
    Bank selection screen before the user is prompted to login. User is prompted for the Bank ID
    with corresponding bank name.
    Returns:
        Bank object based on selected ID
     */
    private Bank selectBank(){
        //Complete this method
        return null;
    }

    /*
    Create a login session based on the logged user account.
    Params:
        account – Account that has successfully logged in.
     */
    private void setLoggedSession(){
        //Complete this method
    }

    /*
    Destroy the log session of the previously logged user account
     */
    private void destroyLogSession(){
        //Complete this method
    }

    /*
    Checks inputted credentials during account login.
    Params:
        accountNum – Account number.
        pin – 4-digit pin.
    Returns:
        Account object if it passes verification. Null if not.
     */
    public Account checkCredentials(String accountNum, String pin){
        //Complete this method
        return null;
    }

}
