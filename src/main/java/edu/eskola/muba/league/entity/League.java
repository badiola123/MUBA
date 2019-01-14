package edu.eskola.muba.league.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "LEAGUE")
public class League {

	@Id
	@Column(name= "LEAGUEID", insertable = false, updatable = false)
	private int leagueId;
	
	@Column(name= "HOSTTEAM")
	private int hostTeam;
	
	@Column(name= "STARTED")
	private boolean started;

	@Column(name = "STARTDATE")
	private Date startDate;
	
	@Column(name = "ENDDATE")
	private Date endDate;
	
	@Column(name = "LEAGUENAME")
	private String leagueName;
	
	@Column(name = "LEAGUEDESC")
	private String leagueDesc;
	
	@Column(name = "STAGES")
	private int stages;
	
	@Column(name = "WINNERTEAM")
	private int winnerTeam;
	
	public League() {}
	
	public League(int leagueId, int hostTeam, boolean started, Date startDate, Date endDate, String leagueName, String leagueDesc, int stages, int winnerTeam) {
		super();
		this.leagueId=leagueId;
		this.hostTeam=hostTeam;
		this.started=started;
		this.startDate=startDate;
		this.endDate=endDate;
		this.leagueName=leagueName;
		this.leagueDesc=leagueDesc;
		this.stages=stages;
		this.winnerTeam=winnerTeam;
	}

	public int getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}
	
	public int getHostTeam() {
		return hostTeam;
	}

	public void setHostTeam(int hostTeam) {
		this.hostTeam = hostTeam;
	}
	
	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	public String getLeagueDesc() {
		return leagueDesc;
	}

	public void setLeagueDesc(String leagueDesc) {
		this.leagueDesc = leagueDesc;
	}
	
	public int getStages() {
		return stages;
	}

	public void setStages(int stages) {
		this.stages = stages;
	}
	
	public int getWinnerTeam() {
		return winnerTeam;
	}

	public void setWinnerTeam(int winnerTeam) {
		this.winnerTeam = winnerTeam;
	}
	
}

