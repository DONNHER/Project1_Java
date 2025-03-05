package SavingsAccount;

import Account.AccountLauncher;
import Bank.BankLauncher;
import java.util.Scanner;

public class SavingsAccountLauncher extends AccountLauncher {

    private Scanner scanner = new Scanner(System.in);

    public SavingsAccountLauncher(BankLauncher bankLauncher) {
        super(bankLauncher);
    }

    /*
        Method that deals with all things about savings accounts. Mainly utilized for showing the main menu after Savings Account users log in to the application
         */
    public void savingsAccountInit() {
        while (true) {
            System.out.println("Welcome to the Savings Account Portal!");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Fund Transfer");
            System.out.println("4. Log Out");
            System.out.print("Enter your choice: ");

            int choice = -1;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                // Handle invalid input
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;  // Skip this iteration and ask again
            }

            switch (choice) {
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
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        SavingsAccount account = getLoggedAccount();
        if (account != null && account.cashDeposit(amount)) {
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
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        SavingsAccount account = getLoggedAccount();
        if (account != null && account.withdrawal(amount)) {
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
