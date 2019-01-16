package edu.eskola.muba.league.dao;

import java.util.Date;
import java.util.List;

import edu.eskola.muba.league.entity.League;

public interface LeagueDao {
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
	List<League> getAllLeagues();
}
