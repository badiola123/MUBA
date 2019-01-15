package edu.eskola.muba.team.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

	public Team() {}
	
	public Team(String teamName, int budget, int userId) {
		this.teamName = teamName;
		this.budget = budget;
		this.userId = userId;
	}
	
	public Team(int teamId, String teamName, int budget, int userId) {
		super();
		this.teamId=teamId;
		this.teamName=teamName;
		this.budget=budget;
		this.userId=userId;
	}
	
	public String getTeamName() {
		return teamName;
	}

	
	public int getTeamId() {
		return teamId;
	}

	public void setTeamName(String username) {
		this.teamName = username;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
