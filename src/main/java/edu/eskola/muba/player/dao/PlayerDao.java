package edu.eskola.muba.player.dao;

import java.util.List;

import edu.eskola.muba.player.entity.Player;

public interface PlayerDao {
	void addPlayer(Player player);
	void updateInitialPosition(int position, int playerId);
	Player getPlayer(int playerId);
	List<Player> getTeamPlayers(int teamId);
	boolean checkPlayer(int playerId);
	List<Player> getInitialTeamPlayers(int teamId);
}
