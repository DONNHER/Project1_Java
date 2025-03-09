package Bank;

import Account.Account;
import BusinessAccount.BusinessAccount;
import Main.Field;
import CreditAccount.CreditAccount;
import SavingsAccount.SavingsAccount;
import StudentAccount.StudentAccount;
import java.util.ArrayList;
import java.util.Comparator;

public class Bank implements Comparator{
    Field <String,String> accountNField = new Field<String, String>("Name field", String.class, "0", new Field.StringFieldValidator());
    Field <String,String> fnameField = new Field<String, String>("First name field", String.class, "0", new Field.StringFieldValidator());
    Field <String,String> lnameField = new Field<String, String>("Last name field", String.class, "0", new Field.StringFieldValidator());
    Field <String,String> emailField = new Field<String, String>("Email Address field", String.class, "0", new Field.StringFieldValidator());
    Field <String,String> pinField = new Field<String, String>("Pin field", String.class, "0", new Field.StringFieldValidator());
    Field <Double,Double> BalanceField = new Field<Double, Double>("Balance field", Double.class, 0.0, new Field.DoubleFieldValidator());
    Field <Double,Double> laonField = new Field<Double, Double>("Loan field", Double.class, 0.0, new Field.DoubleFieldValidator());

    // ID number of bank
    private int ID ;
    // name of the bank
    private String name;
    // passcode of the bank;
    private String passcode;
    /*
    The amount of money each Savings Account registered to this bank can deposit at every transaction. Defaults to 50,000.0
     */
    private Double depositLimit = 50000.0;
    /*
    The amount of money withdrawable / transferrable at once, restricted to every Savings Account registered to this bank. Defaults to 50,000.0
     */
    private Double withdrawLimit = 50000.0;
    /*
    Limits the amount of credit or loan that all Credit Accounts, registered on this bank, can handle all at once. Defaults to 100,000.0
     */
    private Double creditLimit = 100000.0;
    /*
    Processing fee added when some transaction is involved with another bank. Cannot be lower than 0.0. Defaults to 10.00
     */
    private Double processingFee = 10.00;
    //Arraylist of BANK ACCOUNTS
    private ArrayList<Account> bankAccounts = new ArrayList<Account>();
    // Size of bank credit accounts
    private int c_count = 0;
    // Size of bank savings accounts
    private int s_count = 0;
    /*
    State of the Bank if updated or changed
     */
    private boolean isNew = false;
    private  ArrayList<CreditAccount> CreditAccounts = new ArrayList<>();
    private  ArrayList<SavingsAccount> SavingsAccounts = new ArrayList<>();


    public Bank(int ID, String name , String passcode){
        this.ID = ID;
        this.name = name;
        this.passcode = passcode;
    }

    public Bank(Integer ID,String name,String passcode, Double depositLimit, Double withdrawLimit, Double creditLimit, Double processingFee ){
        this.ID = ID;
        this.name = name;
        this.passcode = passcode;
        this.depositLimit = depositLimit;
        this.withdrawLimit = withdrawLimit;
        this.creditLimit = creditLimit;
        this.processingFee = processingFee;
    }
    //Getters
    public int getID(){
        return this.ID;
    }
    public String getName(){
        return this.name;
    }
    public String getPasscode(){
        return this.passcode;
    }
    public double getDepositLimit(){
        return this.depositLimit;
    }
    public double getWithdrawLimit(){
        return this.withdrawLimit;
    }
    public double getCreditLimit(){
        return this.creditLimit;
    }
    public double getProcessingFee(){
        return this.processingFee;
    }
    public ArrayList<Account> getBankAccounts() {
        return bankAccounts;
    }
    public ArrayList<CreditAccount> getCreditAccounts() {
        return CreditAccounts;
    }
    public ArrayList<SavingsAccount> getSavingsAccounts() {
        return SavingsAccounts;
    }
    public boolean getIsNew(){
        return this.isNew;
    }
    public Field<String, String> getIdField(){
        return this.accountNField;
    }
    public Field<String,String> getPinField(){
        return this.accountNField;
    }


    //Setters
    public void setID(int id){this.ID = id;}
    public void setName(String name){
        this.name = name;
    }
    public void setPasscode(String pass) {this.passcode = pass; }
    public void setDepositLimit(double newLimit){
        this.depositLimit = newLimit;
    }
    public void setWithdrawLimit(double newLimit){
        this.withdrawLimit = newLimit;
    }
    public void setCreditLimit(double newLimit){
        this.creditLimit = newLimit;
    }
    public void setProcessingFee(double newLimit){
        this.processingFee = newLimit;
    }
    public void setBankAccounts(ArrayList<Account> newBankAccounts){
        this.bankAccounts = new ArrayList<>(newBankAccounts);
    }
    public void setIsNew(boolean newState){
        this.isNew = newState;
    }

    @Override
    public int compare(Object o1, Object o2) {
        Account a1 = (Account) o1;
        Account a2 = (Account) o2;
        int res = a1.getOwnerLastName().compareTo(a2.getOwnerLastName());
        if( res != 0) {
            return res;
        }
        return a1.getOwnerFirstName().compareTo(a2.getOwnerFirstName());
    }

    /*
        A comparator that compares if two bank objects are the same.
         */
    public static class  BankComparator implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            //Complete this method
            Bank b1 = (Bank) o1;
            Bank b2 = (Bank) o2;
            if(b1 != b2){
                return -1;
            }
            return 0;
        }
    }

    /*
    A comparator that compares if two bank objects have the same bank id.
     */
    public static class BankIdComparator implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            //Complete this method
            Bank b1 = (Bank) o1;
            Bank b2 = (Bank) o2;
            return Integer.compare(b1.getID(), b2.getID());
        }
    }

    /*
    A comparator that compares if two bank objects share the same set of credentials.
     */
    public static class BankCredentialsComparator implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            //Complete this method
            Bank b1 = (Bank) o1;
            Bank b2 = (Bank) o2;
            if (b1.getID() != b2.getID()) {
                return Integer.compare(b1.getID(), b2.getID());
            }
            if (!b1.getName().equals(b2.getName())) {
                return b1.getName().compareTo(b2.getName());
            }
            return b1.getPasscode().compareTo(b2.getPasscode());
        }
    }
    /*
    Show accounts based on option.
    @Params accountType – Type of account to be shown
     */
    public <T> void showAccount(Class<T> accountType) {
        for (Account account : bankAccounts) {
            if (account.getClass().equals(accountType)) {
                System.out.println(account);
            }
        }
    }

    /*
    Get the Account object (if it exists) from a given bank.
    @Params bank - Bank to search from.
    @Params accountNum – Account number of target account.
     */
    public Account getBankAccount(Bank bank, String accountNum) {
         // Get the list of accounts
        int l = 0, r = bank.bankAccountSize() - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            Account midAccount = bank.getBankAccounts().get(mid);
            int res = midAccount.getAccountNumber().compareTo(accountNum);

            if (res == 0) {
                return midAccount; // Found the account
            } else if (res < 0) {
                l = mid + 1; // Search right half
            } else {
                r = mid - 1; // Search left half
            }
        }
        return null; // Account not found
    }


    /*
    Handles the processing of inputting the basic information of the account.
    @Returns Array list of Field objects, which are the basic account information of the account user
     */
    public ArrayList<Field<String, ?>> createNewAccount(){
        //Complete this code
        this.accountNField.setFieldValue("Enter account number: ");
        this.fnameField.setFieldValue("Enter first name: ",false);
        this.lnameField.setFieldValue("Enter last name: ",false);
        this.pinField.setFieldValue("Enter pin password: ");
        this.emailField.setFieldValue("Enter email address: ");


        ArrayList<Field<String, ?>> fields = new ArrayList<>();
        fields.add(this.accountNField);
        fields.add(this.fnameField);
        fields.add(this.lnameField);
        fields.add(this.pinField);
        fields.add(this.emailField);
        return fields;
    }
    /*
    Create a new savings account. Utilizes the createNewAccount() method.
    @Returns New savings account.
     */
    public SavingsAccount createSavingsAccount(Bank bank) {
        //Complete this method
        this.BalanceField.setFieldValue("Enter balance: ");
        createNewAccount();
        SavingsAccount newsavingsAccount = new SavingsAccount(
                bank,
                this.accountNField.getFieldValue(),
                this.fnameField.getFieldValue(),
                this.lnameField.getFieldValue(),
                this.emailField.getFieldValue(),
                this.pinField.getFieldValue(),
                this.BalanceField.getFieldValue());
        newsavingsAccount.setIsNew(true);
        addNewAccount(newsavingsAccount);
        addSavingsAccount(newsavingsAccount);
        s_count += 1;
        return newsavingsAccount;
    }

    /*
    Create a new credit account. Utilizes the createNewAccount() method.
    @param logged Bank in this session
    @Returns New credit account.
     */
    public CreditAccount createCreditAccount(Bank bank){
        //Complete this method
        this.laonField.setFieldValue("Enter loan: ");
        createNewAccount();
        CreditAccount newCreditAccount = new CreditAccount(bank,this.accountNField.getFieldValue(),this.fnameField.getFieldValue(),
                this.lnameField.getFieldValue(),this.emailField.getFieldValue(),this.pinField.getFieldValue(),this.laonField.getFieldValue());
        newCreditAccount.setIsNew(true);
        addNewAccount(newCreditAccount);
        addCreditAccount(newCreditAccount);
        c_count += 1;
        return newCreditAccount;
    }
    /*
    Create a new credit account. Utilizes the createNewAccount() method.
    @Returns New Student account.
     */
    public StudentAccount createStudentAccount(Bank bank){
        //Complete this method
        this.BalanceField.setFieldValue("Enter balance: ");
        createNewAccount();
        StudentAccount newStudentAccount = new StudentAccount(bank,this.accountNField.getFieldValue(),this.fnameField.getFieldValue(),
                this.lnameField.getFieldValue(),this.emailField.getFieldValue(),this.pinField.getFieldValue(),this.BalanceField.getFieldValue());
        addNewAccount(newStudentAccount);
        return newStudentAccount;
    }
    /*
    Create a new credit account. Utilizes the createNewAccount() method.
    @Returns New Business account.
     */
    public BusinessAccount createBusinessAccount(Bank bank){
        //Complete this method
        this.BalanceField.setFieldValue("Enter balance: ");
        createNewAccount();
        BusinessAccount newBusinessAccount = new BusinessAccount(bank,this.accountNField.getFieldValue(),this.fnameField.getFieldValue(),
                this.lnameField.getFieldValue(),this.emailField.getFieldValue(),this.pinField.getFieldValue(),this.BalanceField.getFieldValue());
        addNewAccount(newBusinessAccount);
        return newBusinessAccount;
    }

    /*
    Adds a new account to this bank, if the account number of the new account does not exist inside the bank.
    @Params account – Account object to be added into this bank.
     */
    public void addNewAccount(Account account){
        //Complete this method

        int index = findIndexInsertion(this.bankAccounts, account);
        this.bankAccounts.add(index, account);
    }

    public void addCreditAccount(CreditAccount ca){
        this.CreditAccounts.add(ca);
    }
    public void addSavingsAccount(SavingsAccount ca){
        this.SavingsAccounts.add(ca);
    }

    /*
    Checks if an account object exists into a given bank based on some account number.
    @Params bank – Bank to check if account exists.
    @Params accountNum – Account number of target account to check.
     */
    public boolean accountExist(Bank bank, String accountNum){
        //Complete this method
        return getBankAccount(bank,accountNum) == null;
    }

    /*
    Binary search method to find the index where a new bank account should be inserted
     */
    public int findIndexInsertion(ArrayList<Account> accounts, Account a) {
        int left = 0, right = bankAccountSize();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (compare(accounts.get(mid), a) >= 0) {
                right = mid;  // Search in the left half
            } else {
                left = mid + 1;  // Search in the right half
            }
        }
        return left; // Returns the correct index for insertion

    }


    //Get size of bank accounts
    //@return size number of bank accounts
    public int bankAccountSize(){
        return this.bankAccounts.size();
    }

    /*
    Return string
     */
    public String toString(){
        //Complete this method
        return this.getName();
    }
}