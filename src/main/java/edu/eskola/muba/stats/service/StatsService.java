package edu.eskola.muba.stats.service;

import edu.eskola.muba.stats.entity.Stats;
import edu.eskola.muba.stats.entity.StatsId;

public interface StatsService {
	void addStats(Stats stats);
	Stats getStats(StatsId statsId);
	void updateStats(StatsId statsId, String key, String value);
}

