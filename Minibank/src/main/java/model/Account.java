package model;

import exception.BankException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;
import model.annotation.Id;
import model.annotation.Positive;
import java.io.Serializable;

public abstract class Account implements Transactable, InterestBearing, Comparable<Account>, Serializable {
    private static final long serialVersionUID = 1L;
    private static int counter = 1;
    @Id
    private final String accountNumber;
    private String ownerName;
    @Positive
    private long balance;
    private boolean active = true;

    protected Account(String ownerName, long openingBalance) {
        this.accountNumber = String.format("AC%04d", counter++);
        this.ownerName = ownerName;
        this.balance = openingBalance;
    }

    protected Account(String ownerName) {
        this(ownerName, 0);
    }

    protected Account(Account other) {
        this.accountNumber = other.accountNumber;
        this.ownerName = other.ownerName;
        this.balance = other.balance;
        this.active = other.active;
    }

    public static void updateSequence(String accountNumber) {
        try {
            int number = Integer.parseInt(accountNumber.substring(2));
            if (number >= counter) counter = number + 1;
        } catch (RuntimeException ignored) {
        }
    }

    public abstract double interestRate();
    public abstract boolean canWithdraw(long amount);

    @Override
    public synchronized void deposit(long amount) throws InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException("Amount must be positive.");
        balance += amount;
    }

    @Override
    public synchronized boolean withdraw(long amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException("Amount must be positive.");
        if (!canWithdraw(amount)) throw new InsufficientFundsException(Math.max(0, amount - balance));
        balance -= amount;
        return true;
    }

    public void transfer(Account to, long amount) throws BankException {
        if (to == null || to == this) throw new InvalidAmountException("Choose a different destination account.");
        Account first = accountNumber.compareTo(to.accountNumber) < 0 ? this : to;
        Account second = first == this ? to : this;
        synchronized (first) {
            synchronized (second) {
                try {
                    withdraw(amount);
                    to.deposit(amount);
                } catch (BankException e) {
                    throw e;
                } finally {
                    active = true;
                }
            }
        }
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public synchronized long getBalance() { return balance; }
    public boolean isActive() { return active; }
    public void close() { active = false; }

    @Override
    public String toString() { return accountNumber + " | " + ownerName + " | " + balance; }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Account account)) return false;
        return accountNumber.equals(account.accountNumber);
    }

    @Override
    public int hashCode() { return accountNumber.hashCode(); }

    @Override
    public int compareTo(Account other) { return accountNumber.compareTo(other.accountNumber); }
}
