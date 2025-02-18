package SavingsAccount;

public abstract class SavingsAccount {
    /*
    Validates whether this savings account has enough balance to proceed.
    @param amount - Amount of money to be supposedly adjusted from this account's balance.
    @Return - Flag if transaction can proceed by adjusting balance by the amount tobe changed.
     */
    public abstract String hasEnoughBalance();

    /*
    Transfers an amount of money from this account to another savings account. Is extensively used by the other transfer() method.
    @param account – Account number of recipient.
    @param amount – Amount of money to be supposedly adjusted from this account’s balance.
    @Return - Flag if fund transfer transaction is successful or not.
    @Throws IllegalAccountType – Cannot fund transfer when the other account is of type CreditAccount.
    */
    public abstract boolean transfer();

    /*
    Deposit some cash into this account. Cannot be greater than the bank’s deposit limit.
    @Param amount – Amount of money to be deposited.
     */
    public abstract void cashDeposit();

    /*
    Withdraw an amount of money from this savings account. Cannot proceed if account does not have sufficient balance.
    @Param amount – Amount of money to be withdrawn.
    */
    public abstract void withdrawal();

    /*
    Get the account balance statement of this savings account.
    @Returns - String balance statement.
     */
    public abstract void getAccountBalanceStatement();

    /*
    Warns the account owner that their balance is not enough for the transaction to proceed successfully.
     */
    public abstract String insufficientBalance();

    /*
    Adjust the account balance of this savings account based on the amount to be adjusted. If it results to the account balance going less than 0.0, then it is forcibly reset to 0.0.
    @Param amount – Amount to be added or subtracted from the account balance.
     */
    public abstract void adjustAccountBalance();
}

