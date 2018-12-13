package bujny.GameMechanism;

public class Player {
	private float stamina=100.0f;
	private final int resistance;
	private final int height;
	private final int ballControl;
	private final int defence;
	private final int longShootSkill;
	private final int shortShootSkill;
	private int neededStatValue;
	static final float STAMINA_LOSS = 6.0f;
	
	public Player(int resistance, int height, int ballControl, int defence, int longShootSkill, int shortShootSkill) {
		this.resistance = resistance;
		this.height = height;
		this.ballControl = ballControl;
		this.defence = defence;
		this.longShootSkill = longShootSkill;
		this.shortShootSkill = shortShootSkill;
	}
	
	public void rest() {
		stamina=100;
	}
	
	public void staminaLoss() {
		stamina -= STAMINA_LOSS/resistance;
		if(stamina<10) stamina=10;
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

}
