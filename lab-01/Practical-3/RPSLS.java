
import java.util.Scanner;
import java.util.Random;

enum Move {
    ROCK, PAPER, SCISSORS, LIZARD, SPOCK
}

public class RPSLS {

    static int winner(Move a, Move b) {
        if (a == b) {
            return 0;
        }

        return switch (a) {
            case ROCK ->
                (b == Move.SCISSORS || b == Move.LIZARD) ? 1 : -1;

            case PAPER ->
                (b == Move.ROCK || b == Move.SPOCK) ? 1 : -1;

            case SCISSORS ->
                (b == Move.PAPER || b == Move.LIZARD) ? 1 : -1;

            case LIZARD ->
                (b == Move.SPOCK || b == Move.PAPER) ? 1 : -1;

            case SPOCK ->
                (b == Move.SCISSORS || b == Move.ROCK) ? 1 : -1;
        };
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        int playerScore = 0;
        int computerScore = 0;

        System.out.println("=== Rock Paper Scissors Lizard Spock ===");
        System.out.println("Enter ROCK, PAPER, SCISSORS, LIZARD, or SPOCK.");

        for (int i = 1; i <= 5; i++) {
            System.out.println("\nRound " + i);
            System.out.print("Enter your move: ");

            String input = sc.next().toUpperCase();
            Move player;

            try {
                player = Move.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid move! Try again.");
                i--;
                continue;
            }

            Move computer = Move.values()[r.nextInt(5)];

            System.out.println("You: " + player);
            System.out.println("Computer: " + computer);

            int result = winner(player, computer);

            if (result == 1) {
                System.out.println("You win this round!");
                playerScore++;
            } else if (result == -1) {
                System.out.println("Computer wins this round!");
                computerScore++;
            } else {
                System.out.println("Round tie!");
            }

            System.out.println("Score: You " + playerScore
                    + " - " + computerScore + " Computer");
        }

        System.out.println("\n=== Final Result ===");

        if (playerScore > computerScore) {
            System.out.println("You win the match!");
        } else if (computerScore > playerScore) {
            System.out.println("Computer wins the match!");
        } else {
            System.out.println("The match is a tie!");
        }

        System.out.println("Final Score: You " + playerScore
                + " - " + computerScore + " Computer");

        sc.close();
    }
}
