package model;

import exception.InsufficientFundsException;
import exception.InvalidAmountException;

@FunctionalInterface
public interface WithdrawRule {
    boolean allow(Account account, long amount) throws InsufficientFundsException, InvalidAmountException;
}
