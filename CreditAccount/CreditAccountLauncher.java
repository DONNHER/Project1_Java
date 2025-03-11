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
                    for (Transaction t: account.getTransactionsInfo()){
                        System.out.print("Description: " + t.description +"\n");
                    }
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
    private void creditAccountProcess() throws IllegalAccountType {
        //Complete this method
        try {
            Main.showMenuHeader("Payment Menu");
            getAccountNum().setFieldValue("Enter Recipient's Account Number: ");
            Account recipientAccount2 = bl.findAccount(getAccountNum().getFieldValue());
            if (recipientAccount2 != null) {
                bl.getFieldDouble().setFieldValue("Enter Amount: ");
                if(getLoggedAccount().pay(recipientAccount2, bl.getFieldDouble().getFieldValue())){
                    getLoggedAccount().addNewTransaction(getLoggedAccount().getAccountNumber(), Transaction.Transactions.Payment, "Payment Successful: $"+bl.getFieldDouble().getFieldValue());
                    System.out.println("Payment Successful: $"+bl.getFieldDouble().getFieldValue());
                    return;
                }
                System.out.println("Payment Unsuccessful: $"+bl.getFieldDouble().getFieldValue()+" exceeds bank credit limit.");
            }
            System.out.println("Recipient's Account Number not found!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*
    Method that is utilized to process the credit compensation transaction
     */
    private void credRecompenseProcess(){
        //Complete this method
        bl.getFieldDouble().setFieldValue("Enter Amount: ");
        if (this.getLoggedAccount().recompense(bl.getFieldDouble().getFieldValue())){
            getLoggedAccount().addNewTransaction(getLoggedAccount().getAccountNumber(), Transaction.Transactions.Recompense, "Recompense Successful: $"+bl.getFieldDouble().getFieldValue());
            return;
        }
        System.out.println("Recompense failed. Please check the amount and try again.");
    }

    protected CreditAccount getLoggedAccount(){
        //Complete this method
        return this.account;
    }
}
