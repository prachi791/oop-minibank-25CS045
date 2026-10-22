
class Driver {
    public static void main(String[] args) {

        Card c1 = new Card("Ace", "Spades");
        Card c2 = new Card("King", "Hearts");
        Card c3 = new Card("Queen", "Diamonds");
        Card c4 = new Card("Ace", "Spades");
        Card c5 = new Card("Jack", "Clubs");

        Card[] cards = {c1, c2, c3, c4, c5};

        for (int i = 0; i < cards.length; i++) {
            for (int j = 0; j < i; j++) {

                if (cards[i].equals(cards[j])) {
                    System.out.println("Duplicate found: " + cards[i]);
                    break;
                }
            }
        }
    }
}
