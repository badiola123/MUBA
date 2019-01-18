package edu.eskola.muba.leagueconnector.dao;

import java.util.List;

import edu.eskola.muba.leagueconnector.entity.LeagueConnector;

/**
 * DAO of LeagueConnector
 * 
 * @author MUBA team
 * @version Final version
 */
public interface LeagueConnectorDao {
	
	/**
	 * Tries to add a league connector
	 * 
	 * @param leagueConnector League connector to add
	 */
	void addLeagueConnector(LeagueConnector leagueConnector);
	
	/**
	 * Tries to get the league connectors in which a team is involved
	 * 
	 * @param teamId The team involved
	 * @return A list of league connectors
	 */
	List<LeagueConnector> getTeamLeagues(int teamId);
	
	/**
	 * Tries to get the league connectors in which a league is involved
	 * @param leagueId The league involved
	 * @return A list of league connectors
	 */
	List<LeagueConnector> getLeagueTeams(int leagueId);
	
	/**
	 * Tries to get the number of teams registered in a league
	 * 
	 * @param leagueId The league to check
	 * @return The number of teams registered in the league
	 */
	int getRegisteredTeams(int leagueId);
	
	/**
	 * Tries to remove the connection of a team to a league
	 * 
	 * @param leagueId The league ID
	 * @param userTeamId The team ID
	 */
	void leaveLeague(int leagueId, int userTeamId);
}
