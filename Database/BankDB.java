package Database;

import Account.Account;
import Bank.*;
import CreditAccount.CreditAccount;
import Main.Field;
import Main.FieldValidator;
import SavingsAccount.SavingsAccount;

import java.sql.*;
import java.util.ArrayList;

public class BankDB {
    private static final String path = "jdbc:sqlite:Database/bank.db";// SQLite file in project directory

    public static Connection connect() throws SQLException {
        // Establish the connection
        Connection conn = DriverManager.getConnection(path);
        System.out.println("Connected to SQLite database.");
        return conn;
    }

    public void loadBanksFromDatabase(BankLauncher banks) throws Exception {
        String sql = "SELECT * FROM Banks";
        String table1 = "SELECT * FROM CreditAccounts";
        String table2 = "SELECT * FROM SavingsAccount";

        try (Connection conn = BankDB.connect();
             PreparedStatement preStatements1 = conn.prepareStatement(table1);
             PreparedStatement preStatements2 = conn.prepareStatement(table2);
             PreparedStatement checkPstmt = conn.prepareStatement(sql)) {

            ResultSet rs = checkPstmt.executeQuery();
            ResultSet res1 = preStatements1.executeQuery();  // Credit Accounts
            ResultSet res2 = preStatements2.executeQuery();  // Savings Accounts

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
                banks.publicAddBank(bank);

                // Add Credit Accounts
                while (res1.next()) {
                    if (rs.getString("ID").equals(res1.getString("Bank"))) {
                        CreditAccount creditAccount = new CreditAccount(
                                bank,
                                res1.getString("Account_Number"),
                                res1.getString("First_Name"),
                                res1.getString("Last_Name"),
                                res1.getString("Email"),
                                res1.getString("Pin"),
                                res1.getDouble("Loan_Statement"));
                        bank.addNewAccount(creditAccount);
                    }
                }

                // Add Savings Accounts
                while (res2.next()) {
                    if (rs.getString("ID").equals(res2.getString("Bank"))) {
                        SavingsAccount savingsAccount = new SavingsAccount(
                                bank,
                                res2.getString("Account_Number"),
                                res2.getString("First_Name"),
                                res2.getString("Last_Name"),
                                res2.getString("Email"),
                                res2.getString("Pin"),
                                res2.getDouble("Balance"));
                        bank.addNewAccount(savingsAccount);
                    }
                }
            }

            rs.close();
            res1.close();
            res2.close();
        } catch (Exception e) {
            e.printStackTrace();  // Log the error
            throw new Exception("Error loading data from the database", e);
        }
    }



    //ISSUE HERe//
    public void saveBanksToDatabase(ArrayList<Bank> lists) throws Exception {
        String insertSqlCredit = "INSERT OR REPLACE INTO CreditAccounts (Account_Number, First_Name, Last_Name, Loan_Statement, Pin, Bank, Email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String insertSqlSavings = "INSERT OR REPLACE INTO SavingsAccount (Account_Number, First_Name, Last_Name, Balance, Pin, Bank, Email) VALUES (?, ?, ?, ?, ?, ?, ?)";

        String updateSqlCredit = "UPDATE CreditAccounts SET First_Name = ?, Last_Name = ?, Loan_Statement = ?, Pin = ?, Bank = ?, Email = ? WHERE Account_Number = ?";
        String updateSqlSavings = "UPDATE SavingsAccount SET First_Name = ?, Last_Name = ?, Balance = ?, Pin = ?, Bank = ?, Email = ? WHERE Account_Number = ?";

        String insertSql = "INSERT OR REPLACE INTO Banks (ID, Name, Passcode, Deposit_Limit, Withdraw_Limit, Credit_Limit, Processing_Fee) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String updateSql = "UPDATE Banks SET Name = ?, Passcode = ?, Deposit_Limit = ?, Withdraw_Limit = ?, Credit_Limit = ?, Processing_Fee = ? WHERE ID = ?";

        try (Connection conn = BankDB.connect()) {
            conn.setAutoCommit(false);  // Start transaction

            for (Bank bank : lists) {
                PreparedStatement pstmt;

                if (bank.getIsNew()) {  // For new banks, insert
                    pstmt = conn.prepareStatement(updateSql);
                    pstmt.setString(1, bank.getName());
                    pstmt.setString(2, bank.getPasscode());
                    pstmt.setDouble(3, bank.getDepositLimit());
                    pstmt.setDouble(4, bank.getWithdrawLimit());
                    pstmt.setDouble(5, bank.getCreditLimit());
                    pstmt.setDouble(6, bank.getProcessingFee());
                    pstmt.setInt(7, bank.getID());
                } else {  // For existing banks, update
                    pstmt = conn.prepareStatement(insertSql);
                    pstmt.setInt(1, bank.getID());
                    pstmt.setString(2, bank.getName());
                    pstmt.setString(3, bank.getPasscode());
                    pstmt.setDouble(4, bank.getDepositLimit());
                    pstmt.setDouble(5, bank.getWithdrawLimit());
                    pstmt.setDouble(6, bank.getCreditLimit());
                    pstmt.setDouble(7, bank.getProcessingFee());
                }
                pstmt.executeUpdate();
                pstmt.close();
                try (Connection conn1 = BankDB.connect()) {
                    conn1.setAutoCommit(false);
                    Connection conn2 = BankDB.connect();
                    conn2.setAutoCommit(false);
                    for (Account account : bank.getBankAccounts()) {
                        PreparedStatement pstmt1;
                        PreparedStatement pstmt2;
                        if (account.getClass().getName().equals("CreditAccount")) {
                            if (account.getIsNew()) {
                                pstmt1 = conn1.prepareStatement(updateSqlCredit);// New CreditAccount
                                pstmt1.setString(1, account.getOwnerFirstName());
                                pstmt1.setString(2, account.getOwnerLastName());
                                pstmt1.setDouble(3, account.loan_balance());  // Check if it's loan_balance() or another method
                                pstmt1.setString(4, account.getPin());
                                pstmt1.setInt(5, account.getBank().getID());
                                pstmt1.setString(6, account.getOwnerEmail());
                                pstmt1.setString(7, account.getAccountNumber());
                            } else {
                                pstmt1 = conn1.prepareStatement(insertSqlCredit);  // Update CreditAccount
                                pstmt1.setString(7, account.getAccountNumber());
                                pstmt1.setString(1, account.getOwnerFirstName());
                                pstmt1.setString(2, account.getOwnerLastName());
                                pstmt1.setDouble(3, account.loan_balance());
                                pstmt1.setString(4, account.getPin());
                                pstmt1.setInt(5, account.getBank().getID());
                                pstmt1.setString(6, account.getOwnerEmail());
                            }
                            pstmt1.executeUpdate();
                            pstmt1.close();
                        } else if (account.getClass().getName().equals("SavingsAccount")) {
                                if (account.getIsNew()) {
                                    pstmt2 = conn2.prepareStatement(updateSqlSavings);// New CreditAccount
                                    pstmt2.setString(1, account.getOwnerFirstName());
                                    pstmt2.setString(2, account.getOwnerLastName());
                                    pstmt2.setDouble(3, account.loan_balance());  // Check if it's loan_balance() or another method
                                    pstmt2.setString(4, account.getPin());
                                    pstmt2.setInt(5, account.getBank().getID());
                                    pstmt2.setString(6, account.getOwnerEmail());
                                    pstmt2.setString(7, account.getAccountNumber());
                                } else {
                                    pstmt2 = conn2.prepareStatement(insertSqlSavings);  // Update CreditAccount
                                    pstmt2.setString(7, account.getAccountNumber());
                                    pstmt2.setString(1, account.getOwnerFirstName());
                                    pstmt2.setString(2, account.getOwnerLastName());
                                    pstmt2.setDouble(3, account.loan_balance());
                                    pstmt2.setString(4, account.getPin());
                                    pstmt2.setInt(5, account.getBank().getID());
                                    pstmt2.setString(6, account.getOwnerEmail());
                                }
                                pstmt2.executeUpdate();
                                pstmt2.close();
                        }
                    }
                    conn1.commit();
                    conn2.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Database error occurred", e);

                }
            }

            conn.commit();  // Commit transaction
            System.out.println("Banks saved to database.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database error occurred", e);
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
}
