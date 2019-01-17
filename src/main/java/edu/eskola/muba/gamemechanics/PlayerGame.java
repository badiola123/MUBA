package edu.eskola.muba.gamemechanics;

import edu.eskola.muba.characteristics.entity.Characteristics;

public class PlayerGame {
	private float stamina=100.0f;
	private final int resistance;
	private final int height;
	private final int ballControl;
	private final int defence;
	private final int longShootSkill;
	private final int shortShootSkill;
	private final int playerId;
	private int neededStatValue;
	static final float STAMINA_LOSS = 6.0f;
	
	private int twoPointsScored;
	private int twoPointsShot;
	private int threePointsScored;
	private int threePointsShot;
	private int offRebound;
	private int deffRebound;
	private int steals;
	private int blocks;

	public PlayerGame(Characteristics stat) {
		this.playerId = stat.getPlayerId();
		this.resistance = stat.getResistance();
		this.height = (int) (stat.getHeight()/2.5);
		this.ballControl = stat.getBallControl();
		this.defence = stat.getDefense();
		this.longShootSkill = stat.getLongShoot();
		this.shortShootSkill = stat.getShortShoot();
		this.twoPointsScored=0;
		this.twoPointsShot=0;
		this.threePointsScored=0;
		this.threePointsShot=0;
		this.offRebound=0;
		this.deffRebound=0;
		this.steals=0;
		this.blocks=0;
	}
	
	public void rest() {
		stamina=100;
	}
	
	public void staminaLoss() {
		stamina -= STAMINA_LOSS/resistance;
		if(stamina<10) stamina=10;
	}
	
	public int getPlayerId() {
		return playerId;
	}
	
	public int getNeededStatValue() {
		return neededStatValue;
	}

	public void setNeededStatValue(int neededStatValue) {
		this.neededStatValue = neededStatValue;
	}

	public int getHeight() {
		return height;
	}

	public int getBallControl() {
		return (int) (ballControl*stamina/100);
	}

	public int getDefence() {
		return (int) (defence*stamina/100);
	}

	public int getLongShootSkill() {
		return (int) (longShootSkill*stamina/100);
	}

	public int getShortShootSkill() {
		return (int) (shortShootSkill*stamina/100);
	}
	
	public float getStamina() {
		return stamina;
	}
	
	public int getTwoPointsScored() {
		return twoPointsScored;
	}

	public void increaseTwoPointsScored() {
		twoPointsScored++;
	}

	public int getTwoPointsShot() {
		return twoPointsShot;
	}

	public void increaseTwoPointsShot() {
		twoPointsShot++;
	}

	public int getThreePointsScored() {
		return threePointsScored;
	}

	public void increaseThreePointsScored() {
		threePointsScored++;
	}

	public int getThreePointsShot() {
		return threePointsShot;
	}

	public void increaseThreePointsShot() {
		threePointsShot++;
	}

	public int getOffRebound() {
		return offRebound;
	}

	public void increaseOffRebound() {
		offRebound++;
	}

	public int getDeffRebound() {
		return deffRebound;
	}

	public void increaseDeffRebound() {
		deffRebound++;
	}

	public int getSteals() {
		return steals;
	}

	public void increaseSteals() {
		steals++;
	}

	public int getBlocks() {
		return blocks;
	}

	public void increaseBlocks() {
		blocks++;
	}

}