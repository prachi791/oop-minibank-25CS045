package Miniproject.src;


public record Command(
        TransactionType type,
        String accountNumber,
        long amount) {
}

