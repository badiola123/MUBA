package edu.eskola.muba.leagueconnector.dao;

import java.util.List;

import edu.eskola.muba.league.entity.League;
import edu.eskola.muba.leagueconnector.entity.LeagueConnector;
import edu.eskola.muba.team.entity.Team;

public interface LeagueConnectorDao {
	void addLeagueConnector(LeagueConnector leagueConnector);
	List<LeagueConnector> getTeamLeagues(int teamId);
	List<LeagueConnector> getLeagueTeams(int leagueId);
}
