package edu.eskola.muba.characteristics.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Characteristics")
public class Characteristics {
	
	@Id
	@Column(name= "CHARACTERISTICSID", insertable = false, updatable = false)
	private int id;
	
	@Column(name = "RESISTANCE")
	private int resistance;
	
	@Column(name = "HEIGHT")
	private int height;
	
	@Column(name = "BALLCONTROL")
	private int ballControl;
	
	@Column(name = "DEFENSE")
	private int defense;
	
	@Column(name = "LONGSHOOT")
	private int longShoot;
	
	@Column(name = "SHORTSHOOT")
	private int shortShoot;
	
	@Column(name = "AGE")
	private int age;
	
	@Column(name = "CHARACTERISTICSDATE")
	private Date cDate;
	
	@Column(name = "PLAYERID")
	private int playerId;
	
	public Characteristics() {}

	public Characteristics(int resistance, int height, int ballControl, int defense, int longShoot, int shortShoot,
			int age, Date cDate, int playerId) {
		super();
		this.resistance = resistance;
		this.height = height;
		this.ballControl = ballControl;
		this.defense = defense;
		this.longShoot = longShoot;
		this.shortShoot = shortShoot;
		this.age = age;
		this.cDate = cDate;
		this.playerId = playerId;
	}

	public int getId() {
		return id;
	}


	public int getResistance() {
		return resistance;
	}


	public int getHeight() {
		return height;
	}


	public int getBallControl() {
		return ballControl;
	}


	public int getDefense() {
		return defense;
	}


	public int getLongShoot() {
		return longShoot;
	}


	public int getShortShoot() {
		return shortShoot;
	}


	public int getAge() {
		return age;
	}


	public Date getcDate() {
		return cDate;
	}

	
	public int getPlayerId() {
		return playerId;
	}

}
