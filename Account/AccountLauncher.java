package Account;
import Bank.*;

import CreditAccount.*;
import Main.*;
import SavingsAccount.*;

public class AccountLauncher {
    private BankLauncher bankLauncher;
    Field<Integer,Integer> bankid = new Field<Integer, Integer>("Selected user ID",Integer.class,0,new Field.IntegerFieldValidator());
    Field<String,String> password = new Field<String, String>("User password",String.class,"0",new Field.StringFieldValidator());
    Field<String,String> accountNum = new Field<String, String>("User password",String.class,"0",new Field.StringFieldValidator());

    public AccountLauncher(BankLauncher bankLauncher){
        this.bankLauncher = bankLauncher;
    }
    /*
    Account object of logged account user
     */
    private Account loggedAccount = null;

    /*
    Selected associated bank when attempting to log-in in the account module
     */
    private Bank assocBank = null;

    //Getters
    protected Account getLoggedAccount() {
        return this.loggedAccount;
    }

    public Bank getAssocBank() {
        return this.assocBank;
    }

    //Setters
    public void setLoggedAccount(Account newAccount) {
        this.loggedAccount = newAccount;
    }

    public void setAssocBank() {
        this.assocBank = this.selectBank();
    }

    /*
    Verifies if some account is currently logged in.
     */
    public boolean isLoggedIn() {
        //Complete this method
        return this.loggedAccount != null;
    }

    /*
    Login an account. Bank must be selected first before logging in. Account existence will depend
    on the selected bank.
     */
    public void accountLogin() {
        //Complete this method
        assocBank = selectBank();
        if (assocBank!= null) {
                Main.showMenuHeader("Options here");
                accountNum.setFieldValue("Enter account number: ");
                password.setFieldValue("Enter 4-digit PIN: ");
                Account account = checkCredentials(accountNum.getFieldValue(), password.getFieldValue());
                if (account != null) {
                    setLoggedSession(account);
                    System.out.println("Login successful! Welcome, " + loggedAccount.getOwnerFullName() + ".");
                    if (getLoggedAccount() instanceof CreditAccount){
                        new CreditAccountLauncher(bankLauncher,this.loggedAccount).creditAccountInit();
                    }else if(getLoggedAccount() instanceof SavingsAccount){
                        new SavingsAccountLauncher(bankLauncher,this.loggedAccount).savingsAccountInit();
                    }
                } else {
                    System.out.println("Login failed. Invalid credentials.");
                }
            }else {
            System.out.println("Login failed. Invalid credentials.");
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
        Main.showMenuHeader("Select Bank");
        bankid.setFieldValue("Enter Bank ID: ");
        Bank bank = new Bank(bankid.getFieldValue(),"","");
        return bankLauncher.getBank(bankLauncher.getBankIdComparator(),bank);
    }

    /*
    Create a login session based on the logged user account.
    Params:
        account – Account that has successfully logged in.
     */
    private void setLoggedSession(Account account){
        //Complete this method
        this.loggedAccount = account;
    }

    /*
    Destroy the log session of the previously logged user account
     */
    private void destroyLogSession(){
        //Complete this method
        this.loggedAccount = null;
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
        Account search =bankLauncher.findAccount(accountNum); ;
        if (search != null){
            if (search.getPin().equals(pin)){
                return search;
            }
        }
        return null;
    }

}
