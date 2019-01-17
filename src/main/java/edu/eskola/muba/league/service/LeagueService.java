package edu.eskola.muba.league.service;

import java.util.Date;
import java.util.List;

import edu.eskola.muba.league.entity.League;

/**
 * Service of League
 * 
 * @author MUBA team
 * @version Final version
 * @see LeagueDao
 */
public interface LeagueService {
	void addLeague(League league);
	League getLeague(int leagueId);
	List<League> getActiveLeagues(int teamId);
	List<League> getFinishedLeagues(int teamId);
	List<League> getNotStartedLeagues(int teamId);
	List<Integer> getAvailableLeagues(int teamId);
	int getLastLeagueId();
	boolean checkIfHost(int leagueId, int userTeamId);
	int getNeededTeams(int leagueId);
	void startLeague(int leagueId);
	void changeLeagueDates(int leagueId, Date startDate, Date endDate);
	void deleteLeague(int leagueId);
	void updateLeague(int leagueId, String key, String value);
	public List<League> getAllLeagues();
}
