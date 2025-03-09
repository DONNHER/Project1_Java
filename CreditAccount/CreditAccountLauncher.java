package CreditAccount;

import Account.Account;
import Account.AccountLauncher;
import Accounts.IllegalAccountType;
import Accounts.Transaction;
import Bank.Bank;
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
                    getCreditProcess();
                    break;
                case 5:
                    for (Transaction t: account.getTransactionsInfo()){
                        System.out.print("Description: " + t.description +"\n");
                    }
                    break;
                case 6:
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
        Main.showMenuHeader("Payment Menu");
        Main.showMenu(6);
        Main.setOption();
        if (Main.getOption() == 1) {
            getLoggedAccount().getBank().getIdField().setFieldValue("Enter Recipient's Account Number: ");
            Account recipient = getLoggedAccount().getBank().getBankAccount(getLoggedAccount().getBank(), getLoggedAccount().getBank().getIdField().getFieldValue());
            if(recipient != null) {
                bl.getFieldDouble().setFieldValue("Enter Amount: ");
                if (this.getLoggedAccount().pay(recipient, bl.getFieldDouble().getFieldValue())) {
                    return;
                }
                System.out.println("Payment failed. Please check the amount and try again.");
            }
            System.out.println("Recipient's Account Number not found!");
        }else if(Main.getOption() == 2){
            bl.showBanksMenu();
            Main.showMenuHeader("Options here");
            bl.getIdField().setFieldValue("Enter Bank ID: ");
            Bank b1 = new Bank(bl.getIdField().getFieldValue(), "", "");
            Bank search1 = bl.getBank(bl.getBankIdComparator(), b1);
            if (search1 != null) {
                getAccountNum().setFieldValue("Enter Recipient's Account Number: ");
                Account recipientAccount1 = getLoggedAccount().getBank().getBankAccount(search1,getAccountNum().getFieldValue());
                if(recipientAccount1 != null) {
                    bl.getFieldDouble().setFieldValue("Enter Amount: ");
                    if (this.getLoggedAccount().pay(getLoggedAccount(), bl.getFieldDouble().getFieldValue())) {
                        System.out.println("Payment successful! Your remaining loan balance is: " + this.getLoggedAccount().getLoan());
                        return;
                    }
                    System.out.println("Payment failed. Please check the amount and try again.");
                }
                System.out.println("Recipient's Account Number not found!");
            }
        }
    }

    /*
    Method that is utilized to process the credit compensation transaction
     */
    private void credRecompenseProcess(){
        //Complete this method
        bl.getFieldDouble().setFieldValue("Enter Amount: ");
        if (this.getLoggedAccount().recompense(bl.getFieldDouble().getFieldValue())){
            return;
        }
        System.out.println("Recompense failed. Please check the amount and try again.");
    }

    /*
    Get the Credit Account instance of the currently logged account
     */

    private void getCreditProcess(){
        bl.getFieldDouble().setFieldValue("Enter Amount: ");
        getLoggedAccount().getCredit(bl.getFieldDouble().getFieldValue());
    }

    protected CreditAccount getLoggedAccount(){
        //Complete this method
        return this.account;
    }
}
