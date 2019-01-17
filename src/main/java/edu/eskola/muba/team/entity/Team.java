package edu.eskola.muba.team.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity of Team
 * 
 * @author MUBA team
 * @version Final version
 */

@Entity
@Table(name = "TEAM")
public class Team {

	@Id
	@Column(name = "TEAMID", insertable = false, updatable = false)
	private int teamId;

	@Column(name = "TEAMNAME")
	private String teamName;

	@Column(name = "BUDGET")
	private int budget;

	@Column(name = "USERID")
	private int userId;

	/**
	 * Empty constructor of team
	 */
	public Team() {}
	
	/**
	 * Constructor with the parameters to set to the team
	 * 
	 * @param teamName Name to set to the team
	 * @param budget Budget of the team
	 * @param userId User id the team belongs to
	 */
	public Team(String teamName, int budget, int userId) {
		this.teamName = teamName;
		this.budget = budget;
		this.userId = userId;
	}
	
	/**
	 * Constructor with the parameters to set to the team
	 * 
	 * @param teamId Id of the team
	 * @param teamName Name of the team
	 * @param budget Budget of the team
	 * @param userId Id of the user the team belongs to
	 */
	public Team(int teamId, String teamName, int budget, int userId) {
		super();
		this.teamId=teamId;
		this.teamName=teamName;
		this.budget=budget;
		this.userId=userId;
	}
	
	/**
	 * 
	 * @return Returns the team name
	 */
	public String getTeamName() {
		return teamName;
	}
	
	/**
	 * 
	 * @return Returns the team id
	 */
	public int getTeamId() {
		return teamId;
	}

	/**
	 * 
	 * @return Return the team budget
	 */
	public int getBudget() {
		return budget;
	}

	/**
	 * 
	 * @return Returns the user id
	 */
	public int getUserId() {
		return userId;
	}
}
