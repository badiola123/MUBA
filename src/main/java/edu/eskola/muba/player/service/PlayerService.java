package edu.eskola.muba.player.service;

import java.util.List;

import edu.eskola.muba.player.entity.Player;

public interface PlayerService {
	void addPlayer(Player player);
	Player getPlayer(int playerId);
	List<Player> getTeamPlayers(int teamId);
	boolean checkPlayer(int playerId);
}