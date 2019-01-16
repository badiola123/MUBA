package edu.eskola.muba.team.service;

import java.util.List;

import edu.eskola.muba.team.entity.Team;

public interface TeamService {
	void addTeam(Team team);
	Team getTeam(int teamId);
	Team getTeamByUserId(int userId);
	void updateBudget(int teamId, int budget);
	List<Team> getAllTeams();
}