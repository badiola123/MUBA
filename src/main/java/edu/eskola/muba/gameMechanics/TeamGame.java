package edu.eskola.muba.gameMechanics;

import java.util.ArrayList;
import java.util.List;

import edu.eskola.muba.characteristics.entity.Characteristics;
import edu.eskola.muba.player.entity.Player;
import edu.eskola.muba.team.entity.Team;

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