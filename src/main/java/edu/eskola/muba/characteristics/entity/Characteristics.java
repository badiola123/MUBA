package edu.eskola.muba.characteristics.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity of Characteristics
 * 
 * @author MUBA team
 * @version Final version
 */
@Entity
@Table(name = "CHARACTERISTICS")
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
	
	/**
	 * Empty constructor for Characteristics
	 */
	public Characteristics() {}

	/**
	 * Characteristics constructor with all fields
	 * 
	 * @param resistance The resistance characteristic value
	 * @param height The height characteristic value
	 * @param ballControl The ballControl characteristic value
	 * @param defense The defense characteristic value
	 * @param longShoot The longShoot characteristic value
	 * @param shortShoot The shortShoot characteristic value
	 * @param age The age characteristic value
	 * @param cDate The date in which the characteristic has been registered
	 * @param playerId The player to which the characteristics are about
	 */
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
	
	/**
	 * @return Characteristics ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return Resistance value
	 */
	public int getResistance() {
		return resistance;
	}

	/**
	 * @return Height value
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return Ball controll value
	 */
	public int getBallControl() {
		return ballControl;
	}

	/**
	 * @return Defense value
	 */
	public int getDefense() {
		return defense;
	}

	/**
	 * @return Longo shoot value
	 */
	public int getLongShoot() {
		return longShoot;
	}

	/**
	 * @return Short Shoot value
	 */
	public int getShortShoot() {
		return shortShoot;
	}

	/**
	 * @return Age value
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @return Registered date value
	 */
	public Date getcDate() {
		return cDate;
	}

	/**
	 * @return Corresponding player characteristics ID
	 */
	public int getPlayerId() {
		return playerId;
	}

}
