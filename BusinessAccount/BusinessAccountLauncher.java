package BusinessAccount;

import Account.Account;
import Account.AccountLauncher;
import Accounts.IllegalAccountType;
import Accounts.Transaction;
import Bank.BankLauncher;
import Main.Main;


import java.time.LocalDateTime;

public class BusinessAccountLauncher extends AccountLauncher {
    private final   BankLauncher bl;
    private final BusinessAccount account;
    public BusinessAccountLauncher(BankLauncher bankLauncher,Account logged) {
        super(bankLauncher);
        this.bl = bankLauncher;
        this.account = (BusinessAccount) logged;
    }

    /*
   Method that deals with all things about savings accounts. Mainly utilized for showing the main menu after Savings Account users log in to the application
    */
    public void businessAccountInit() throws IllegalAccountType {
        while (true) {
            Main.showMenuHeader("Welcome to the Business Account Portal!");
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
                case 4:
                    fundTransferProcess();
                    break;
                case 5:
                    paymentProcess();
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
    A method that deals with the fund transfer process transaction.
     */
    private void fundTransferProcess(){
        // Get recipient's account number
        try {
            Main.showMenuHeader("Fund Transfer Menu");
            getAccountNum().setFieldValue("Enter Recipient's Account Number: ");
            Account recipientAccount2 = bl.findAccount(getAccountNum().getFieldValue());
            if(recipientAccount2 != null) {
                if(recipientAccount2.getBank().equals(getLoggedAccount().getBank())) {
                    bl.getFieldDouble().setFieldValue("Enter Amount to Transfer: ");
                    getLoggedAccount().transfer(recipientAccount2, bl.getFieldDouble().getFieldValue());
                    getLoggedAccount().addNewTransaction(account.getAccountNumber(), Transaction.Transactions.FundTransfer, LocalDateTime.now()+" Fund Transfer successful: $"+bl.getFieldDouble().getFieldValue()+" to "+ recipientAccount2.getOwnerFullName());
                    System.out.println(LocalDateTime.now()+" Fund Transfer successful: $"+bl.getFieldDouble().getFieldValue()+" to "+ recipientAccount2.getOwnerFullName());
                    return;
                }
                bl.getFieldDouble().setFieldValue("Enter Amount to Transfer: ");
                getLoggedAccount().transfer(recipientAccount2.getBank(),recipientAccount2, (bl.getFieldDouble().getFieldValue() - getAssocBank().getProcessingFee()));
                getLoggedAccount().addNewTransaction(account.getAccountNumber(), Transaction.Transactions.FundTransfer, LocalDateTime.now()+" Fund Transfer successful: $"+bl.getFieldDouble().getFieldValue()+" to "+ recipientAccount2.getOwnerFullName());
                System.out.println(LocalDateTime.now()+" Fund Transfer successful: $"+bl.getFieldDouble().getFieldValue()+" to "+ recipientAccount2.getOwnerFullName());
                return;
            }
            System.out.println("Fund Transfer Unsuccessful: $"+bl.getFieldDouble().getFieldValue()+" to "+ recipientAccount2.getOwnerFullName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void paymentProcess() {
        try {
            Main.showMenuHeader("Payment Menu");
            getAccountNum().setFieldValue("Enter Recipient's Account Number: ");
            Account recipientAccount2 = bl.findAccount(getAccountNum().getFieldValue());
            if (recipientAccount2 != null) {
                bl.getFieldDouble().setFieldValue("Enter Amount: ");
                if (getLoggedAccount().pay(recipientAccount2, bl.getFieldDouble().getFieldValue())) {
                    getLoggedAccount().addNewTransaction(getLoggedAccount().getAccountNumber(), Transaction.Transactions.Payment, "Payment Successful: $" + bl.getFieldDouble().getFieldValue());
                    System.out.println("Payment Successful: $" + bl.getFieldDouble().getFieldValue());
                    return;
                }
                System.out.println("Payment Unsuccessful: $" + bl.getFieldDouble().getFieldValue() + " exceeds bank credit limit.");
            }
            System.out.println("Recipient's Account Number not found!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*
    Get the Savings Account instance of the currently logged account.
     */
    @Override
    protected BusinessAccount getLoggedAccount() {
        //Complete this method
        return this.account;
    }
}

