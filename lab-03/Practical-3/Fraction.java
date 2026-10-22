
class Fraction {
    private int numerator;
    private int denominator;

    Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero");
        }

        this.numerator = numerator;
        this.denominator = denominator;
    }

    public String toString() {
        return numerator + "/" + denominator;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Fraction)) {
            return false;
        }

        Fraction f = (Fraction) obj;

        return numerator * f.denominator
                == f.numerator * denominator;
    }

    public int hashCode() {
        return 31 * numerator + denominator;
    }
}
