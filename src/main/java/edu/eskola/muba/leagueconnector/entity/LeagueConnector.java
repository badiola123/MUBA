package edu.eskola.muba.leagueconnector.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LEAGUECONNECTOR")
public class LeagueConnector implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "LEAGUEID", insertable = false, updatable = false)
	private int leagueId;
	
	@Id
	@Column(name = "TEAMID", insertable = false, updatable = false)
	private int teamId;
	
	public LeagueConnector() {}
	
	public LeagueConnector(int leagueId, int teamId) {
		super();
		this.leagueId=leagueId;
		this.teamId=teamId;
	}

	public int getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	
	
}
