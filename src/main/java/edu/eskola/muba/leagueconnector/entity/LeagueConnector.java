package edu.eskola.muba.leagueconnector.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity of LeagueConnector
 * 
 * @author MUBA team
 * @version Final version
 */

@Entity
@Table(name = "LEAGUECONNECTOR")
public class LeagueConnector implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "LEAGUEID", insertable = false, updatable = false)
	private int leagueId;
	
	@Id
	@Column(name = "TEAMID", insertable = false, updatable = false)
	private int teamId;
	
	/**
	 * Empty constructor for LeagueConnector
	 */
	public LeagueConnector() {}
	
	/**
	 * 
	 * @param leagueId The league ID
	 * @param teamId The team ID
	 */
	public LeagueConnector(int leagueId, int teamId) {
		super();
		this.leagueId=leagueId;
		this.teamId=teamId;
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
	 * @return The team ID
	 */
	public int getTeamId() {
		return teamId;
	}
	
}
