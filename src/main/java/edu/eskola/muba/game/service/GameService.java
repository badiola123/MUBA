package edu.eskola.muba.game.service;

import java.util.List;

import edu.eskola.muba.game.dao.GameDao;
import edu.eskola.muba.game.entity.Game;

/**
 * Service of Game
 * 
 * @author MUBA team
 * @version Final version
 * @see GameDao
 */
public interface GameService {
	void addGame(Game game);
	Game getGame(int gameId);
	List<Game> getLeagueGamesByStage(int leagueId, int stage);
	List<Game> getLeagueGames(int leagueId);
	Game getLastPlayedGame(int teamId);
	Game nextGame(int teamId);
	void updateGame(int gameId, String key, String value);
	boolean moveTeamUp(int teamId, int leagueId);
    Game getLastGame();
	List<Game> getAllGames();
}

