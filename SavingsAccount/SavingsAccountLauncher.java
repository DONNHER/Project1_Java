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
            this.account.addNewTransaction(this.account.getAccountNumber(), Transaction.Transactions.Deposit,"Deposit successful. New balance: " + this.account.getBalance());
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
            this.account.addNewTransaction(this.account.getAccountNumber(), Transaction.Transactions.Withdraw,"Withdrawal successful. New balance: " + this.account.getBalance());

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
        Main.showMenuHeader("Fund Transfer Menu");
        Main.showMenu(5);
        Main.setOption();
        if (Main.getOption() == 1) {
            getAccountNum().setFieldValue("Enter Recipient's Account Number: ");
            Account recipientAccount = getLoggedAccount().getBank().getBankAccount(getLoggedAccount().getBank(),getAccountNum().getFieldValue());
            if(recipientAccount != null) {
                bl.getFieldDouble().setFieldValue("Enter Amount to Transfer: ");
                double transferAmount = bl.getFieldDouble().getFieldValue();
                SavingsAccount owner = getLoggedAccount();
                owner.transfer(recipientAccount,transferAmount);
            }

        }else if(Main.getOption() == 2){
            bl.showBanksMenu();
            Main.showMenuHeader("Options here");
            bl.getIdField().setFieldValue("Enter Bank ID: ");
            Bank b1 = new Bank(bl.getIdField().getFieldValue(), "", "");
            Bank search1 = bl.getBank(bl.getBankIdComparator(), b1);
            if (search1 != null) {
                getAccountNum().setFieldValue("Enter Recipient's Account Number: ");
                String AccountNUm1 = getAccountNum().getFieldValue();
                Account recipientAccount1 = getLoggedAccount().getBank().getBankAccount(getLoggedAccount().getBank(),AccountNUm1);
                if(recipientAccount1 != null) {
                    bl.getFieldDouble().setFieldValue("Enter Amount to Transfer: ");
                    double transferAmount = bl.getFieldDouble().getFieldValue();
                    SavingsAccount owner = getLoggedAccount();
                    owner.transfer(search1,recipientAccount1,transferAmount);
                }
            }
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
