package util;

import model.Account;

public class StatementFormatter {
    public static String buildStatement(Account account) {
        StringBuilder result = new StringBuilder();
        result.append("===== MINI BANK STATEMENT =====\n");
        result.append("Account Number: ").append(account.getAccountNumber()).append('\n');
        result.append("Account Holder: ").append(account.getOwnerName()).append('\n');
        result.append("Balance: Rs. ").append(account.getBalance()).append('\n');
        result.append("Account Status: ").append(account.isActive() ? "Active" : "Inactive");
        return result.toString();
    }
}
