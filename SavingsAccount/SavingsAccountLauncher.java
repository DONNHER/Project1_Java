package SavingsAccount;

import Account.AccountLauncher;
import Bank.BankLauncher;

public class SavingsAccountLauncher extends AccountLauncher {

    public SavingsAccountLauncher(BankLauncher bankLauncher) {
        super(bankLauncher);
    }

    /*
        Method that deals with all things about savings accounts. Mainly utilized for showing the main menu after Savings Account users log in to the application
         */
    public void savingsAccountInit(){
        //Complete this method
        while (true){

        }
    }

    /*
    A method that deals with the deposit process transaction.
     */
    private void depositProcess(){
        //Complete this method
    }

    /*
    A method that deals with the withdrawal process transaction.
     */
    private void withdrawProcess(){
        //Complete this method
    }

    /*
    A method that deals with the fund transfer process transaction.
     */
    private void fundTransferProcess(){
        //Complete this method
    }

    /*
    Get the Savings Account instance of the currently logged account.
     */
    @Override
    public SavingsAccount getLoggedAccount() {
        //Complete this method
        return this.getLoggedAccount();
    }
}
