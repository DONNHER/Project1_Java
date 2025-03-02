package AccountLauncher;

import Account.Account;
import Bank.Bank;
import Main.Field;

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
        return loggedAccount != null;
    }

    /*
    Login an account. Bank must be selected first before logging in. Account existence will depend
    on the selected bank.
     */
    public void accountLogin(){
        //Complete this method
        if (assocBank == null) {
            System.out.println("Please select a bank first.");
            assocBank = selectBank();
        }

        if (assocBank == null) {
            System.out.println("Bank selection failed. Cannot proceed with login.");
            return;
        }

        Field<String, String> accountNumberField = new Field<String, String>("Account Number", String.class, "", new Field.StringFieldValidator());
        Field<String, String> pinField = new Field<String,  String>("PIN", String.class, "", new Field.StringFieldValidator());

        accountNumberField.setFieldValue("Enter Account Number: ");
        pinField.setFieldValue("Enter 4-digit PIN: ");

        Account account = checkCredentials(accountNumberField.getFieldValue(), pinField.getFieldValue());
        if (account != null) {
            setLoggedAccount(account);
            setLoggedSession();
            System.out.println("Login successful! Welcome, " + loggedAccount.getOwnerFullName() + ".");
        } else {
            System.out.println("Invalid credentials. Login failed. ");

        }
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
        System.out.println("Session created for " + loggedAccount.getOwnerFullName());
    }

    /*
    Destroy the log session of the previously logged user account
     */
    private void destroyLogSession(){
        //Complete this method
        if (loggedAccount != null) {
            System.out.println("Session ended for " + loggedAccount.getOwnerFullName());
            loggedAccount = null;
        } else {
            System.out.println("No active session to destroy");
        }
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
