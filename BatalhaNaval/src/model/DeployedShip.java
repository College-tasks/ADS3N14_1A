package model;

import java.util.ArrayList;

public class DeployedShip extends Ship {
	private ArrayList<String> deployedCoord = new ArrayList<String>();
	private int hitCount;

	/**
	 * Constructor
	 * @param name Name of the ship
	 * @param size Size of the ship
	 */
	public DeployedShip(String name, int size)
	{
		super(name, size);
		this.hitCount = 0;
	}
	
	public ArrayList<String> getDeployedCoord() {
		return deployedCoord;
	}

	public void setDeployedCoord(ArrayList<String> deployedCoord) {
		this.deployedCoord = deployedCoord;
	}
	
	public void addDeployedCoord(String coord)
	{
		this.deployedCoord.add(coord);
	}
	
	public int getHitCount() {
		return hitCount;
	}

	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}
}
