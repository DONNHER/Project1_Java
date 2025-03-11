package Database;

import Account.Account;

public interface loadFromDB {
    void loadDepositTransaction(Account account) ;
    void loadFundTransaction(Account account) ;
     void loadPaymentTransaction(Account account);
     void loadRecompenseTransaction(Account account) ;
     void loadWithdrawTransaction(Account account) ;
}
