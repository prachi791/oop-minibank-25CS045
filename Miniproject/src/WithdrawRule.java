package Miniproject.src;

@FunctionalInterface
public interface WithdrawRule {
    boolean allow(Account account, long amount);
}