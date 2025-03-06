package CreditAccount;

import Account.AccountLauncher;
import Bank.BankLauncher;
import Main.Main;

public class CreditAccountLauncher extends AccountLauncher {
    private  BankLauncher bl;

    public CreditAccountLauncher(BankLauncher bankLauncher) {
        super(bankLauncher);
        this.bl = bankLauncher;
    }

    /*
        Method that deals with all things about credit accounts. Mainly utilized for showing the main
        menu after Credit Account users log in to the application.
         */
    public void creditAccountInit(){
        //Complete this method
        while (true) {
            Main.showMenuHeader("Welcome to the Credit Account Portal!");
            Main.showMenu(41,2);
            Main.setOption();
            switch (Main.getOption()) {
                case 1:
                    getLoggedAccount();
                    break;
                case 2:
                    creditAccountProcess();
                    break;
                case 3:
                    credRecompenseProcess();
                    break;
                case 4:
                    getLoggedAccount().getTransactionsInfo();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;  // Exit the method and break the loop
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
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
