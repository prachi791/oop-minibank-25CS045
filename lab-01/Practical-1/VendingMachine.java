import java.util.Scanner;

enum Coin {
    ONE(1), TWO(2), FIVE(5), TEN(10);
    private final int value;
    Coin(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}

public class VendingMachine {
    public static void main(String[] args) {
        final int PRICE = 15;
        int total = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Vending Machine!");
        System.out.println("Insert coins (ONE, TWO, FIVE, TEN) to reach " + PRICE + ".");

        while (total < PRICE) {
            System.out.print("Enter coin: ");
            String input = sc.nextLine().toUpperCase();
            try {
                Coin coinInserted = Coin.valueOf(input);
                total += coinInserted.getValue();
                System.out.println("Inserted: " + coinInserted.getValue() + ". Total: " + total);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid coin! Please enter ONE, TWO, FIVE, or TEN.");
            }
        }
        System.out.println("Total reached: " + total + ". Item dispensed! Thank you!");
        sc.close();
    }
}