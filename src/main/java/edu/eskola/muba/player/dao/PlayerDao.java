package edu.eskola.muba.player.dao;

import java.util.List;

import edu.eskola.muba.player.entity.Player;

/**
 * DAO of Player
 *
 * @author MUBA team
 * @version Final version
 */
public interface PlayerDao {
	/**
	 * Adds a new player to the database
	 * 
	 * @param player Player to be added
	 */
	void addPlayer(Player player);
	
	/**
	 * Updates the position of the specified player
	 * 
	 * @param position New position to be set
	 * @param playerId Player id whom position is changed
	 */
	void updateInitialPosition(int position, int playerId);
	
	/**
	 * Returns a player identified by an id
	 * 
	 * @param playerId Id to search for in the database
	 * @return Player fetched from the database
	 */
	Player getPlayer(int playerId);
	
	/**
	 * Fetches the list of all players who belong to a team id
	 * 
	 * @param teamId Team id to search for players
	 * @return List of players from the specified team id
	 */
	List<Player> getTeamPlayers(int teamId);
	
	/**
	 * Checks if a player exists in the database
	 * 
	 * @param playerId Id to look for
	 * @return True if it exists and false if it does not
	 */
	boolean checkPlayer(int playerId);
	
	/**
	 * Gets the initial five players from a team
	 * 
	 * @param teamId Id to look for
	 * @return List of the initial five players
	 */
	List<Player> getInitialTeamPlayers(int teamId);
	
	/**
	 * Gets the highest number id of the players
	 * 
	 * @return Highest number id
	 */
	int getLastId();
	
	/**
	 * Gets all players and return them on a list
	 * 
	 * @return List containing all the players
	 */
	List<Player> getAllPlayers();
}
