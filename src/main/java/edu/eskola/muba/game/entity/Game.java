package edu.eskola.muba.game.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	
	
	public Game() {}
	
	public Game(int gameId, Date gameDate, int localTeamId, int visitorTeamId, int leagueId, int localTeamResult, int visitorTeamResult, int stage, String enLogs, String esLogs, String bqLogs) {
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
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public Date getGameDate() {
		return gameDate;
	}

	public void setGameDate(Date gameDate) {
		this.gameDate = gameDate;
	}

	public int getLocalTeamId() {
		return localTeamId;
	}

	public void setLocalTeamId(int localTeamId) {
		this.localTeamId = localTeamId;
	}

	public int getVisitorTeamId() {
		return visitorTeamId;
	}

	public void setVisitorTeamId(int visitorTeamId) {
		this.visitorTeamId = visitorTeamId;
	}

	public int getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}

	public int getLocalTeamResult() {
		return localTeamResult;
	}

	public void setLocalTeamResult(int localTeamResult) {
		this.localTeamResult = localTeamResult;
	}

	public int getVisitorTeamResult() {
		return visitorTeamResult;
	}

	public void setVisitorTeamResult(int visitorTeamResult) {
		this.visitorTeamResult = visitorTeamResult;
	}
	
	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}
	
}

