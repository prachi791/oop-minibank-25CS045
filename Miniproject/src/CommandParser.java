package Miniproject.src;


public class CommandParser {

    public static Command parse(String line) {

        if (line == null || line.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Command cannot be empty.");
        }

        String[] parts = line.trim().split("\\s+");

        if (parts.length != 3) {
            throw new IllegalArgumentException(
                    "Command must contain: TYPE ACCOUNT_NUMBER AMOUNT");
        }

        TransactionType type;

        try {
            type = TransactionType.valueOf(
                    parts[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Invalid transaction type: " + parts[0]);
        }

        String accountNumber = parts[1];
        long amount;

        try {
            amount = Long.parseLong(parts[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Amount must be a whole number.");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be positive.");
        }

        return new Command(type, accountNumber, amount);
    }
}

