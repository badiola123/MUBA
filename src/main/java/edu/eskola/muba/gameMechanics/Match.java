package edu.eskola.muba.gameMechanics;

public class Match {
	private TeamGame teamA;
	private TeamGame teamB;
	private int teamApoints = 0;
	private int teamBpoints = 0;
	private int roundsAmount;
	private int currentRound = 0;
	private String matchLogs = "";

	public Match(TeamGame teamA, TeamGame teamB) {
		this.teamA = teamA;
		this.teamB = teamB;
		roundsAmount = new OurRandom().randomBetween(350,370);
	}

	public TeamGame startMatch() {
		Calculations calculations = new Calculations(new OurRandom());
		matchLogs += "The match between "+teamA.getName()+" and "+teamB.getName()+" is going to start! \n";
		do {
			currentRound++;
			Round round;
			if(currentRound%2==1) {
				round = new Round(teamA, teamB, calculations, matchLogs);
				teamApoints+=round.playRound();
				matchLogs = round.getMatchLogs();
			}
			else {
				round = new Round(teamB, teamA, calculations, matchLogs);
				teamBpoints+=round.playRound();
				matchLogs = round.getMatchLogs();
			}
		} while(currentRound<roundsAmount || teamApoints==teamBpoints);
		TeamGame winnerTeam;
		if(teamApoints>teamBpoints) winnerTeam = teamA;
		else winnerTeam = teamB;
		matchLogs += winnerTeam.getName() + " wins the match!";
		return winnerTeam; 
	}
	
	public String getMatchLogs() {
		return matchLogs;
	}
	
	public int getTeamApoints() {
		return teamApoints;
	}
	
	public int getTeamBpoints() {
		return teamBpoints;
	}
}
