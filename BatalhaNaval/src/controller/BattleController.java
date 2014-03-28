package controller;

import java.util.ArrayList;
import java.util.Random;

import model.*;
import view.*;

public class BattleController {
	ArrayList<Ship> lstShip;
	String[][] table = new String[10][10];
	BattleView view;

	/**
	 * Constructor
	 */
	public BattleController() {
		lstShip = new ArrayList<Ship>();

		lstShip.add(new Ship("Porta-aviões", 5));
		lstShip.add(new Ship("Destroyer", 4));
		lstShip.add(new Ship("Destroyer", 4));
		lstShip.add(new Ship("Fragata", 3));
		lstShip.add(new Ship("Fragata", 3));
		lstShip.add(new Ship("Torpedeiro", 2));
		lstShip.add(new Ship("Torpedeiro", 2));
		lstShip.add(new Ship("Torpedeiro", 2));
		lstShip.add(new Ship("Submarino", 1));
		lstShip.add(new Ship("Submarino", 1));
		lstShip.add(new Ship("Submarino", 1));
		lstShip.add(new Ship("Submarino", 1));
		lstShip.add(new Ship("Submarino", 1));

		view = new BattleView();

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				table[i][j] = "0";
			}
		}

		setTable();
	}

	/**
	 * Shows the table of the game
	 */
	public void showTable() {
		view.showTable(this.table);
	}

	/**
	 * Sets the ships in the table
	 */
	private void setTable() {
		boolean flagOk = false;
		for (Ship item : lstShip) {
			flagOk = false;
			while (!flagOk) {
				// Gets some random numbers for positioning
				boolean horizontal = (new Random()).nextBoolean();
				int initPosDyn = (new Random()).nextInt(10 - item.getSize());
				int initPosFix = (new Random()).nextInt(10);

				// Verifies if the ship can be deployed in the Initial Position
				flagOk = checkShip(initPosFix, initPosDyn, horizontal,
						item.getSize());

				// If the ship can be deployed, deploy it
				if (flagOk) {
					deployShip(initPosFix, initPosDyn, horizontal,
							item.getSize());
				}
			}
		}
	}

	/**
	 * Check if the ship can be deployed
	 * 
	 * @param initPosFix
	 *            Initial "fixed" position
	 * @param initPosDin
	 *            Initial "dynamic" position
	 * @param horizontal
	 *            Horizontal or vertical
	 * @param itemSize
	 *            The size of the Ship
	 * @return If the ship can be deployed
	 */
	private boolean checkShip(int initPosFix, int initPosDyn,
			boolean horizontal, int itemSize) {
		boolean flag = true;
		// Horizontally
		if (horizontal) {
			if (initPosDyn + itemSize <= 9) {
				for (int i = initPosDyn; i < 10 - itemSize; i++) {
					if (table[i][initPosFix] == "1") {
						flag = false;
						break;
					}
				}
			} else {
				flag = false;
			}
		}
		// Vertically
		else {
			if (initPosDyn + itemSize <= 9) {
				for (int i = initPosDyn; i < 10 - itemSize; i++) {
					if (table[initPosFix][i] == "1") {
						flag = false;
						break;
					}
				}
			}
			else
			{
				flag = false;
			}
		}

		return flag;
	}

	/**
	 * Deploy the ship within the coordinates
	 * 
	 * @param initPosFix
	 *            Initial "fixed" position
	 * @param initPosDin
	 *            Initial "dynamic" position
	 * @param horizontal
	 *            Horizontal or vertical
	 * @param itemSize
	 *            The size of the Ship
	 */
	private void deployShip(int initPosFix, int initPosDyn, boolean horizontal,
			int itemSize) {
		// Horizontally
		if (horizontal) {
			for (int i = initPosDyn; i < initPosDyn + itemSize; i++) {
				table[i][initPosFix] = "1";
			}
		}
		// Vertically
		else {
			for (int i = initPosDyn; i < initPosDyn + itemSize; i++) {
				table[initPosFix][i] = "1";
			}
		}
	}
}
