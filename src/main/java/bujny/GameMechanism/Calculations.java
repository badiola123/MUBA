package bujny.GameMechanism;

import java.util.ArrayList;

public class Calculations {
	private final OurRandom ourRandom;
	
	public Calculations(OurRandom ourRandom) {
		this.ourRandom = ourRandom; 
	}
	
	public Player getRandomPlayer(Team team) {
		int index = ourRandom.randomBetween(0,4);
		return team.getPlayers().get(index);
	}
	
	public Player getGoingToAttackAttackingPlayer(Team team) {
		ArrayList<Player> players = team.getPlayers();
		for(Player each : players) {
			each.setNeededStatValue(each.getBallControl());
		}
		return ourRandom.ruletteWheelSelection(players);
	}
	
	public Player getShootAttackingPlayer(Team team, int pointsForShoot) {
		ArrayList<Player> players = team.getPlayers();
		for(Player each : players) {
			if(pointsForShoot==2) each.setNeededStatValue(each.getShortShootSkill());
			else each.setNeededStatValue(each.getLongShootSkill());
		}
		return ourRandom.ruletteWheelSelection(players);
	}
	
	public Player getReboundPlayer(Team team) {
		ArrayList<Player> players = team.getPlayers();
		for(Player each : players) {
			each.setNeededStatValue(each.getHeight());
		}
		return ourRandom.ruletteWheelSelection(players);
	}
	
	public boolean goingToAttackResult(Player attackingPlayer,Player defendingPlayer) {
		ArrayList<Player> players = new ArrayList<Player>();
		attackingPlayer.setNeededStatValue(attackingPlayer.getBallControl());
		defendingPlayer.setNeededStatValue(defendingPlayer.getDefence());
		players.add(attackingPlayer);
		players.add(defendingPlayer);
		Player winner = ourRandom.ruletteWheelSelection(players);
		if(attackingPlayer.equals(winner)) return true;
		return false; 
	}
	
	public boolean goingToShootResult(Player attackingPlayer,Player defendingPlayer) {
		ArrayList<Player> players = new ArrayList<Player>();
		attackingPlayer.setNeededStatValue(attackingPlayer.getBallControl());
		defendingPlayer.setNeededStatValue(defendingPlayer.getDefence());
		players.add(attackingPlayer);
		players.add(defendingPlayer);
		Player winner = ourRandom.ruletteWheelSelection(players);
		if(attackingPlayer.equals(winner)) return true;
		return false; 
	}
	
	public boolean reboundResult(Player attackingPlayer,Player defendingPlayer) {
		ArrayList<Player> players = new ArrayList<Player>();
		attackingPlayer.setNeededStatValue(attackingPlayer.getHeight());
		defendingPlayer.setNeededStatValue(defendingPlayer.getHeight());
		players.add(attackingPlayer);
		players.add(defendingPlayer);
		Player winner = ourRandom.ruletteWheelSelection(players);
		if(attackingPlayer.equals(winner)) return true;
		return false; 
	}
	
	public int shootResult(Player attackingPlayer,Player defendingPlayer, int pointsForShoot) {
		ArrayList<Player> players = new ArrayList<Player>();
		defendingPlayer.setNeededStatValue(defendingPlayer.getDefence());
		players.add(attackingPlayer);
		players.add(defendingPlayer);
		Player winner;
		if(pointsForShoot == 2) {
			attackingPlayer.setNeededStatValue(attackingPlayer.getShortShootSkill());
			winner = ourRandom.ruletteWheelSelection(players);
			if(attackingPlayer.equals(winner)) return 2;
			return 0;
		}
		attackingPlayer.setNeededStatValue(attackingPlayer.getLongShootSkill());
		winner = ourRandom.ruletteWheelSelection(players);
		if(attackingPlayer.equals(winner)) return 3;
		return 0; 
	}

}
