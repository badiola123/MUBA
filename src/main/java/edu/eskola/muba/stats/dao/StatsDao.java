package edu.eskola.muba.stats.dao;

import java.util.List;

import edu.eskola.muba.stats.entity.Stats;

public interface StatsDao {
	void addStats(Stats stats);
	Stats getStats(int playerId, int gameId);
	void updateStats(int playerId, int gameId, String key, String value);
	List<Stats> getAllStatsOfPlayer(int playerId);
	List<Stats> getAllStatsOfTeamOfGame(int teamId, int gameId);
}
