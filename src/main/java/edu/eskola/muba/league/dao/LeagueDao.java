package edu.eskola.muba.league.dao;

import java.util.Date;
import java.util.List;

import edu.eskola.muba.league.entity.League;

/**
 * DAO of League
 * 
 * @author MUBA team
 * @version Final version
 */
public interface LeagueDao {
	
	/**
	 * Tries to add a league
	 * 
	 * @param league League to add
	 */
	void addLeague(League league);
	
	/**
	 * Tries to get a league
	 * 
	 * @param leagueId The league ID to take
	 * @return The league
	 */
	League getLeague(int leagueId);
	
	/**
	 * Tries to get the leagues that are currently running
	 * 
	 * @param teamId The team that requests the leagues involved
	 * @return A list of leagues
	 */
	List<League> getActiveLeagues(int teamId);
	
	/**
	 * Tries to get the leagues that have finished
	 * 
	 * @param teamId The team that requests the leagues involved
	 * @return A list of leagues
	 */
	List<League> getFinishedLeagues(int teamId);
	
	/**
	 * Tries to get the leagues that have not started for a team
	 * 
	 * @param teamId The team that requests the leagues involved
	 * @return A list of leagues
	 */
	List<League> getNotStartedLeagues(int teamId);
	
	/**
	 * Trie to get the leagues that are available to join for a team
	 * 
	 * @param teamId The team that requests the leagues involved
	 * @return A list of integers of the league IDs
	 */
	List<Integer> getAvailableLeagues(int teamId);
	
	/**
	 * Tries to get the last league ID from the database
	 * 
	 * @return The league ID
	 */
	int getLastLeagueId();
	
	/**
	 * Tries to check whether a team is the host of a league
	 * 
	 * @param leagueId The league ID
	 * @param userTeamId The team ID
	 * @return If it is the host or not
	 */
	boolean checkIfHost(int leagueId, int userTeamId);
	
	/**
	 * Tries to get the number of teams needed to start a league
	 * 
	 * @param leagueId The league ID
	 * @return The number of teams
	 */
	int getNeededTeams(int leagueId);
	
	/**
	 * Tries to update the field that determines whether a league has started in the database
	 * 
	 * @param leagueId The league ID
	 */
	void startLeague(int leagueId);
	
	/**
	 * Tries to update the dates of the league
	 * 
	 * @param leagueId The league ID
	 * @param startDate The new starting date
	 * @param endDate The new ending date
	 */
	void changeLeagueDates(int leagueId, Date startDate, Date endDate);
	
	/**
	 * Tries to delete a league
	 * 
	 * @param leagueId The league ID
	 */
	void deleteLeague(int leagueId);
	
	/**
	 * Tries to update a specific field of a league in the database
	 * 
	 * @param leagueId The league
	 * @param key The field in the league to change
	 * @param value	The value to set
	 */
	void updateLeague(int leagueId, String key, String value);
	
	/**
	 * Tries to get all the leagues 
	 * 
	 * @return A list of all the leagues
	 */
	List<League> getAllLeagues();
}
