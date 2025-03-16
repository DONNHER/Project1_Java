package StudentAccount;

import Account.Account;
import Account.AccountLauncher;
import Accounts.Transaction;
import Bank.BankLauncher;
import Main.Main;

import java.time.LocalDateTime;

public class StudentAccountLauncher extends AccountLauncher {
    private final   BankLauncher bl;
    private final StudentAccount account;
    public StudentAccountLauncher(BankLauncher bankLauncher,Account logged) {
        super(bankLauncher);
        this.bl = bankLauncher;
        this.account = (StudentAccount) logged;
    }

    /*
   Method that deals with all things about Student accounts. Mainly utilized for showing the main menu after Savings Account users log in to the application
    */
    public void studentAccountInit(){
        while (true) {
            Main.showMenuHeader("Welcome to the Student Account Portal!");
            Main.showMenu(61,2);
            Main.setOption();
            switch (Main.getOption()) {
                case 1:
                    getLoggedAccount().getAccountBalanceStatement();
                    break;
                case 2:
                    depositProcess();
                    break;
                case 3:
                    withdrawProcess();
                    break;
                case 6:
                    System.out.println(getLoggedAccount().getTransactionsInfo());
                    break;
                case 7:
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
        if(getLoggedAccount().cashDeposit(bl.getFieldDouble().getFieldValue())){
            getLoggedAccount().addNewTransaction(getLoggedAccount().getAccountNumber(),Transaction.Transactions.Deposit, LocalDateTime.now()+" Deposit Successful: $"+bl.getFieldDouble().getFieldValue());
            System.out.println(LocalDateTime.now()+ " Deposit Successful: $"+bl.getFieldDouble().getFieldValue());
        }
        System.out.println("Deposit Failed. Amount exceeds bank deposit limit.");
    }

    /*
    A method that deals with the withdrawal process transaction.
     */
    private void withdrawProcess(){

        // Complete this method
        bl.getFieldDouble().setFieldValue("Enter Amount: ");
        if(getLoggedAccount().withdrawal(bl.getFieldDouble().getFieldValue())){
            getLoggedAccount().addNewTransaction(getLoggedAccount().getAccountNumber(), Transaction.Transactions.Withdraw, LocalDateTime.now()+" Withdraw Successful: $" + bl.getFieldDouble().getFieldValue());
            System.out.println( LocalDateTime.now()+" Withdraw Successful: $" + bl.getFieldDouble().getFieldValue());
            return;
        }
        System.out.println("Withdraw Failed: The amount to be withdraw exceeds to Bank withdrawal limit.");
    }

    /*
    Get the Savings Account instance of the currently logged account.
     */
    @Override
    protected StudentAccount getLoggedAccount() {
        //Complete this method
        return this.account;
    }
}

