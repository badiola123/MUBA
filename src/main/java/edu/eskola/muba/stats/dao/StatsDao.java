package edu.eskola.muba.stats.dao;

import edu.eskola.muba.stats.entity.StatsId;

import edu.eskola.muba.stats.entity.Stats;

public interface StatsDao {
	void addStats(Stats stats);
	Stats getStats(StatsId statsId);
	void updateStats(StatsId statsId, String key, String value);
}
