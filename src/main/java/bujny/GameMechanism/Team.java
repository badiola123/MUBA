package bujny.GameMechanism;

import java.util.ArrayList;

public class Team {
	private ArrayList<Player> players;
	private String name;
	
	public Team(String name, Player player1, Player player2, Player player3, Player player4, Player player5) {
		players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		this.name = name; 
	}
	
	public void staminaLoss() {
		for(Player player : players) {
			player.staminaLoss();
		}
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
	
	public String getName() {
		return name;
	}
	
}
