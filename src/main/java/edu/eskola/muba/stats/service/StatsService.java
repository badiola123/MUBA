package edu.eskola.muba.stats.service;

import java.util.List;

import edu.eskola.muba.stats.dao.StatsDao;
import edu.eskola.muba.stats.entity.Stats;

/**
 * Service of Stats
 * 
 * @author MUBA team
 * @version Final version
 * @see StatsDao
 */
public interface StatsService {
	void addStats(Stats stats);
	Stats getStats(int playerId, int gameId);
	void updateStats(int playerId, int gameId, String key, String value);
	List<Stats> getAllStatsOfPlayer(int playerId);
	List<Stats> getAllStatsOfTeamOfGame(int teamId, int gameId);
}

