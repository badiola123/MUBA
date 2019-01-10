package edu.eskola.muba.game.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.eskola.muba.game.dao.GameDao;
import edu.eskola.muba.game.entity.Game;
import edu.eskola.muba.leagueconnector.dao.LeagueConnectorDao;
import edu.eskola.muba.leagueconnector.entity.LeagueConnector;
import edu.eskola.muba.leagueconnector.service.LeagueConnectorService;

@Service
public class GameServiceImpl implements GameService{

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

}

