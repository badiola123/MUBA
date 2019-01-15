package edu.eskola.muba.stats.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StatsId implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "PLAYERID")
	private int playerId;
	@Column(name = "GAMEID")
	private int gameId;
	
	public StatsId(int playerId, int gameId) {
		this.playerId = playerId;
		this.gameId = gameId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public int getGameId() {
		return gameId;
	}
}
