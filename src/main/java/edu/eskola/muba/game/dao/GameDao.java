package edu.eskola.muba.game.dao;

import java.util.List;

import edu.eskola.muba.game.entity.Game;

public interface GameDao {
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
