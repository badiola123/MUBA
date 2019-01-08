package edu.eskola.muba.team.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Team")
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