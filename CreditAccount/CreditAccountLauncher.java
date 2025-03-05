package CreditAccount;

import Account.AccountLauncher;
import Bank.BankLauncher;

public class CreditAccountLauncher extends AccountLauncher {

    public CreditAccountLauncher(BankLauncher bankLauncher) {
        super(bankLauncher);
    }

    /*
        Method that deals with all things about credit accounts. Mainly utilized for showing the main
        menu after Credit Account users log in to the application.
         */
    public void creditAccountInit(){
        //Complete this method
    }

    /*
    Method that is utilized to process the credit payment transaction.
     */
    private void creditAccountProcess(){
        //Complete this method
    }

    /*
    Method that is utilized to process the credit compensation transaction
     */
    private void credRecompenseProcess(){
        //Complete this method
    }

    /*
    Get the Credit Account instance of the currently logged account
     */
    public CreditAccount getLoggedAccount(){
        //Complete this method
        return null;
    }
}
