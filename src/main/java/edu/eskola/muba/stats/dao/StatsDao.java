package edu.eskola.muba.stats.dao;

import java.util.List;

import edu.eskola.muba.stats.entity.Stats;

/**
 * DAO of Stats
 * 
 * @author MUBA team
 * @version Final version
 */
public interface StatsDao {
	/**
	 * Tries to add new stats
	 * 
	 * @param stats The stats to be added
	 */
	void addStats(Stats stats);
	
	/**
	 * Tries to get the stats of a player in a determined game
	 * 
	 * @param playerId The player for which the stats have to be taken
	 * @param gameId The game from which the stats have to be taken
	 * @return The statistics of the player in a determined game
	 */
	Stats getStats(int playerId, int gameId);
	
	/**
	 * Tries to update stats of a player
	 * 
	 * @param playerId Player from which the stats have to be updated
	 * @param gameId The game in which the stats have to be updated
	 * @param key The name of the stat to be updated
	 * @param value The new value to be introduced
	 */
	void updateStats(int playerId, int gameId, String key, String value);
	
	/**
	 * Tries to get all stats of a player
	 * 
	 * @param playerId Player from which the stats have to be taken
	 * @return All the stats of a player
	 */
	List<Stats> getAllStatsOfPlayer(int playerId);
	
	/**
	 * Tries to get all stats of a team in a game
	 * 
	 * @param teamId Team for which the stats have to be taken
	 * @param gameId Game from which the stats have to be taken
	 * @return The stats of a team in a game
	 */
	List<Stats> getAllStatsOfTeamOfGame(int teamId, int gameId);
}
