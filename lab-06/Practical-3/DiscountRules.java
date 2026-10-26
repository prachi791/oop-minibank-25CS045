public class DiscountRules {

    public static DiscountRule tenPercent() {
        return price -> price * 0.90;
    }

    public static DiscountRule twentyPercent() {
        return price -> price * 0.80;
    }

    public static DiscountRule flatFifty() {
        return price -> Math.max(0, price - 50);
    }

    public static DiscountRule noDiscount() {
        return price -> price;
    }
}