import java.util.Scanner;
import java.util.Random;

public class GameOfNumberGuessing {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();

        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 10;
        int score = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        do {
            int GeneratedNumber = rd.nextInt(maxRange - minRange + 1) + minRange;
            int attempts = 0;
            int YourGuess;

            System.out.println("\nI have selected a number between " + minRange + " and " + maxRange + ". Guess it!");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                YourGuess = sc.nextInt();
                attempts++;

                if (YourGuess == GeneratedNumber) {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    score++;
                    break;
                } else if (YourGuess < GeneratedNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (attempts == maxAttempts) {
                System.out.println("Sorry, you've run out of attempts. The correct number was: " + GeneratedNumber);
            }

            System.out.print("Do you want to play again? (yes/no): ");
        } while (sc.next().equalsIgnoreCase("yes"));

        System.out.println("Thanks for playing! Your final score is: " + score);

        sc.close();
    }
}

