package CreditAccount;

import Account.Account;
import Account.AccountLauncher;
import Accounts.IllegalAccountType;
import Accounts.Transaction;
import Bank.BankLauncher;
import Main.Main;

public class CreditAccountLauncher extends AccountLauncher {
    private  BankLauncher bl;
    private CreditAccount account;

    public CreditAccountLauncher(BankLauncher bankLauncher, Account logged) {
        super(bankLauncher);
        this.bl = bankLauncher;
        this.account = (CreditAccount) logged;
    }

    /*
        Method that deals with all things about credit accounts. Mainly utilized for showing the main
        menu after Credit Account users log in to the application.
         */
    public void creditAccountInit() throws IllegalAccountType {
        //Complete this method
        while (true) {
            Main.showMenuHeader("Welcome to the Credit Account Portal!");
            Main.showMenu(41,2);
            Main.setOption();
            switch (Main.getOption()) {
                case 1:
                    this.account.getLoanStatement();
                    break;
                case 2:
                    creditAccountProcess();
                    break;
                case 3:
                    credRecompenseProcess();
                    break;
                case 4:
                    for (Transaction t: account.getTransactionsInfo()){
                        System.out.print("Type: "+t.transactionType+"\nDescription: " + t.description);
                    }
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
    private void creditAccountProcess() throws IllegalAccountType {
        //Complete this method
        bl.getFieldDouble().setFieldValue("Enter The Account Number: ");
        if (this.getLoggedAccount().pay(getLoggedAccount(),bl.getFieldDouble().getFieldValue())) {
            System.out.println("Payment successful! Your remaining loan balance is: " + this.getLoggedAccount().getLoan());
        } else {
            System.out.println("Payment failed. Please check the amount and try again.");
        }
    }

    /*
    Method that is utilized to process the credit compensation transaction
     */
    private void credRecompenseProcess(){
        //Complete this method
        bl.getFieldDouble().setFieldValue("Enter the amount you want to recompense: ");
        if (this.getLoggedAccount() != null && this.getLoggedAccount().recompense(bl.getFieldDouble().getFieldValue())){
            System.out.println("Recompense successful! Your updated loan balance is: " + this.getLoggedAccount().getLoan());
        } else {
            System.out.println("Recompense failed. Please check the amount and try again.");
        }
    }

    /*
    Get the Credit Account instance of the currently logged account
     */
    protected CreditAccount getLoggedAccount(){
        //Complete this method
        return this.account;
    }
}
