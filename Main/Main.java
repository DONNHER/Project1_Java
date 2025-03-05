package Main;

import Account.AccountLauncher;
import Bank.Bank;
import Bank.BankLauncher;
import CreditAccount.CreditAccount;
import CreditAccountLauncher.CreditAccountLauncher;
import Database.*;
import SavingsAccount.SavingsAccount;
import SavingsAccountLauncher.SavingsAccountLauncher;

import java.io.File;
import java.util.Scanner;

public class Main
{

    private static final Scanner input = new Scanner(System.in);

    /**
     * Option field used when selection options during menu prompts. Do not create a different
     * option variable in menus. Just use this instead. <br>
     * As to how to utilize Field objects properly, refer to the following:
     *
     * @see #prompt(String, boolean)
   3  * @see #setOption() How Field objects are used.
     */
    public static Field<Integer, Integer> option = new Field<Integer, Integer>("Option",
            Integer.class, -1, new Field.IntegerFieldValidator());

    public static void main(String[] args) throws Exception {
        BankLauncher bl = new BankLauncher();
        AccountLauncher al = new AccountLauncher(bl);
        CreditAccountLauncher cl = new CreditAccountLauncher();
        SavingsAccountLauncher sl = new SavingsAccountLauncher();
        BankDB bankdb = new BankDB();
        bankdb.loadBanksFromDatabase(bl);
        while (true)
        {
            showMenuHeader("Main Menu");
            showMenu(1);
            setOption();
            // Account Option
            if (getOption() == 1)
            {
                // READ ME: Refer to this code block on how one should properly utilize
                // showMenuHeader(), showMenu(),
                // setOption(), and getOption() methods...
                showMenuHeader("Account Login Menu");
                showMenu(2, 1);
                setOption();
                showMenu(getOption(), 1);
                // TODO: Complete this portion
                if (bl.bankSize() != 0) {
                    if (getOption() == 1) {
                        showMenuHeader("All registered banks");
                        bl.showBanksMenu();
                        al.accountLogin();
                        if (al.isLoggedIn()) {
                            if (al.getLoggedAccount() instanceof CreditAccount) {
                                cl.creditAccountInit();
                            }else if (al.getLoggedAccount() instanceof SavingsAccount) {
                                sl.savingsAccountInit();
                            }
                        } else {
                            System.out.println("Logout, login first...");
                        }
                    }

                }
                else {
                    System.out.println("No bank exist, create first...");
                }
            }
            // Bank Option
            else if (getOption() == 2)
            {
                // TODO: Complete Bank option
                //READ ME: Checks if there's registered bank
                if (bl.bankSize() != 0) {
                    while (true) {
                        //Display all banks
                        showMenuHeader("All registered banks");
                        bl.showBanksMenu();
                        showMenuHeader("Options here");
                        showMenu(3);
                        setOption();
                        if (getOption() == 1) {
                            bl.bankLogin();//Call for bankLogin method
                            while (bl.isLogged()) {
                                showMenuHeader("Bank Accounts Menu");
                                showMenu(31, 1);
                                setOption();
                                if (getOption() == 1) {
                                    bl.bankInit();
                                } else if (getOption() == 2) {
                                    showMenuHeader("New Account options");
                                    showMenu(33, 1);
                                    bl.GetNewAccounts();
                                } else if (getOption() == 3) {
                                    break;
                                } else {
                                    System.out.println("Invalid option!");
                                }
                            }
                        }else if( getOption() == 2){
                            break;
                        } else {
                            System.out.println("Invalid option!");
                        }
                    }
                }
                else {
                    System.out.println("No bank exist, create first...");
                }
            }
            // Create New Bank
            else if (getOption() == 3)
            {
                // TODO: Complete this portion...
                bl.createNewBank();
            }
            else if (getOption() == 4)
            {
                bankdb.saveBanksToDatabase(bl.getBanks());
                bankdb.deleteMultipleBanksFromDatabase(bl.getBankids());
                for (Bank bank : bl.getBanks()){
                    bankdb.saveCreditsAccount(bank.getCreditAccounts());
                    bankdb.saveSavingsAccount(bank.getSavingsAccounts());
                }
                System.out.println("Exiting. Thank you for banking!");
                break;
            }
            else
            {
                System.out.println("Invalid option!");
            }
        }
    }

    /**
     * Show menu based on index given. <br>
     * Refer to Menu enum for more info about menu indexes. <br>
     * Use this method if you want a single menu option every line.
     *
     * @param menuIdx Main.Menu index to be shown
     */
    public static void showMenu(int menuIdx)
    {
        showMenu(menuIdx, 1);
    }

    /**
     * Show menu based on index given. <br>
     * Refer to Menu enum for more info about menu indexes.
     *
     * @param menuIdx Main.Menu index to be shown
     * @param inlineTexts Number of menu options in a single line. Set to 1 if you only want a
     *        single menu option every line.
     * @see Menu
     */
    public static void showMenu(int menuIdx, int inlineTexts)
    {
        String[] menu = Menu.getMenuOptions(menuIdx);
        if (menu == null)
        {
            System.out.println("Invalid menu index given!");
        }
        else
        {
            String space = inlineTexts == 0 ? "" : "%-20s";
            String fmt = "[%d] " + space;
            int count = 0;
            for (String s : menu)
            {
                count++;
                System.out.printf(fmt, count, s);
                if (count % inlineTexts == 0)
                {
                    System.out.println();
                }
            }
        }
    }

    /**
     * Prompt some input to the user. Only receives on non-space containing String. This string can
     * then be parsed into targeted data type using DataTypeWrapper.parse() method.
     *
     * @param phrase Prompt to the user.
     * @param inlineInput A flag to ask if the input is just one entire String or receive an entire
     *        line input. <br>
     *        Set to <b>true</b> if receiving only one String input without spaces. <br>
     *        Set to <b>false</b> if receiving an entire line of String input.
     * @return Value of the user's input.
     * @see Field#setFieldValue(String, boolean) How prompt is utilized in Field.
     */
    public static String prompt(String phrase, boolean inlineInput)
    {
        System.out.print(phrase);
        if (inlineInput)
        {
            String val = input.next();
            input.nextLine();
            return val;
        }
        return input.nextLine();
    }

    /**
     * Prompts user to set an option based on menu outputted.
     *
     * @throws NumberFormatException May happen if the user attempts to input something other than
     *         numbers.
     */
    public static void setOption() throws NumberFormatException
    {
        option.setFieldValue("Select an option: ");
    }

    /**
     * @return Recently inputted option by the user.
     */
    public static int getOption()
    {
        return Main.option.getFieldValue();
    }

    /**
     * Used for printing the header whenever a new menu is accessed.
     *
     * @param menuTitle Title of the menu to be outputted.
     */
    public static void showMenuHeader(String menuTitle)
    {
        System.out.printf("<---- %s ----->\n", menuTitle);
    }
}
