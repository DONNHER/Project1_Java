package SavingsAccount;

import Account.AccountLauncher;
import Bank.BankLauncher;
import Main.Main;

import java.util.Scanner;

public class SavingsAccountLauncher extends AccountLauncher {

    private  BankLauncher bl;

    public SavingsAccountLauncher(BankLauncher bankLauncher) {
        super(bankLauncher);
        this.bl = bankLauncher;
    }

    /*
        Method that deals with all things about savings accounts. Mainly utilized for showing the main menu after Savings Account users log in to the application
         */
    public void savingsAccountInit() {
        while (true) {
            Main.showMenuHeader("Welcome to the Savings Account Portal!");
            Main.showMenu(51,2);
            Main.setOption();
            switch (Main.getOption()) {
                case 1:
                    depositProcess();
                    break;
                case 2:
                    withdrawProcess();
                    break;
                case 3:
                    fundTransferProcess();
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;  // Exit the method and break the loop
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    /*
    A method that deals with the deposit process transaction.
     */
    private void depositProcess(){

        // Complete this method
        bl.getFieldDouble().setFieldValue("Enter Amount: ");
        SavingsAccount account = getLoggedAccount();
        if (account.cashDeposit(bl.getFieldDouble().getFieldValue())) {
            System.out.println("Deposit successful. New balance: " + account.getBalance());
        } else {
            System.out.println("Deposit failed. Please try again.");
        }
    }

    /*
    A method that deals with the withdrawal process transaction.
     */
    private void withdrawProcess(){

        // Complete this method
        bl.getFieldDouble().setFieldValue("Enter Amount: ");
        SavingsAccount account = getLoggedAccount();
        if (account != null && account.withdrawal(bl.getFieldDouble().getFieldValue())) {
            System.out.println("Withdrawal successful. New balance: " + account.getBalance());
        } else {
            System.out.println("Withdrawal failed. Insufficient funds or invalid input.");
        }
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
