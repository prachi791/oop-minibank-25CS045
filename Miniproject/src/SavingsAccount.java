
package Miniproject.src;

public class SavingsAccount extends Account {

    private long minBalance;

    public SavingsAccount(
            String ownerName, long openingBalance, long minBalance) {
        super(ownerName, openingBalance);
        this.minBalance = minBalance;
    }

    // Copy constructor for Lab 3 compatibility
    public SavingsAccount(SavingsAccount other) {
        super(other);
        this.minBalance = other.minBalance;
    }

    @Override
    public double interestRate() {
        return 4.0;
    }

    @Override
    public boolean canWithdraw(long amount) {
        return amount > 0
                && getBalance() - amount >= minBalance;
    }

    public long getMinBalance() {
        return minBalance;
    }
}
