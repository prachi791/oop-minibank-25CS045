import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DiscountApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Double> prices = new ArrayList<>(
            Arrays.asList(100.0, 250.0, 500.0, 1000.0)
        );

        System.out.println("===== DISCOUNT ENGINE =====");
        System.out.println("Original prices: " + prices);

        System.out.println("\nChoose a discount rule:");
        System.out.println("1. 10% Discount");
        System.out.println("2. 20% Discount");
        System.out.println("3. Flat Rs. 50 Discount");
        System.out.println("4. No Discount");
        System.out.print("Enter your choice: ");

        int choice = sc.nextInt();

        DiscountRule rule;

        switch (choice) {
            case 1:
                rule = DiscountRules.tenPercent();
                break;

            case 2:
                rule = DiscountRules.twentyPercent();
                break;

            case 3:
                rule = DiscountRules.flatFifty();
                break;

            case 4:
                rule = DiscountRules.noDiscount();
                break;

            default:
                System.out.println("Invalid choice!");
                sc.close();
                return;
        }

        List<Double> discountedPrices = prices.stream()
            .map(rule::apply)
            .toList();

        System.out.println("\nDiscounted prices:");
        for (int i = 0; i < prices.size(); i++) {
            System.out.printf(
                "Rs. %.2f -> Rs. %.2f%n",
                prices.get(i),
                discountedPrices.get(i)
            );
        }

        sc.close();
    }
}