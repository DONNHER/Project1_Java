package Bank;

import Account.Account;
import Accounts.Transaction;
import CreditAccount.CreditAccount;
import Database.BankDB;
import Main.Field;
import Main.Field.*;
import Main.Main;
import SavingsAccount.SavingsAccount;


import java.security.cert.TrustAnchor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class BankLauncher {
    BankDB bankdb = new BankDB();
    Field<Double,Double> doubleField = new Field<Double,Double>("Double input", Double.class, 0.0, new DoubleFieldValidator());
    Field < Integer, Integer > user = new Field<Integer, Integer>("Options", Integer.class, 0, new IntegerFieldValidator());
    Field<Integer, Integer> idField = new Field<Integer, Integer>("ID field", Integer.class, -1, new IntegerFieldValidator());
    Field<String, String> nameField = new Field<String, String>("Name field", String.class, "0", new StringFieldValidator());
    Field<String, String> passcodeField = new Field<String, String>("Passcode field", String.class, "0", new StringFieldValidator());
    Field<Double, Double> depositLimitField = new Field<Double, Double>("Deposit limit field", Double.class, 0.0, new DoubleFieldValidator());
    Field<Double, Double> withdrawLimitField = new Field<Double, Double>("Withdraw limit field", Double.class, 0.0, new DoubleFieldValidator());
    Field<Double, Double> creditLimitField = new Field<Double, Double>("Credit Limit field", Double.class, 0.0, new DoubleFieldValidator());
    Field<Double, Double> processingFeeField = new Field<Double, Double>("ID field", Double.class, 0.0, new DoubleFieldValidator());
    Bank.BankComparator bankComparator = new Bank.BankComparator();
    Bank.BankIdComparator bankIdComparator = new Bank.BankIdComparator();
    Bank.BankCredentialsComparator BankCredentials = new Bank.BankCredentialsComparator();
    //List of banks currently registered in this session.
    private ArrayList<Bank> banks = new ArrayList<Bank>();
    //The Bank object currently logged in. Null by default, or when no bank is currently logged in.
    private Bank loggedBank = null;
    //Counter
    private int size = 0;
    //Ids of deleted Banks to update  database
    private ArrayList<Integer> bankids = new ArrayList<>();

    //Getters
    public Field<Double, Double> getFieldDouble(){return this.doubleField;}
    public int getUser(){
        return this.user.getFieldValue();
    }
    public Field<Integer, Integer> getIdField(){
        return this.idField;
    }
    public ArrayList<Bank> getBanks() {
        return banks;
    }

    public Bank getLoggedBank() {
        return this.loggedBank;
    }
    /*
    Utilize for Creating account or interaction when bank is logged in
     */
    public void GetNewAccounts() {
        this.newAccounts();
    }
    public ArrayList<Integer> getBankids(){
        return this.bankids;
    }
    public Bank.BankIdComparator getBankIdComparator(){
        return this.bankIdComparator;
    }

    //Setters
    public void setBanks(ArrayList<Bank> banks) {
        this.banks = banks;
    }

    public void setLoggedBank( Bank newbank ) {

        this.loggedBank = newbank;
    }

    /*
    Return Flag that Check if there is bank currently logged in
     */
    public boolean isLogged() {
        //Complete this method
        // Check if there is bank currently logged in (simplified)
        return this.loggedBank != null;
    }

    /*
    Bank interaction initialization. Utilized only when logged in.
     */
    public void bankInit() {
        //Complete this method

        while (isLogged()){

            Main.showMenuHeader("Welcome to the Bank Account Portal!");
            Main.showMenu(31, 1);
            Main.setOption();
            switch (Main.getOption()) {
                case 1:
                    showAccounts();
                    break;
                case 2:
                    Main.showMenuHeader("Account type selection");
                    Main.showMenu(33);
                    this.newAccounts();
                    break;
                case 3:
                    return;  // Exit the method and break the loop
                case 4:
                    System.out.println("Logging out...");
                    logout();
                    break;// Exit the method and break the loop
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }
    }
    public void bankUpdateInit() {
        //Complete this method
        while (true) {
            Main.showMenuHeader("Welcome to the Bank Update Portal!");
            Main.showMenu(4, 2);
            Main.setOption();
            switch (Main.getOption()) {
                case 1:
                    showAccounts();
                    break;
                case 2:
                    Main.showMenuHeader("Account type selection");
                    Main.showMenu(33);
                    this.newAccounts();
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                case 4:
                    System.out.println("Logging out...");
                    return;
                case 5:
                    System.out.println("Logging out...");
                    return;
                case 6:
                    System.out.println("Logging out...");
                    return;
                case 7:
                    System.out.println("Logging out...");
                    return;  // Exit the method and break the loop
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /*
    Show the accounts registered to this bank. Must prompt the user to select which type of
    accounts to show: (1) Credit Accounts, (2) Savings Accounts, and (3) All.
     */
    private void showAccounts() {
        //Complete this method
        boolean exit = false;
        do {
            Main.showMenuHeader("Show Account options");
            Main.showMenu(32, 1);
            user.setFieldValue("Enter option: ");
            for (Bank bank : this.banks) {
                if (user.getFieldValue() == 1) {
                    bank.showAccount(CreditAccount.class);
                } else if (user.getFieldValue() == 2) {
                    bank.showAccount(SavingsAccount.class);
                } else if (user.getFieldValue() == 3) {
                    for (Account accounts : bank.getBankAccounts()) {
                        System.out.println(accounts);
                    }
                } else if (user.getFieldValue() == 4) {
                    exit = true;
                }
            }
        } while (!exit);
    }
    /*
    Bank interaction when creating new accounts for the currently logged in bank.
     */
    private void newAccounts () {
        //Complete this method
        user.setFieldValue("Enter option: ");
        if (user.getFieldValue() == 1) {
            this.loggedBank.createCreditAccount(this.loggedBank);
        } else if (user.getFieldValue() == 2) {
            this.loggedBank.createSavingsAccount(this.loggedBank);
        }
    }

    /*
    Bank interaction when attempting to log in to the banking module using a bank user’s credentials.
     */
    public void bankLogin () {
        //Complete this method
        this.idField.setFieldValue("Enter bank ID: ");
        this.nameField.setFieldValue("Enter bank name: ",false);
        this.passcodeField.setFieldValue("Enter bank passcode: ");
        Bank bank = new Bank(this.idField.getFieldValue(),this.nameField.getFieldValue(),this.passcodeField.getFieldValue());

        Bank searchBank= getBank(BankCredentials,bank);
        if (searchBank != null){
            this.setLoggedBank(searchBank);
            System.out.println("Login successful! Welcome to " + searchBank.getName());
        }else {
            System.out.println("Login failed. Invalid credentials.");
        }
    }

    /*
    Creates a new login session for the logged in bank user. Sets up a new value for the loggedBank field.
    Params:
        b – Bank user that successfully logged in.
     */
    private void setLoggedSession (Bank b){
        //Complete this method
        this.loggedBank = b;
    }

    /*
    Destroys the login session for the current user.
     */
    private void logout () {
        //Complete this method
        this.loggedBank = null;
    }

    /*
    Creates a new bank record. Utilized separately from the rest of the methods of this class.
    Throws:
        NumberFormatException – May happen when inputting deposit, withdraw, and credit limit,
        and processing fee.
     */
    public void createNewBank () {
        //Complete this method
        this.idField.setFieldValue("Enter bank ID: ");
        this.nameField.setFieldValue("Enter bank name: ",false);
        this.passcodeField.setFieldValue("Enter bank passcode: ");
        this.depositLimitField.setFieldValue("Enter deposit limit: ");
        this.withdrawLimitField.setFieldValue("Enter withdraw limit: ");
        this.creditLimitField.setFieldValue("Enter credit limit: ");
        this.processingFeeField.setFieldValue("Enter processing fee: ");
        Bank newbank = new Bank(this.idField.getFieldValue(), this.nameField.getFieldValue(), this.passcodeField.getFieldValue(), this.depositLimitField.getFieldValue(),
                this.withdrawLimitField.getFieldValue(), this.creditLimitField.getFieldValue(), this.processingFeeField.getFieldValue());
        newbank.setIsNew(true);
        addBank(newbank);
        this.size += 1;
    }

    /*
    Output a menu of all registered or created banks in this session
     */
    public void showBanksMenu () {
        //Complete this method
        for (Bank banks : this.banks) {
            System.out.print("[" + banks.getID()+ "]   " + banks.getName() + '\n');
        }
    }

    /*
    Adds new bank to array list of banks.
    Params:
        b – Bank object to be added.
     */
    private void addBank (Bank b){
        //Complete this method
        if (getBank(this.BankCredentials,b) == null) {
            int index = findIndexInsertion(this.banks, b);
            this.banks.add(index, b);
        }
    }

    // Utilize only for Testing
    public void publicAddBank(Bank b){
        addBank(b);
    }

    /*
    Checks if a bank exists based on some criteria.
    Params:
        bankComparator – Criteria for searching.
        bank – Bank object to be compared.
    Returns:
        Bank object if it passes the criteria. Null if none.
     */
    public Bank getBank (Comparator < Bank > bankComparator, Bank bank ) {
        //Complete this method
        int left = 0;
        int right = this.banks.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Bank currentBank = this.banks.get(mid);

            // Use the comparator for comparison
            int comparison = bankComparator.compare(bank, currentBank);

            if (comparison == 0) {  // Bank found
                return currentBank;
            } else if (comparison > 0) {
                left = mid + 1;  // Search right
            } else {
                right = mid - 1; // Search left
            }
        }
        return null;
    }

    /*
    Finds the Account object based on some account number on all registered banks.
    Params:
        accountNumber – Account number of target Account.
    Returns:
        Account object if it exists. Null if not found.
     */
    public Account findAccount (String accountNum){
        //Complete this method
        int l = 0;
        for (Bank bank : this.banks) {
            int r = bank.bankAccountSize() - 1;
            while (l <= r) {
                int m = l + (r - l) / 2;
                int res = bank.getBankAccounts().get(m).getAccountNumber().compareTo(accountNum);
                if (res == 0) {
                    return bank.getBankAccounts().get(m);
                } else if (res < 0) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
        }
        return null;
    }

    /*
    Get the number of currently registered banks.
     */
    public int bankSize () {
        //Complete this method
        return this.banks.size();
    }

    /*
    Binary search method to find the index where a new bank should be inserted
     */
    public static int findIndexInsertion(ArrayList<Bank> banks, Bank b) {
        for (int i = 0; i < banks.size(); i++) {
            // Compare last names
            if (banks.get(i).getName().compareTo(b.getName()) >= 0) {
                // Insert i before contact
                return i;
            }
        }
        // Insert at i[-1] if no position found to be inserted
        return banks.size();
    }

    /*
    Delete Bank for some instances
    @param bankId Bank ID for unique identification
    @param passcode Pass code for unique identification
     */
    public void deleteBankFromArrayList(int bankId) {
        //Complete this method
        idField.setFieldValue(Integer.toString(bankId));
        Bank b = new Bank(idField.getFieldValue(),"","");
        Bank res = getBank(bankIdComparator,b);
        if (res != null) {
            getBanks().remove(res);
            bankids.add(idField.getFieldValue());
            return;
        }
        System.out.println("Bank not found in the list.");
    }
    public void loadBanksFromDatabase() {
        try {
            Connection conn = BankDB.connect();
            PreparedStatement pstmt1 = conn.prepareStatement("SELECT * FROM Banks");
            ResultSet rs = pstmt1.executeQuery();
            // Load banks and their accounts
            while (rs.next()) {
                Bank bank = new Bank(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Passcode"),
                        rs.getDouble("Deposit_Limit"),
                        rs.getDouble("Withdraw_Limit"),
                        rs.getDouble("Credit_Limit"),
                        rs.getDouble("Processing_Fee"));
                bank.setIsNew(false);
                this.addBank(bank);
            }
        } catch (SQLException _) {

        }
    }
    public void loadSavingsFromDatabase() {
        try {
            Connection conn = BankDB.connect();
            PreparedStatement pstmt1 = conn.prepareStatement("SELECT * FROM SavingsAccount");
            ResultSet res2 = pstmt1.executeQuery();
            while (res2.next()) {
                Bank bank = new Bank(res2.getInt("Bank"),"","");
                Bank search =  getBank(bankIdComparator,bank);
                if(search != null) {
                    SavingsAccount savingsAccount = new SavingsAccount(
                            search,
                            res2.getString("Account_Number"),
                            res2.getString("First_Name"),
                            res2.getString("Last_Name"),
                            res2.getString("Email"),
                            res2.getString("Pin"),
                            res2.getDouble("Balance"));
                    savingsAccount.setIsNew(false);
                    search.addNewAccount(savingsAccount);
                    search.addSavingsAccount(savingsAccount);
                }
            }
        } catch (SQLException _) {

        }
    }
    public void loadCreditsFromDatabase() {
        try {
            Connection conn = BankDB.connect();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM CreditAccounts ");
            ResultSet res1 = pstmt.executeQuery();  // Credit Accounts
            while (res1.next()) {
                Bank bank = new Bank(res1.getInt("Bank"),"","");
                Bank search =  getBank(bankIdComparator,bank);
                if(search != null) {
                    CreditAccount creditAccount = new CreditAccount(
                        search,
                        res1.getString("Account_Number"),
                        res1.getString("First_Name"),
                        res1.getString("Last_Name"),
                        res1.getString("Email"),
                        res1.getString("Pin"),
                        res1.getDouble("Loan_Statement"));
                    creditAccount.setIsNew(false);
                    search.addNewAccount(creditAccount);
                    search.addCreditAccount(creditAccount);
                }
            }
        } catch (SQLException _) {
        }
    }

    public void savetoDB() throws Exception {
        for(Bank b : this.banks){
            bankdb.saveBanksToDatabase(b);
            for (CreditAccount account: b.getCreditAccounts()){
                bankdb.saveCreditsAccount(account);
                for(Transaction transaction : account.getTransactionsInfo()){
                    if(transaction.transactionType.equals(Transaction.Transactions.Deposit)){
                        bankdb.save(transaction);
                    }else if(transaction.transactionType.equals(Transaction.Transactions.FundTransfer)){
                        bankdb.saveFund(transaction);
                    }else if(transaction.transactionType.equals(Transaction.Transactions.Withdraw)){
                        bankdb.saveWithdraw(transaction);
                    }else if(transaction.transactionType.equals(Transaction.Transactions.Recompense)){
                        bankdb.saveRecompense(transaction);
                    }else if(transaction.transactionType.equals(Transaction.Transactions.Payment)){
                        bankdb.savePayment(transaction);
                    }
                }
            }
            for (SavingsAccount account: b.getSavingsAccounts()){
                bankdb.saveSavingsAccount(account);
                for(Transaction transaction : account.getTransactionsInfo()){
                    if(transaction.transactionType.equals(Transaction.Transactions.Deposit)){
                        bankdb.save(transaction);
                    }else if(transaction.transactionType.equals(Transaction.Transactions.FundTransfer)){
                        bankdb.saveFund(transaction);
                    }else if(transaction.transactionType.equals(Transaction.Transactions.Withdraw)){
                        bankdb.saveWithdraw(transaction);
                    }else if(transaction.transactionType.equals(Transaction.Transactions.Recompense)){
                        bankdb.saveRecompense(transaction);
                    }else if(transaction.transactionType.equals(Transaction.Transactions.Payment)){
                        bankdb.savePayment(transaction);
                    }
                }
            }
        }
    }
}