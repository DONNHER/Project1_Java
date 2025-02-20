package Accounts;

public interface Withdrawal {
    /**
     * Withdraws an amount of money using a given medium.
     * @param amount Amount of money to be withdrawn from.
     */
    public boolean withdrawal(double amount);

    /*
        Get the account balance statement of this savings account.
        @Returns - String balance statement.
         */
    void getAccountBalanceStatement();
}
