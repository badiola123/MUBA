package edu.eskola.muba.player.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.eskola.muba.player.dao.PlayerDao;
import edu.eskola.muba.player.entity.Player;

@Service
public class PlayerServiceImpl implements PlayerService{

	@Autowired
	PlayerDao playerDao;
	
	@Transactional
	@Override
	public void addPlayer(Player player) {
		playerDao.addPlayer(player);
	}

	@Transactional
	@Override
	public Player getPlayer(int playerId) {
		return playerDao.getPlayer(playerId);
	}
	
	@Transactional
	@Override
	public List<Player> getTeamPlayers(int teamId) {
		return playerDao.getTeamPlayers(teamId);
	}

	@Transactional
	@Override
	public boolean checkPlayer(int playerId) {
		return playerDao.checkPlayer(playerId);
	}

	@Transactional
	@Override
	public List<Player> getInitialTeamPlayers(int teamId) {
		return playerDao.getInitialTeamPlayers(teamId);
	}

}
