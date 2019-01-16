package edu.eskola.muba.game.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.eskola.muba.game.dao.GameDao;
import edu.eskola.muba.game.entity.Game;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	GameDao gameDao;

	@Transactional
	@Override
	public void addGame(Game game) {
		gameDao.addGame(game);
	}

	@Transactional
	@Override
	public Game getGame(int gameId) {
		return gameDao.getGame(gameId);
	}

	@Transactional
	@Override
	public List<Game> getLeagueGamesByStage(int leagueId, int stage) {
		return gameDao.getLeagueGamesByStage(leagueId, stage);
	}

	@Transactional
	@Override
	public List<Game> getLeagueGames(int leagueId) {
		return gameDao.getLeagueGames(leagueId);
	}

	@Transactional
	@Override
	public Game getLastPlayedGame(int teamId) {
		return gameDao.getLastPlayedGame(teamId);
	}

	@Transactional
	@Override
	public Game nextGame(int teamId) {
		return gameDao.nextGame(teamId);
	}

	@Transactional
	@Override
	public void updateGame(int gameId, String key, String value) {
		gameDao.updateGame(gameId, key, value);	
	}

	@Transactional
	@Override
	public boolean moveTeamUp(int teamId, int leagueId) {
		return gameDao.moveTeamUp(teamId, leagueId);
	}
	
	@Transactional
	@Override
	public Game getLastGame() {
		return gameDao.getLastGame();
	}

}
