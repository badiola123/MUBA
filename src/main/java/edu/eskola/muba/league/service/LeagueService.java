package edu.eskola.muba.league.service;

import java.util.List;

import edu.eskola.muba.league.entity.League;

public interface LeagueService {
	void addLeague(League league);
	League getLeague(int leagueId);
	List<League> getActiveLeagues();
}
