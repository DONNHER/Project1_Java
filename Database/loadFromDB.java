package Database;

import Account.Account;
import Accounts.Transaction;
import Bank.BankLauncher;

import java.util.ArrayList;

public interface loadFromDB {
    public void loadDepositTransaction(Account account) ;
    public void loadFundTransaction(Account account) ;
    public void loadPaymentTransaction(Account account);
    public void loadRecompenseTransaction(Account account) ;
    public void loadWithdrawTransaction(Account account) ;

}
