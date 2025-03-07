package SavingsAccount;

import Account.Account;
import Account.AccountLauncher;
import Accounts.IllegalAccountType;
import Accounts.Transaction;
import Bank.Bank;
import Bank.BankLauncher;
import Main.Main;

public class SavingsAccountLauncher extends AccountLauncher {

    private  BankLauncher bl;
    private SavingsAccount account;

    public SavingsAccountLauncher(BankLauncher bankLauncher, Account logged) {
        super(bankLauncher);
        this.bl = bankLauncher;
        this.account = (SavingsAccount) logged;
    }

    /*
        Method that deals with all things about savings accounts. Mainly utilized for showing the main menu after Savings Account users log in to the application
         */
    public void savingsAccountInit() throws IllegalAccountType {
        while (true) {
            Main.showMenuHeader("Welcome to the Savings Account Portal!");
            Main.showMenu(51,2);
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
                case 4:
                    fundTransferProcess();
                    break;
                case 5:
                     for (Transaction t: account.getTransactionsInfo()){
                         System.out.print("Type: "+t.transactionType+"\nDescription: " + t.description);
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
    A method that deals with the deposit process transaction.
     */
    private void depositProcess(){

        // Complete this method
        bl.getFieldDouble().setFieldValue("Enter Amount: ");
        if (this.account.cashDeposit(bl.getFieldDouble().getFieldValue())) {
            System.out.println("Deposit successful. New balance: " + this.account.getBalance());
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
        if (this.account != null && this.account.withdrawal(bl.getFieldDouble().getFieldValue())) {
            System.out.println("Withdrawal successful. New balance: " + this.account.getBalance());
        } else {
            System.out.println("Withdrawal failed. Insufficient funds or invalid input.");
        }
    }

    /*
    A method that deals with the fund transfer process transaction.
     */
    private void fundTransferProcess() throws IllegalAccountType {
        // Get recipient's account number
        getAccountNum().setFieldValue("Enter Recipient's Account Number: ");
        String AccountNUm = getAccountNum().getFieldValue();
        bl.getFieldDouble().setFieldValue("Enter Amount:: ");
        Double Amount = bl.getFieldDouble().getFieldValue();
        String recipientAccountNumber = String.valueOf(getLoggedAccount().transfer(getAssocBank(), getLoggedAccount(), Amount));

        // Check if the account exists
        Account recipientAccount = getLoggedAccount();

        if (recipientAccount == null) {
            System.out.println("Account not found.");
            return;
        }

        // Ask for the amount to transfer
        bl.getFieldDouble().setFieldValue("Enter Amount to Transfer: ");
        double transferAmount = bl.getFieldDouble().getFieldValue();  // Assuming this method retrieves the value

        // Check if transfer is valid and execute
        boolean transferSuccess = false;
        try {
            transferSuccess = this.account.transfer(recipientAccount, transferAmount);
        } catch (IllegalAccountType e) {
            System.out.println("Transfer failed");
        }

        if (transferSuccess) {
            System.out.println("Transfer successful! New Balance: " + this.account.getBalance());
        } else {
            System.out.println("Transfer failed. Please check your balance or try again.");
        }

    }


    /*
    Get the Savings Account instance of the currently logged account.
     */
    @Override
    protected SavingsAccount getLoggedAccount() {
        //Complete this method
        return this.account;
    }
}
