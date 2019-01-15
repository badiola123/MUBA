package edu.eskola.muba.game.dao;

import java.util.List;

import edu.eskola.muba.game.entity.Game;

public interface GameDao {
	void addGame(Game game);
	Game getGame(int gameId);
	List<Game> getLeagueGamesByStage(int leagueId, int stage);
	List<Game> getLeagueGames(int leagueId);
}
