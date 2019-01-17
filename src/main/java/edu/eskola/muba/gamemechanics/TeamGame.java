package edu.eskola.muba.gamemechanics;

import java.util.ArrayList;
import java.util.List;

import edu.eskola.muba.characteristics.entity.Characteristics;

public class TeamGame {
	private ArrayList<PlayerGame> players;
	private String name;
	
	public TeamGame(String name, List<Characteristics> chars) {
		players = new ArrayList<PlayerGame >();
		players.add(new PlayerGame(chars.get(0)));
		players.add(new PlayerGame(chars.get(1)));
		players.add(new PlayerGame(chars.get(2)));
		players.add(new PlayerGame(chars.get(3)));
		players.add(new PlayerGame(chars.get(4)));
		this.name = name; 
	}
	
	public TeamGame(String name, PlayerGame player1, PlayerGame player2, PlayerGame player3, PlayerGame player4, PlayerGame player5) {
		players = new ArrayList<PlayerGame >();
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		this.name = name;
	}
	
	public PlayerGame getPlayerById(int playerId) {
		PlayerGame player = null;
		for(PlayerGame each : players) {
			if(each.getPlayerId() == playerId) player = each;
		}
		return player;
	}
	
	public void staminaLoss() {
		for(PlayerGame player : players) {
			player.staminaLoss();
		}
	}
	
	public ArrayList<PlayerGame> getPlayers(){
		return players;
	}
	
	public String getName() {
		return name;
	}
	
}
