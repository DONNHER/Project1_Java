package CreditAccount;

public abstract class CreditAccount {
    /*
    Loan statement of this credit account.
    @Returns - String loan statement.
     */
    public abstract String getLoanStatement();

    /*
    Checks if this credit account can do additional credit transactions if the amount to credit will not exceed the credit limit set by the bank associated to this Credit Account.
    @Param amountAdjustment – The amount of credit to be adjusted once the said transaction is processed.
    @Return - Flag if this account can continue with the credit transaction.
     */
    public abstract String canCredit();

    /*
    Adjust the owner’s current loan. Result of adjustment cannot be less than 0.
    @Param amountAdjustment – Amount to be adjusted to the loan of this credit account.
     */
    public abstract void adjustAmount();

    /*
    Pay an amount of money to a selected account. Such an account cannot be of type CreditAccount.
    @Param account – Target account to pay money into.
    @Return - True if pay transaction was successful. False otherwise.
    @Throws IllegalAccountType – Credit Accounts cannot pay to other Credit Accounts as they do not hold account money balance but rather, credits. As such, in this scenario, only Savings Accounts can receive payment from Credit Accounts.
     */
    public abstract boolean pay();

    /*
    Recompense some amount of money to the bank and reduce the value of loan recorded in this account. Must not be greater than the current credit.
    @Param amount – Amount of money to be recompensed.
    @Return - Flag if compensation was successful.
     */
    public abstract boolean recompense();
}
