package controller;

import java.util.ArrayList;
import java.util.Random;

import model.*;
import view.*;

public class BattleController {
	ArrayList<Ship> lstShip;
	String[][] table = new String[10][10];
	
	/**
	 * Constructor
	 */
	public BattleController()
	{
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
		
		for(int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				table[i][j] = "0";
			}
		}
	}
	
	/**
	 * Sets the ships in the table
	 */
	private void setTable()
	{
		boolean flagOk = true;
		for (Ship item : lstShip)
		{
			while (flagOk)
			{
				boolean horizontal = (new Random()).nextBoolean();
				int initPos = (new Random()).nextInt(9-item.getSize());
			}
		}
	}
}
