package view;

import static java.lang.System.out;
import java.util.Scanner;

import model.DeployedShip;

/**
 * View of the Battle
 * 
 * @author Simor
 * 
 */
public class BattleView {

	/**
	 * Shows the table
	 * 0 = Water (Hidden)
	 * 1 = Ship (Hidden)
	 * 2 = Water (Visible)
	 * 3 = Ship (Visible)
	 * 
	 * @param table
	 *            Matrix of the table
	 */
	public void showTable(String[][] table) {
		// Prints the Letters
		out.print(" ");
		for (char i = 'A'; i < 'K'; i++) {
			out.print(" " + i);
		}

		out.println("");

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				// Prints the numbers
				if (j == 0) {
					out.print(i + " ");
				}

				// Checks the symbol to print
				if (table[i][j] == "0" || table[i][j] == "1") {
					out.print(". ");
				} else if (table[i][j] == "2") {
					out.print("- ");
				} else if (table[i][j] == "3") {
					out.print("O ");
				}

			}
			out.println("");
		}
	}

	/**
	 * Asks the player to play
	 * 
	 * @return The coordinates to play
	 */
	public String play() {
		out.println("Digite as coordenadas (Ex.: B2): ");
		return new Scanner(System.in).next();
	}

	/**
	 * Show the GameOver message
	 */
	public void showGameOver() {
		out.println("Suas chances acabaram!");
		out.println("Game over.");
	}

	/**
	 * Show the "You win!" message
	 */
	public void showGameWin() {
		out.println("Você venceu o jogo!");
		out.println("Parabéns!");
	}

	/**
	 * Show an error message
	 * 
	 * @param msg
	 *            The message to be shown
	 */
	public void showErrorMessage(String msg) {
		out.println("#############");
		out.println(msg);
		out.println("#############");
	}

	/**
	 * Show a "miss" message
	 */
	public void showWaterMessage() {
		out.println("Você errou! Água!");
	}

	/**
	 * Shows a hit message
	 * @param ship Deployed ship that has hit
	 */
	public void showHitMessage(DeployedShip ship) {
		out.println("Você acertou um " + ship.getName());
	}
	
	/**
	 * Shows a "ship destroyed" message
	 * @param ship Deployed ship that has destroyed
	 */
	public void showDestroyedMessage(DeployedShip ship)
	{
		out.println("Você destruiu um " + ship.getName());
	}
	
	/**
	 * Show the count of points left
	 * @param count Number of points left
	 */
	public void showPointsLeft(int count)
	{
		out.println("Você tem " + count + " pontos restantes");
	}
}
