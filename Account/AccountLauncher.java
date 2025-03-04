package Account;
import Bank.Bank;
import Bank.BankLauncher;
import Main.Field;

public class AccountLauncher {
    Field <Integer,Integer> bankid = new Field<Integer, Integer>("Selected user ID",Integer.class,0,new Field.IntegerFieldValidator());
    Field <String,String> password = new Field<String, String>("User password",String.class,"0",new Field.StringFieldValidator());

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
    public void setAssocBank(){
        this.assocBank = this.selectBank();
    }

    /*
    Verifies if some account is currently logged in.
     */
    private boolean isLoggedIn(){
        //Complete this method
        return this.loggedAccount != null;
    }

    /*
    Login an account. Bank must be selected first before logging in. Account existence will depend
    on the selected bank.
     */
    public void accountLogin(){
        //Complete this method
        this.bankid.setFieldValue("Enter bank ID: ");
        this.bankid.getFieldValue();
    }

    /*
    Bank selection screen before the user is prompted to login. User is prompted for the Bank ID
    with corresponding bank name.
    Returns:
        Bank object based on selected ID
     */
    private Bank selectBank(){
        //Complete this method

        this.bankid.setFieldValue("Enter Bank ID: ");
        for (Bank bank: new BankLauncher().getBanks()) {
            if (this.bankid.getFieldValue() == bank.getID() ){
                return bank;
            }
        }
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
