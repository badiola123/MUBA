package edu.eskola.muba.stats.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity of Stats
 *
 * @author MUBA team
 * @version Final version
 */
@Entity
@Table(name = "STATS")
public class Stats implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PLAYERID", insertable = false, updatable = false)
	private int playerId;

	@Id
	@Column(name = "GAMEID", insertable = false, updatable = false)
	private int gameId;

	/**
	 * Empty constructor for Stats
	 */
	public Stats() {}
	
	/**
	 * Statistics constructor with all fields
	 * 
	 * @param playerId The player which the stats correspond to
	 * @param gameId The game in which the stats have been stored
	 * @param twoPointsScored Two point shoots scored
	 * @param twoPointsShot Two point shoots attempted
	 * @param threePointsScored Three point shoots scored
	 * @param threePointsShot Three point shoots attempted
	 * @param offRebound Offensive rebounds
	 * @param deffRebound Deffensive rebounds
	 * @param steals Steals
	 * @param blocks Blocks
	 */
	public Stats(int playerId, int gameId, int twoPointsScored, int twoPointsShot, int threePointsScored,
			int threePointsShot, int offRebound, int deffRebound, int steals, int blocks) {
		this.playerId = playerId;
		this.gameId = gameId;
		this.twoPointsScored = twoPointsScored;
		this.twoPointsShot = twoPointsShot;
		this.threePointsScored = threePointsScored;
		this.threePointsShot = threePointsShot;
		this.offRebound = offRebound;
		this.deffRebound = deffRebound;
		this.steals = steals;
		this.blocks = blocks;
	}

	@Column(name = "TWOPOINTSCORED")
	private int twoPointsScored;

	@Column(name = "TWOPOINTSHOT")
	private int twoPointsShot;

	@Column(name = "THREEPOINTSCORED")
	private int threePointsScored;

	@Column(name = "THREEPOINTSHOT")
	private int threePointsShot;

	@Column(name = "OFFREBOUND")
	private int offRebound;

	@Column(name = "DEFFREBOUND")
	private int deffRebound;

	@Column(name = "STEALS")
	private int steals;

	@Column(name = "BLOCKS")
	private int blocks;

	/**
	 * @return Corresponding player ID
	 */
	public int getPlayerId() {
		return playerId;
	}

	/**
	 * @return Game ID of the stats
	 */
	public int getGameId() {
		return gameId;
	}

	/**
	 * @return Scored two point shoots value
	 */
	public int getTwoPointsScored() {
		return twoPointsScored;
	}

	/**
	 * @return Attempted two point shoots value
	 */
	public int getTwoPointsShot() {
		return twoPointsShot;
	}

	/**
	 * @return Scored three point shoots value
	 */
	public int getThreePointsScored() {
		return threePointsScored;
	}

	/**
	 * @return Attempted three point shoots value
	 */
	public int getThreePointsShot() {
		return threePointsShot;
	}

	/**
	 * @return Offensive rebounds value
	 */
	public int getOffRebound() {
		return offRebound;
	}

	/**
	 * @return Defensive rebounds value
	 */
	public int getDeffRebound() {
		return deffRebound;
	}

	/**
	 * @return Steals value
	 */
	public int getSteals() {
		return steals;
	}

	/**
	 * @return Blocks value
	 */
	public int getBlocks() {
		return blocks;
	}

}
