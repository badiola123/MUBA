package edu.eskola.muba.team.dao;

import java.util.List;

import edu.eskola.muba.player.entity.Player;
import edu.eskola.muba.team.entity.Team;

public interface TeamDao {
	void addTeam(Team team);
	Team getTeam(int teamId);
	Team getTeamByUserId(int userId);
	void updateBudget(int teamId, int budget);
}