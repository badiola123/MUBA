package edu.eskola.muba.game.service;

import java.util.List;

import edu.eskola.muba.game.entity.Game;

public interface GameService {
	void addGame(Game game);
	Game getGame(int gameId);
	List<Game> getLeagueGamesByStage(int leagueId, int stage);
	List<Game> getLeagueGames(int leagueId);
}

