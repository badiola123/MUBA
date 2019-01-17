package edu.eskola.muba.gamemechanics;

import java.util.ArrayList;

public class Calculations {
	private final OurRandom ourRandom;
	
	public Calculations(OurRandom ourRandom) {
		this.ourRandom = ourRandom; 
	}
	
	public PlayerGame getRandomPlayer(TeamGame team) {
		int index = ourRandom.randomBetween(0,4);
		return team.getPlayers().get(index);
	}
	
	public PlayerGame getGoingToAttackAttackingPlayer(TeamGame team) {
		ArrayList<PlayerGame> players = team.getPlayers();
		for(PlayerGame each : players) {
			each.setNeededStatValue(each.getBallControl());
		}
		return ourRandom.ruletteWheelSelection(players);
	}
	
	public PlayerGame getShootAttackingPlayer(TeamGame team, int pointsForShoot) {
		ArrayList<PlayerGame> players = team.getPlayers();
		for(PlayerGame each : players) {
			if(pointsForShoot==2) each.setNeededStatValue(each.getShortShootSkill());
			else each.setNeededStatValue(each.getLongShootSkill());
		}
		return ourRandom.ruletteWheelSelection(players);
	}
	
	public PlayerGame getReboundPlayer(TeamGame team) {
		ArrayList<PlayerGame> players = team.getPlayers();
		for(PlayerGame each : players) {
			each.setNeededStatValue(each.getHeight());
		}
		return ourRandom.ruletteWheelSelection(players);
	}
	
	public boolean goingToAttackResult(PlayerGame attackingPlayer,PlayerGame defendingPlayer) {
		ArrayList<PlayerGame> players = new ArrayList<PlayerGame>();
		attackingPlayer.setNeededStatValue(attackingPlayer.getBallControl());
		defendingPlayer.setNeededStatValue(defendingPlayer.getDefence());
		players.add(attackingPlayer);
		players.add(defendingPlayer);
		PlayerGame winner = ourRandom.ruletteWheelSelection(players);
		if(attackingPlayer.equals(winner)) return true;
		return false; 
	}
	
	public boolean goingToShootResult(PlayerGame attackingPlayer,PlayerGame defendingPlayer) {
		ArrayList<PlayerGame> players = new ArrayList<PlayerGame>();
		attackingPlayer.setNeededStatValue(attackingPlayer.getBallControl());
		defendingPlayer.setNeededStatValue(defendingPlayer.getDefence());
		players.add(attackingPlayer);
		players.add(defendingPlayer);
		PlayerGame winner = ourRandom.ruletteWheelSelection(players);
		if(attackingPlayer.equals(winner)) return true;
		return false; 
	}
	
	public boolean reboundResult(PlayerGame attackingPlayer,PlayerGame defendingPlayer) {
		ArrayList<PlayerGame> players = new ArrayList<PlayerGame>();
		attackingPlayer.setNeededStatValue(attackingPlayer.getHeight());
		defendingPlayer.setNeededStatValue(defendingPlayer.getHeight());
		players.add(attackingPlayer);
		players.add(defendingPlayer);
		PlayerGame winner = ourRandom.ruletteWheelSelection(players);
		if(attackingPlayer.equals(winner)) return true;
		return false; 
	}
	
	public int shootResult(PlayerGame attackingPlayer,PlayerGame defendingPlayer, int pointsForShoot) {
		ArrayList<PlayerGame> players = new ArrayList<PlayerGame>();
		defendingPlayer.setNeededStatValue(defendingPlayer.getDefence());
		players.add(attackingPlayer);
		players.add(defendingPlayer);
		PlayerGame winner;
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
