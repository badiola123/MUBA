package edu.eskola.muba.gameMechanics;

public class Match {
	private TeamGame teamA;
	private TeamGame teamB;
	private int teamApoints = 0;
	private int teamBpoints = 0;
	private int roundsAmount;
	private int currentRound = 0;

	public Match(TeamGame teamA, TeamGame teamB) {
		this.teamA = teamA;
		this.teamB = teamB;
		roundsAmount = new OurRandom().randomBetween(350,370);
	}

	public TeamGame startMatch() {
		Calculations calculations = new Calculations(new OurRandom());
		do {
			currentRound++;
			Round round;
			if(currentRound%2==1) {
				round = new Round(teamA, teamB, calculations);
				teamApoints+=round.playRound();
			}
			else {
				round = new Round(teamB, teamA, calculations);
				teamBpoints+=round.playRound();
			}
		} while(currentRound<roundsAmount || teamApoints==teamBpoints);
		if(teamApoints>teamBpoints) return teamA;
		return teamB; 
	}
	
	public int getTeamApoints() {
		return teamApoints;
	}
	
	public int getTeamBpoints() {
		return teamBpoints;
	}
}
