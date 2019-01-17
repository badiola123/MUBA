package edu.eskola.muba.game.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity of Game
 * 
 * @author MUBA team
 * @version Final version
 */
@Entity
@Table(name = "GAME")
public class Game {

	@Id
	@Column(name= "GAMEID", insertable = false, updatable = false)
	private int gameId;

	@Column(name = "GAMEDATE")
	private Date gameDate;
	
	@Column(name = "LOCALTEAMID")
	private int localTeamId;
	
	@Column(name = "VISITORTEAMID")
	private int visitorTeamId;
	
	@Column(name = "LEAGUEID")
	private int leagueId;
	
	@Column(name = "LOCALTEAMRESULT")
	private int localTeamResult;
	
	@Column(name = "VISITORTEAMRESULT")
	private int visitorTeamResult;
	
	@Column(name = "STAGE")
	private int stage;
	
	@Column(name = "ENLOGS")
	private String enLogs;
	
	@Column(name = "ESLOGS")
	private String esLogs;
	
	@Column(name = "BQLOGS")
	private String bqLogs;
	
	@Column(name = "PLAYED")
	private boolean played;
	
	
	/**
	 * Empty constructor for Game
	 */
	public Game() {}
	
	/**
	 * Game constructor with all fields
	 * 
	 * @param gameId The game ID
	 * @param gameDate The game date
	 * @param localTeamId The local team ID
	 * @param visitorTeamId The visitor team ID
	 * @param leagueId The league ID
	 * @param localTeamResult The local team points
	 * @param visitorTeamResult The visitor team points
	 * @param stage The stage of the game
	 * @param enLogs The English logs
	 * @param esLogs The Spanish logs
	 * @param bqLogs The Basque logs
	 * @param played States if the game has been played or not
	 */
	public Game(int gameId, Date gameDate, int localTeamId, int visitorTeamId, int leagueId, int localTeamResult, int visitorTeamResult, int stage, String enLogs, String esLogs, String bqLogs, boolean played) {
		super();
		this.gameId=gameId;
		this.gameDate=gameDate;
		this.localTeamId=localTeamId;
		this.visitorTeamId=visitorTeamId;
		this.leagueId=leagueId;
		this.localTeamResult=localTeamResult;
		this.visitorTeamResult=visitorTeamResult;
		this.stage=stage;
		this.enLogs=enLogs;
		this.esLogs=esLogs;
		this.bqLogs=bqLogs;
		this.played=played;
	}
	
	/**
	 * @return If the game has been played or not
	 */
	public boolean getPlayed() {
		return played;
	}

	/**
	 * @return The game ID
	 */
	public int getGameId() {
		return gameId;
	}

	/**
	 * @return The game date
	 */
	public Date getGameDate() {
		return gameDate;
	}

	/**
	 * @return The local team ID
	 */
	public int getLocalTeamId() {
		return localTeamId;
	}

	/**
	 * @return The visitor team ID
	 */
	public int getVisitorTeamId() {
		return visitorTeamId;
	}

	/**
	 * @return The league ID
	 */
	public int getLeagueId() {
		return leagueId;
	}

	/**
	 * @return The local team points
	 */
	public int getLocalTeamResult() {
		return localTeamResult;
	}

	/**
	 * @return The visitor team points
	 */
	public int getVisitorTeamResult() {
		return visitorTeamResult;
	}

	/**
	 * @return The stage of the game
	 */
	public int getStage() {
		return stage;
	}

	/**
	 * @return The English logs
	 */
	public String getEnLogs() {
		return enLogs;
	}

	/**
	 * @return The Spanish logs
	 */
	public String getEsLogs() {
		return esLogs;
	}

	/**
	 * @return The Basque logs
	 */
	public String getBqLogs() {
		return bqLogs;
	}

	
}

