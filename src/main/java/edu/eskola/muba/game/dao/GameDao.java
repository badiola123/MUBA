package edu.eskola.muba.game.dao;

import java.util.List;

import edu.eskola.muba.game.entity.Game;

/**
 * DAO of Game
 * 
 * @author MUBA team
 * @version Final version
 */
public interface GameDao {
	/**
	 * Tries to add a game
	 * 
	 * @param game Game to add
	 */
	void addGame(Game game);
	
	/**
	 * Tries to get a game
	 * 
	 * @param gameId The game to take
	 * @return The game
	 */
	Game getGame(int gameId);
	
	/**
	 * Tries to get league games by stage
	 * 
	 * @param leagueId The league of the games
	 * @param stage The stages of the games
	 * @return The game list
	 */
	List<Game> getLeagueGamesByStage(int leagueId, int stage);
	
	/**
	 * Tries to get the games from a league
	 * 
	 * @param leagueId The league of the games
	 * @return The list of games of the league
	 */
	List<Game> getLeagueGames(int leagueId);
	
	/**
	 * Tries to get the last played game of a team
	 * 
	 * @param teamId Team that played the game
	 * @return The last played game of a team
	 */
	Game getLastPlayedGame(int teamId);
	
	/**
	 * Tries to get the next game of a team
	 * 
	 * @param teamId The team that will play the game
	 * @return The next game of the team
	 */
	Game nextGame(int teamId);
	
	/**
	 * Tries to update a game
	 * 
	 * @param gameId The game to be updated
	 * @param key The field to be updated
	 * @param value The new value to be introduced
	 */
	void updateGame(int gameId, String key, String value);
	
	/**
	 * Tries to move a team one stage up
	 * 
	 * @param teamId The team to be moved
	 * @param leagueId The league in which it has to be moved
	 * @return {@code true} if team has been moved up
     * Else {@code false}.
	 */
	boolean moveTeamUp(int teamId, int leagueId);
	
	/**
	 * Tries to get the last game
	 * 
	 * @return The last game
	 */
	Game getLastGame();
	
	/**
	 * Tries to get all games
	 * 
	 * @return All games list
	 */
	List<Game> getAllGames();
}
