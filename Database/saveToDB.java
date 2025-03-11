package Database;

import Accounts.Transaction;
import Bank.Bank;

import java.util.ArrayList;

public interface saveToDB {
    public void save(Transaction transaction) ;
    public void saveFund(Transaction transaction) ;
    public void savePayment(Transaction transaction) ;
    public void saveRecompense(Transaction transaction) ;
    public void saveWithdraw(Transaction transaction) ;
}
