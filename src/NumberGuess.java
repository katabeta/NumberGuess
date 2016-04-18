/**
 * 
 */
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Irina Lavryonova
 *
 */
public class NumberGuess {

	// For making guessCheckes
	private Random rnd = new Random();
	// For asking the user
	private Scanner scan = new Scanner(System.in);

	/*
	 * For keeping track of user's choices and vitalities
	 */
	private int currNum;
	private int lives = 3;
	private boolean playAgain = true;
	private int repeat;
	private boolean wrong;

	// For not using static everywhere
	private static NumberGuess game = new NumberGuess();

	/**
	 * Introduces the game to the player. Keeps the game in a loop that is
	 * dependent on whether the user wants to play again. Outside of the loop
	 * (when the user specifies that they no longer wish to play), the program
	 * breaks and cleans up after itself.
	 * 
	 * @param args
	 *            doesn't do anything for the purposes of this game
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to NumberGuess(TM)! In this game, the player has to guess the randomly generated\n"
				+ "number. The number is in the range of 1 through 9 (both inclusive). Every time the player guesses wrong,\n"
				+ "a life is taken; every time the player guesses right, a life is added. The player\n"
				+ "starts out with 3 lives. When the player guesses wrong, the repeat guess is for the same number.\n\n\n\n");
		while (game.playAgain) {
			game.currNum = game.genRandom();

			//			/*
			//			 * testing
			//			 */
			//			System.out.println(game.currNum);
			//			/*
			//			 * testing
			//			 */

			game.guessCheck(game.currNum);
		}
		game.scan.close();
		return;
	}

	/**
	 * Generates a new number between 1 and 9 (both inclusive)
	 */
	private int genRandom() {
		// Exclude zero and include nine
		return rnd.nextInt(9) + 1;
	}

	/**
	 * Asks the user if they wish to play again. This information is used in the
	 * while loop in main. It catches the user if they enter an integer that is
	 * not 1 or 2 (yes or no), but does not catch it if they enter a String. If
	 * the user enters the wrong integer, the program asks them to answer again,
	 * using only 1 or 2.
	 */
	private void repeat() {
		System.out.println("\n\nDo you want to play again?" + "\n1: Yes"
				+ "\n2: No\n");
		try{
			repeat = scan.nextInt();
		} catch(InputMismatchException ex){
			wrong = true;
		}
		if (repeat == 1) {
			this.playAgain = true;
			this.lives = 3;
		} else if (repeat == 2) {
			this.playAgain = false;
		} else if (wrong){
			System.out.println("Error! I could not catch that! Make sure that you are answering either 1 or 2\n");
			repeat();
		} else {
			System.out.println("I could not catch that! Make sure that you are answering either 1 or 2\n");
			repeat();
		}
	}

	/**
	 * Asks the user to input their guess and checks their guess again the
	 * current random number. If the user guesses right, adds 1 life to the
	 * user's lives. If the user guesses wrong and has more than 0 lives left,
	 * subtracts a life and asks the user to guess for the same number. If the
	 * user has no lives left, runs repeat(); to ask the user if they want to
	 * play again.
	 * 
	 * @param currNum
	 *            the current random number that the user is guessChecking for
	 */
	private void guessCheck(int currNum) {
		System.out.println("\nMake your guess: ");
		int guessCheck = scan.nextInt();
		if (currNum == guessCheck) {
			this.lives++;
			System.out.println("You guessed right");
			System.out.println("Lives: " + this.lives);
			System.out.println();
		} else {
			if (this.lives > 1) {
				lives--;
				System.out.println("You guessed wrong");
				System.out.println("Lives: " + this.lives);
				System.out.println();
				guessCheck(currNum);
			} else if (this.lives == 0) {
				System.out.println("Your ran out of lives!\n");
				repeat();
			} else {
				repeat();
			}
		}
	}

}
