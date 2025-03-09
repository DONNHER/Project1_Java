package Main;
import Bank.BankLauncher;

/**
 * An enumeration of menu options for every different menu in the
 * Banking program.
 */
public enum Menu {
    /**
     * Main.Menu options for main menu. This is the root menu for the program.
     */
    MainMenu(new String[]{ "Accounts Login", "Bank Login", "Create New Bank","Update Bank","Exit" }, 1),
    /**
     * Account.Account.Account Login menu options.
     */
    AccountLogin(new String[]{ "Accounts Login", "Go Back" }, 2),
    /**
     * Bank.Bank Login menu options.
     */
    BankLogin(new String[]{ "Login", "Go Back" }, 3),
    /**
     * Bank.Bank menu options after logging in, assuming that it was successful.
     */
    BankMenu(new String[]{ "Show Accounts", "New Accounts","Update Account","Log Out" }, 31),
    /**
     * Main.Menu option inside Bank.Bank module to show a particular set of accounts.
     */
    ShowAccounts(new String[]{ "Credit Accounts", "Savings Accounts", "All Accounts", "Go Back" }, 32),

    /**
     * Account.Account.Account type selection menu. Used when creating New Accounts for every Bank.Bank.
     */
    AccountTypeSelection(new String[]{ "Credit Account", "Savings Account" }, 33),
    /**
     * CreditAccount type selection menu. Used when logging in as a CreditAccount.
     */
    CreditAccountMenu(new String[]{ "Show Credits", "Pay", "Recompense","Get Credit", "Show Transactions", "Logout" }, 41),
    /**
     * SavingsAccount type selection menu. Used when logging in as a SavingsAccount.
     */
    SavingsAccountMenu(new String[]{ "Show Balance", "Deposit", "Withdraw", "Fund Transfer",
            "Show Transactions", "Logout" }, 51),
    /*
    Update selection menu.
     */
    UpdateBankMenu(new String[] {"Bank Name", "Passcode", "Deposit Limit", "Withdrawal Limit",
            "CreditLimit", "Processing Fee", "Log Out"},4),
    /*
    Fund transfer selection menu.
    */
    FundtransferMenu(new String[] {"Transfer Fund to other Account","Transfer Fund to other Bank Account"},5),
    /*
    Payment selection menu.
    */
    PaymentMenu(new String[] {"Pay to other Account","Pay to other Bank Account"},6);;

    public final String[] menuOptions;
    public final int menuIdx;

    private Menu(String[] menuOptions, int menuIdx) {
        this.menuOptions = menuOptions;
        this.menuIdx = menuIdx;
    }

    public static String[] getMenuOptions(int menuIdx) {
        for(Menu menu: Menu.values()) {
            if(menu.menuIdx == menuIdx) {
                return menu.menuOptions;
            }
        }
        return null;
    }
}
