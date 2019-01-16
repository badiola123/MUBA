package edu.eskola.muba.stats.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

	public int getPlayerId() {
		return playerId;
	}

	public int getGameId() {
		return gameId;
	}

	public int getTwoPointsScored() {
		return twoPointsScored;
	}

	public int getTwoPointsShot() {
		return twoPointsShot;
	}

	public int getThreePointsScored() {
		return threePointsScored;
	}

	public int getThreePointsShot() {
		return threePointsShot;
	}

	public int getOffRebound() {
		return offRebound;
	}

	public int getDeffRebound() {
		return deffRebound;
	}

	public int getSteals() {
		return steals;
	}

	public int getBlocks() {
		return blocks;
	}

}
