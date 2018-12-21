package edu.eskola.muba.team.service;

import edu.eskola.muba.team.entity.Team;

public interface TeamService {
	void addTeam(Team team);
	Team getTeam(int teamId);
	Team getTeamByUserId(int userId);
}
