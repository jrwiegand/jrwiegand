package com.android.google.gametracker;

public class Player {
	
	private String playerName = null;
	private int playerNumber = -1;
	private String position = null;
	private String positionInit = null;
	
	/**
	 * Constructor for the class. Create a player that has all the following
	 * parameters.
	 * 
	 * @param name - name of the player
	 * @param num - number of the player
	 * @param pos - position of the player
	 * @param posInit - position's initials 
	 */
	public Player(String name, int num, String pos, String posInit){
		
		playerName = name;
		playerNumber = num;
		position = pos;
		positionInit = posInit;
	}
	
	/**
	 * Getter mthods for the class.
	 */
	public String getPlayerName() {
		return this.playerName;
	}

	public int getPlayerNumber() {
		return this.playerNumber;
	}
	
	public String getPosition() {
		return this.position;
	}
	
	public String getPositionInit() {
		return this.positionInit;
	}
	
	/**
	 * Setter methods for the class.
	 */
	public void setPlayerName(String name) {
		playerName = name;
	}

	public void setPlayerNumber(int number) {
		playerNumber = number;
	}
	
	public void setPosition(String pos) {
		position = pos;
	}
	
	public void setPositionInit(String posInit) {
		positionInit = posInit;
	}
}
