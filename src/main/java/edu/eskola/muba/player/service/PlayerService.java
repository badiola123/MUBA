package edu.eskola.muba.player.service;

import java.util.List;

import edu.eskola.muba.player.entity.Player;
import edu.eskola.muba.user.dao.UserDao;

/**
 * Service of Player
 * 
 * @author MUBA team
 * @version Final version
 * @see PlayerDao
 */

public interface PlayerService {
	void addPlayer(Player player);
	Player getPlayer(int playerId);
	List<Player> getTeamPlayers(int teamId);
	List<Player> getInitialTeamPlayers(int teamId);
	boolean checkPlayer(int playerId);
	void updateInitialPosition(int position, int playerId);
	int getLastId();
	List<Player> getAllPlayers();
}
