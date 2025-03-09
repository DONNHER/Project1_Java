
package Database;

import Account.Account;
import Accounts.Transaction;
import Bank.*;
import CreditAccount.CreditAccount;

import SavingsAccount.SavingsAccount;

import java.sql.*;
import java.util.ArrayList;

public class BankDB implements loadFromDB,saveToDB{
    private static final String path = "jdbc:sqlite:Database/bank1.db";// SQLite file in project directory

    public static Connection connect() throws SQLException {
        // Establish the connection
        return DriverManager.getConnection(path);
    }

    //ISSUE HERe//
    public void updateBanksToDatabase(Bank bank) {
        String updateSql = "UPDATE Banks SET Name = ?, Passcode = ?, Deposit_Limit = ?, Withdraw_Limit = ?, Credit_Limit = ?, Processing_Fee = ? WHERE ID = ?";
        try (Connection conn = BankDB.connect()) {
            PreparedStatement pstmt;  // For new banks, insert
            pstmt = conn.prepareStatement(updateSql);
            pstmt.setString(1, bank.getName());
            pstmt.setString(2, bank.getPasscode());
            pstmt.setDouble(3, bank.getDepositLimit());
            pstmt.setDouble(4, bank.getWithdrawLimit());
            pstmt.setDouble(5, bank.getCreditLimit());
            pstmt.setDouble(6, bank.getProcessingFee());
            pstmt.setInt(7, bank.getID());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred", e);
        }
    }

    public void saveBanksToDatabase(Bank bank) {
        String s = "CREATE TABLE IF NOT EXISTS Banks ("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "Name TEXT NOT NULL, "
                + "Passcode TEXT NOT NULL UNIQUE, "
                + "Deposit_Limit REAL, "
                + "Withdraw_Limit REAL, "
                + "Credit_Limit REAL, "
                + "Processing_Fee REAL"
                + ");";
        String insertSql = "INSERT INTO Banks (ID, Name, Passcode, Deposit_Limit, Withdraw_Limit, Credit_Limit, Processing_Fee) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = BankDB.connect()) {
            Statement stmt = conn.createStatement();
            stmt.execute(s);
            if (bank.getIsNew().equals("New")) {
                PreparedStatement pstmt = conn.prepareStatement(insertSql);
                pstmt.setInt(1, bank.getID());
                pstmt.setString(2, bank.getName());
                pstmt.setString(3, bank.getPasscode());
                pstmt.setDouble(4, bank.getDepositLimit());
                pstmt.setDouble(5, bank.getWithdrawLimit());
                pstmt.setDouble(6, bank.getCreditLimit());
                pstmt.setDouble(7, bank.getProcessingFee());
                pstmt.executeUpdate();
                pstmt.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred", e);

        }
        if (bank.getIsNew().equals("Update") || bank.getIsNew().equals("Old")) {
            updateBanksToDatabase(bank);
        }
    }

    public void UpdateCreditsAccount(CreditAccount account) {
        String updateSqlCredit = "UPDATE CreditAccounts SET First_Name = ?, Last_Name = ?, Loan_Statement = ?, Pin = ?, Bank = ?, Email = ? WHERE Account_Number = ?";
        try (Connection conn1 = BankDB.connect()) {
            conn1.setAutoCommit(false);
            PreparedStatement pstmt1;
            pstmt1 = conn1.prepareStatement(updateSqlCredit);// New CreditAccount
            pstmt1.setString(1, account.getOwnerFirstName());
            pstmt1.setString(2, account.getOwnerLastName());
            pstmt1.setDouble(3, account.loan_balance());  // Check if it's loan_balance() or another method
            pstmt1.setString(4, account.getPin());
            pstmt1.setInt(5, account.getBank().getID());
            pstmt1.setString(6, account.getOwnerEmail());
            pstmt1.setString(7, account.getAccountNumber());
            pstmt1.executeUpdate();
            pstmt1.close();
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred", e);
        }

    }

    public void saveCreditsAccount(CreditAccount account) {
        String s = "CREATE TABLE IF NOT EXISTS CreditAccounts ("
                + "Account_Number TEXT NOT NULL, "
                + "First_Name TEXT NOT NULL, "
                + "Last_Name TEXT NOT NULL, "
                + "Loan_Statement REAL NOT NULL,, "
                + "Pin TEXT NOT NULL, "
                + "Bank INTEGER NOT NULL, "
                + "Email TEXT NOT NULL,"
                + ");";
        String insertSqlCredit = "INSERT INTO CreditAccounts (Account_Number, First_Name, Last_Name, Loan_Statement, Pin, Bank, Email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn1 = BankDB.connect()) {
            Statement stmt = conn1.createStatement();
            stmt.execute(s);
            if (account.getIsNew().equals("New")) {
                PreparedStatement pstmt1 = conn1.prepareStatement(insertSqlCredit);  // Update CreditAccount
                pstmt1.setString(1, account.getAccountNumber());
                pstmt1.setString(2, account.getOwnerFirstName());
                pstmt1.setString(3, account.getOwnerLastName());
                pstmt1.setDouble(4, account.loan_balance());
                pstmt1.setString(5, account.getPin());
                pstmt1.setInt(6, account.getBank().getID());
                pstmt1.setString(7, account.getOwnerEmail());
                pstmt1.executeUpdate();
                pstmt1.close();
            }
            } catch (SQLException e) {
            throw new RuntimeException("Database error occurred", e);
        }
        if (account.getIsNew().equals("Update") || account.getIsNew().equals("Old")) {
            UpdateCreditsAccount(account);
        }
    }

    public void UpdateSavingsAccount(SavingsAccount account) {
        String updateSqlSavings = "UPDATE SavingsAccount SET First_Name = ?, Last_Name = ?, Balance = ?, Pin = ?, Bank = ?, Email = ? WHERE Account_Number = ?";
        try (Connection conn2 = BankDB.connect()) {
            PreparedStatement pstmt2;
            pstmt2 = conn2.prepareStatement(updateSqlSavings);// New CreditAccount
            pstmt2.setString(1, account.getOwnerFirstName());
            pstmt2.setString(2, account.getOwnerLastName());
            pstmt2.setDouble(3, account.loan_balance());  // Check if it's loan_balance() or another method
            pstmt2.setString(4, account.getPin());
            pstmt2.setInt(5, account.getBank().getID());
            pstmt2.setString(6, account.getOwnerEmail());
            pstmt2.setString(7, account.getAccountNumber());
            pstmt2.executeUpdate();
            pstmt2.close();
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred", e);
        }
    }

    public void saveSavingsAccount(SavingsAccount account) {
        String s = "CREATE TABLE IF NOT EXISTS SavingsAccount ("
                + "Account_Number TEXT NOT NULL PRIMARY KEY, "
                + "First_Name TEXT NOT NULL, "
                + "Last_Name TEXT NOT NULL, "
                + "Loan_Statement REAL NOT NULL,, "
                + "Pin TEXT NOT NULL, "
                + "Bank INTEGER NOT NULL, "
                + "Email TEXT NOT NULL,"
                + ");";
        String insertSqlSavings = "INSERT INTO SavingsAccount (Account_Number, First_Name, Last_Name, Balance, Pin, Bank, Email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn2 = BankDB.connect()) {
            Statement stmt = conn2.createStatement();
            stmt.execute(s);
            if (account.getIsNew().equals("New")) {
                PreparedStatement pstmt2 = conn2.prepareStatement(insertSqlSavings);  // Update CreditAccount
                pstmt2.setString(1, account.getAccountNumber());
                pstmt2.setString(2, account.getOwnerFirstName());
                pstmt2.setString(3, account.getOwnerLastName());
                pstmt2.setDouble(4, account.loan_balance());
                pstmt2.setString(5, account.getPin());
                pstmt2.setInt(6, account.getBank().getID());
                pstmt2.setString(7, account.getOwnerEmail());
                pstmt2.executeUpdate();
                pstmt2.close();
                }
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred", e);
        }
        if (account.getIsNew().equals("Update") || account.getIsNew().equals("Old")) {
            UpdateSavingsAccount(account);
        }
    }


    public void deleteBankFromDatabase(int bankId) throws Exception {
        String deleteSql = "DELETE FROM Banks WHERE id = ?";

        // Establish connection and execute delete query
        Connection conn = BankDB.connect();
        PreparedStatement pstmt = conn.prepareStatement(deleteSql);
        pstmt.setInt(1, bankId);  // Use the bank's ID to identify it
        pstmt.executeUpdate();     // Delete from the database

        System.out.println("Bank removed from database.");

        // Close the resources
        pstmt.close();
        conn.close();
    }


    public void deleteMultipleBanksFromDatabase(ArrayList<Integer> bankIds) throws Exception {
        for (Integer bankId : bankIds) {
            deleteBankFromDatabase(bankId);
        }
    }


    @Override
    public void loadDepositTransaction(Account account) {
        try {
            Connection conn = BankDB.connect();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM DepositTransactions ");
            ResultSet res1 = pstmt.executeQuery();  // Credit Accounts
            while (res1.next()) {
                if(res1.getString("AccountNumber").equals(account.getAccountNumber())) {
                    Transaction.Transactions type = Transaction.Transactions.valueOf(res1.getObject("Type").toString());
                    account.addNewTransaction(
                            res1.getString("AccountNumber"),
                            type,
                            res1.getString("Description"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred", e);
        }
    }
    @Override
    public void loadFundTransaction(Account account)  {
        try {
            Connection conn = BankDB.connect();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM FundTransferTransactions ");
            ResultSet res1 = pstmt.executeQuery();  // Credit Accounts
            while (res1.next()) {
                if(res1.getString("AccountNumber").equals(account.getAccountNumber())) {
                    Transaction.Transactions type = Transaction.Transactions.valueOf(res1.getObject("Type").toString());
                    account.addNewTransaction(
                            res1.getString("AccountNumber"),
                            type,
                            res1.getString("Description"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred", e);
        }
    }
    @Override
    public void loadPaymentTransaction(Account account) {
        try {
            Connection conn = BankDB.connect();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM PaymentTransactions ");
            ResultSet res1 = pstmt.executeQuery();  // Credit Accounts
            while (res1.next()) {
                if(res1.getString("AccountNumber").equals(account.getAccountNumber())) {
                    Transaction.Transactions type = Transaction.Transactions.valueOf(res1.getObject("Type").toString());
                    account.addNewTransaction(
                            res1.getString("AccountNumber"),
                            type,
                            res1.getString("Description"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred", e);
        }
    }
    @Override
    public void loadRecompenseTransaction(Account account) {
        try {
            Connection conn = BankDB.connect();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM RecompenseTransactions ");
            ResultSet res1 = pstmt.executeQuery();  // Credit Accounts
            while (res1.next()) {
                if(res1.getString("AccountNumber").equals(account.getAccountNumber())) {
                    Transaction.Transactions type = Transaction.Transactions.valueOf(res1.getObject("Type").toString());
                    account.addNewTransaction(
                            res1.getString("AccountNumber"),
                            type,
                            res1.getString("Description"));
                }
            }
        }  catch (SQLException e) {
            throw new RuntimeException("Database error occurred", e);
        }
    }

    @Override
    public void loadWithdrawTransaction(Account account){
        try {
            Connection conn = BankDB.connect();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM WithdrawTransactions ");
            ResultSet res1 = pstmt.executeQuery();  // Credit Accounts
            while (res1.next()) {
                if(res1.getString("AccountNumber").equals(account.getAccountNumber())) {
                    Transaction.Transactions type = Transaction.Transactions.valueOf(res1.getObject("Type").toString());
                    account.addNewTransaction(
                            res1.getString("AccountNumber"),
                            type,
                            res1.getString("Description"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred", e);
        }
    }
    public void loadGetCreditTransaction(Account account){
        try {
            Connection conn = BankDB.connect();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM GetCreditTransactions ");
            ResultSet res1 = pstmt.executeQuery();  // Credit Accounts
            while (res1.next()) {
                if(res1.getString("AccountNumber").equals(account.getAccountNumber())) {
                    Transaction.Transactions type = Transaction.Transactions.valueOf(res1.getObject("Type").toString());
                    account.addNewTransaction(
                            res1.getString("AccountNumber"),
                            type,
                            res1.getString("Description"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred", e);
        }
    }

    @Override
    public void save(Transaction transaction) {
        String s = "CREATE TABLE IF NOT EXISTS DepositTransactions ("
                + "AccountNumber TEXT NOT NULL, "
                + "Type TEXT NOT NULL, "
                + "Description TEXT NOT NULL, "
                + ");";

        String insertSqlDeposit = "INSERT INTO DepositTransactions (AccountNumber, Type, Description) VALUES (?, ?, ?)";
        try (Connection conn2 = BankDB.connect()) {
            Statement stmt = conn2.createStatement();
            stmt.execute(s);
            PreparedStatement pstmt2 = conn2.prepareStatement(insertSqlDeposit);  // Update CreditAccount
            pstmt2.setString(1, transaction.accountNumber);
            pstmt2.setObject(2, transaction.transactionType);
            pstmt2.setString(3, transaction.description);
            pstmt2.executeUpdate();
            pstmt2.close();
            // Commit transaction
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred", e);
        }
    }

    @Override
    public void savePayment(Transaction transaction) {
        String s = "CREATE TABLE IF NOT EXISTS PaymentTransactions ("
                + "AccountNumber TEXT NOT NULL, "
                + "Type TEXT NOT NULL, "
                + "Description TEXT NOT NULL, "
                + ");";
        String insertSqlDeposit = "INSERT INTO PaymentTransactions (AccountNumber, Type, Description) VALUES (?, ?, ?)";
        try (Connection conn2 = BankDB.connect()) {
            Statement stmt = conn2.createStatement();
            stmt.execute(s);
            PreparedStatement pstmt2 = conn2.prepareStatement(insertSqlDeposit);  // Update CreditAccount
            pstmt2.setString(1, transaction.accountNumber);
            pstmt2.setObject(2, transaction.transactionType);
            pstmt2.setString(3, transaction.description);
            pstmt2.executeUpdate();
            pstmt2.close();
            // Commit transaction
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred", e);
        }
    }

    @Override
    public void saveFund(Transaction transaction) {
        String s = "CREATE TABLE IF NOT EXISTS FundTransferTransactions ("
                + "AccountNumber TEXT NOT NULL, "
                + "Type TEXT NOT NULL, "
                + "Description TEXT NOT NULL, "
                + ");";
        String insertSqlDeposit = "INSERT INTO FundTransferTransactions (AccountNumber, Type, Description) VALUES (?, ?, ?)";
        try (Connection conn2 = BankDB.connect()) {
            Statement stmt = conn2.createStatement();
            stmt.execute(s);
            PreparedStatement pstmt2 = conn2.prepareStatement(insertSqlDeposit);  // Update CreditAccount
            pstmt2.setString(1, transaction.accountNumber);
            pstmt2.setObject(2, transaction.transactionType);
            pstmt2.setString(3, transaction.description);
            pstmt2.executeUpdate();
            pstmt2.close();
            // Commit transaction
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred", e);
        }
    }

    @Override
    public void saveRecompense(Transaction transaction) {
        String s = "CREATE TABLE IF NOT EXISTS RecompenseTransactions ("
                + "AccountNumber TEXT NOT NULL, "
                + "Type TEXT NOT NULL, "
                + "Description TEXT NOT NULL, "
                + ");";
        String insertSqlDeposit = "INSERT INTO RecompenseTransactions (AccountNumber, Type, Description) VALUES (?, ?, ?)";
        try (Connection conn2 = BankDB.connect()) {
            Statement stmt = conn2.createStatement();
            stmt.execute(s);
            PreparedStatement pstmt2 = conn2.prepareStatement(insertSqlDeposit);  // Update CreditAccount
            pstmt2.setString(1, transaction.accountNumber);
            pstmt2.setObject(2, transaction.transactionType);
            pstmt2.setString(3, transaction.description);
            pstmt2.executeUpdate();
            pstmt2.close();
            // Commit transaction
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred", e);
        }
    }
    @Override
    public void saveWithdraw(Transaction transaction) {
        String s = "CREATE TABLE IF NOT EXISTS WithdrawTransactions ("
                + "AccountNumber TEXT NOT NULL, "
                + "Type TEXT NOT NULL, "
                + "Description TEXT NOT NULL, "
                + ");";
        String insertSqlDeposit = "INSERT INTO WithdrawTransactions (AccountNumber, Type, Description) VALUES (?, ?, ?)";
        try (Connection conn2 = BankDB.connect()) {
            Statement stmt = conn2.createStatement();
            stmt.execute(s);
            PreparedStatement pstmt2 = conn2.prepareStatement(insertSqlDeposit);  // Update CreditAccount
            pstmt2.setString(1, transaction.accountNumber);
            pstmt2.setObject(2, transaction.transactionType);
            pstmt2.setString(3, transaction.description);
            pstmt2.executeUpdate();
            pstmt2.close();
            // Commit transaction
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred", e);
        }
    }
    @Override
    public void saveGetCredit(Transaction transaction) {
        String s = "CREATE TABLE IF NOT EXISTS GetCreditTransactions ("
                + "AccountNumber TEXT NOT NULL, "
                + "Type TEXT NOT NULL, "
                + "Description TEXT NOT NULL, "
                + ");";
        String insertSqlDeposit = "INSERT INTO GetCreditTransactions (AccountNumber, Type, Description) VALUES (?, ?, ?)";
        try (Connection conn2 = BankDB.connect()) {
            Statement stmt = conn2.createStatement();
            stmt.execute(s);
            PreparedStatement pstmt2 = conn2.prepareStatement(insertSqlDeposit);  // Update CreditAccount
            pstmt2.setString(1, transaction.accountNumber);
            pstmt2.setObject(2, transaction.transactionType);
            pstmt2.setString(3, transaction.description);
            pstmt2.executeUpdate();
            pstmt2.close();
            // Commit transaction
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred", e);
        }
    }
}