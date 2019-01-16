package edu.eskola.muba.stats.dao;

import edu.eskola.muba.stats.entity.Stats;

public interface StatsDao {
	void addStats(Stats stats);
	Stats getStats(int playerId, int gameId);
	void updateStats(int playerId, int gameId, String key, String value);
}
