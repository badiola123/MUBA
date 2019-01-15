package edu.eskola.muba.leagueconnector.service;

import java.util.List;

import edu.eskola.muba.leagueconnector.entity.LeagueConnector;

public interface LeagueConnectorService {
	void addLeagueConnector(LeagueConnector leagueConnector);
	List<LeagueConnector> getTeamLeagues(int teamId);
	List<LeagueConnector> getLeagueTeams(int leagueId);
    int getRegisteredTeams(int leagueId);
	void leaveLeague(int leagueId, int userTeamId);
}
