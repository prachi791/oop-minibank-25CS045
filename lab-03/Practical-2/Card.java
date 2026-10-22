
class Card {
    private String rank;
    private String suit;

    Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String toString() {
        return rank + " of " + suit;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Card)) {
            return false;
        }

        Card o = (Card) obj;

        if (rank.equals(o.rank) && suit.equals(o.suit)) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return 31 * rank.hashCode() + suit.hashCode();
    }
}
