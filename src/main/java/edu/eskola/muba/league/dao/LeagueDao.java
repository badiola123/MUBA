package edu.eskola.muba.league.dao;

import java.util.List;

import edu.eskola.muba.league.entity.League;

public interface LeagueDao {
	void addLeague(League league);
	League getLeague(int leagueId);
	List<League> getActiveLeagues();
}
