package main;

import bujny.GameMechanism.Match;
import bujny.GameMechanism.Player;
import bujny.GameMechanism.Team;

public class Main {

	public static void main(String args[]) {
		int teamAwin=0;
		int teamBwin=0;
		
		Player teamAPlayer1 = new Player(80,80,80,80,80,80);
		Player teamAPlayer2 = new Player(80,80,80,80,80,80);
		Player teamAPlayer3 = new Player(80,80,80,80,80,80);
		Player teamAPlayer4 = new Player(80,80,80,80,80,80);
		Player teamAPlayer5 = new Player(80,80,80,80,80,80);
		
		Player teamBPlayer1 = new Player(80,80,80,80,80,80);
		Player teamBPlayer2 = new Player(80,80,80,80,80,80);
		Player teamBPlayer3 = new Player(80,80,80,80,80,80);
		Player teamBPlayer4 = new Player(80,80,80,80,80,80);
		Player teamBPlayer5 = new Player(80,80,80,80,80,80);

		Team teamA = new Team("teamA",teamAPlayer1, teamAPlayer2, teamAPlayer3, teamAPlayer4, teamAPlayer5);
		Team teamB = new Team("teamB",teamBPlayer1, teamBPlayer2, teamBPlayer3, teamBPlayer4, teamBPlayer5);
		
		Match match;
		for(int i=0;i<1;i++) {
		match = new Match(teamB, teamA);
		if(match.startMatch()==teamA)  teamAwin++;
		else teamBwin++;
		}
		
		for(Player each: teamB.getPlayers()) {
			System.out.println("stamina: "+each.getStamina());
		}
		System.out.println("team A wins: "+teamAwin+" team B: "+teamBwin);

	}
	
}
