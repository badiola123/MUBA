package edu.eskola.muba.gameMechanics;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.eskola.muba.characteristics.entity.Characteristics;
import edu.eskola.muba.characteristics.service.CharacteristicsService;
import edu.eskola.muba.config.AppConfig;
import edu.eskola.muba.player.entity.Player;
import edu.eskola.muba.player.service.PlayerService;

public class PlayerGame {
	private float stamina=100.0f;
	private final int resistance;
	private final int height;
	private final int ballControl;
	private final int defence;
	private final int longShootSkill;
	private final int shortShootSkill;
	private int neededStatValue;
	static final float STAMINA_LOSS = 6.0f;

	public PlayerGame(Characteristics stat) {
		this.resistance = stat.getResistance();
		this.height = (int) (stat.getHeight()/2.5);
		this.ballControl = stat.getBallControl();
		this.defence = stat.getDefense();
		this.longShootSkill = stat.getLongShoot();
		this.shortShootSkill = stat.getShortShoot();
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
