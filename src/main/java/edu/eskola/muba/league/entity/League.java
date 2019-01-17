package edu.eskola.muba.league.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

/**
 * Entity of League
 * 
 * @author MUBA team
 * @version Final version
 */
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
	
	/**
	 * Empty constructor for League
	 */
	public League() {}
	
	/**
	 * League constructor with all fields
	 * 
	 * @param leagueId The league ID
	 * @param hostTeam The host team ID of the league
	 * @param started Checker to know if the league has started
	 * @param startDate League starting date
	 * @param endDate League ending date
	 * @param leagueName League name
	 * @param leagueDesc League description
	 * @param stages Number of stages of the league
	 * @param winnerTeam Winner team ID of the league
	 */
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

	/**
	 * 
	 * @return The league ID
	 */
	public int getLeagueId() {
		return leagueId;
	}
	
	/**
	 * 
	 * @return The host team ID
	 */
	public int getHostTeam() {
		return hostTeam;
	}
	
	/**
	 * 
	 * @return If the league has started
	 */
	public boolean isStarted() {
		return started;
	}

	/**
	 * 
	 * @return The league starting date
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * 
	 * @return The league ending date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 
	 * @return The league name
	 */
	public String getLeagueName() {
		return leagueName;
	}

	/**
	 * 
	 * @return The league description
	 */
	public String getLeagueDesc() {
		return leagueDesc;
	}
	
	/**
	 * 
	 * @return The number of stages of the league
	 */
	public int getStages() {
		return stages;
	}

	/**
	 * 
	 * @return The league winner's team ID
	 */
	public int getWinnerTeam() {
		return winnerTeam;
	}
	
}

