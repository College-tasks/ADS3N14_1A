package controller;

import java.util.ArrayList;
import java.util.Random;

import model.*;
import utils.Utils;
import view.*;

public class BattleController {
	ArrayList<Ship> lstShip;
	ArrayList<DeployedShip> lstDeployed;
	String[][] table = new String[10][10];
	BattleView view;
	int chances;

	/**
	 * Constructor
	 */
	public BattleController() {
		lstShip = new ArrayList<Ship>();
		lstDeployed = new ArrayList<DeployedShip>();
		chances = 15;

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
		String coord = view.play();
		if (coord.length() == 2)
		{
			shoot(coord);
		} else {
			view.showErrorMessage("Digite uma coordenada correta!");
			showTable();
		}
		
	}
	
	/**
	 * Shoot!
	 * @param coord Coordinate of the shot
	 */
	private void shoot(String coord)
	{
		int x = Integer.parseInt(coord.substring(1, 2));
		int y = Utils.convertLetterToInt(coord.charAt(0));
		
		String value = table[x][y];
		
		if (value.equals("0"))
		{
			table[x][y] = "2";
			this.chances--;
			view.showWaterMessage();
			view.showPointsLeft(this.chances);
			
		} else if(value.equals("1"))
		{
			table[x][y] = "3";
			this.chances--;
			this.chances += 3;
			hitShip(x, y);
			view.showPointsLeft(this.chances);
		}
		else
		{
			view.showErrorMessage("Coordenada já usada!");
		}
		checkStatus();
		showTable();
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
							item.getSize(), item.getName());
				}
			}
		}
	}

	/**
	 * Checks if the ship can be deployed
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
	private boolean checkShip(int initPosFix, int initPosDyn, boolean horizontal, int itemSize) {
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
	 * Deploys the ship within the coordinates
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
	private void deployShip(int initPosFix, int initPosDyn, boolean horizontal, int itemSize, String itemName) {
		DeployedShip deployed = new DeployedShip(itemName, itemSize);
		// Horizontally
		if (horizontal) {
			for (int i = initPosDyn; i < initPosDyn + itemSize; i++) {
				table[i][initPosFix] = "1";
				deployed.addDeployedCoord(i + "" + initPosFix);
			}
		}
		// Vertically
		else {
			for (int i = initPosDyn; i < initPosDyn + itemSize; i++) {
				table[initPosFix][i] = "1";
				deployed.addDeployedCoord(initPosFix + "" + i);
			}
		}
		
		lstDeployed.add(deployed);
	}

	/**
	 * Verifies which ship has been hit
	 * @param x Coordinate X
	 * @param y Coordinate Y
	 */
	private void hitShip(int x, int y)
	{
		// For each deployed ship
		for (DeployedShip item : lstDeployed)
		{
			// For each coordinate of the deployed ship
			for (String item2 : item.getDeployedCoord())
			{
				// If found, it's the ship that got the hit 
				if (item2.equals(x + "" + y)){
					item.setHitCount(item.getHitCount()+1);
					view.showHitMessage(item);
					if (item.getHitCount() == item.getSize())
					{
						this.chances += 5;
						view.showDestroyedMessage(item);
					}
					break;
				}
			}
			
		}
	}
	
	/**
	 * Checks if the player have Won or Lost
	 */
	private void checkStatus()
	{
		checkGameWin();
		checkGameOver();
	}
	
	/**
	 * Checks if the player have lost the game
	 */
	private void checkGameOver()
	{
		if(chances == 0)
		{
			view.showGameOver();
			System.exit(0);
		}
	}

	/**
	 * Checks if the player have won que game
	 */
	private void checkGameWin()
	{
		boolean flagOk = true;
		for(DeployedShip item : lstDeployed)
		{
			if (item.getSize() != item.getHitCount())
			{
				flagOk = false;
				break;
			}
		}
		
		if (flagOk)
		{
			view.showGameWin();
			System.exit(0);
		}
	}

	
}
