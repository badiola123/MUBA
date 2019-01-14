package edu.eskola.muba.gameMechanics;

public class Round {
	private final TeamGame attackingTeam;
	private final TeamGame defendingTeam;
	private final Calculations calculations;
	static final int MAX_REBOUNDS = 3; 
	private String matchLogs;

	public Round (TeamGame attackingTeam, TeamGame defendingTeam, Calculations calculations, String matchLogs) {
		this.attackingTeam = attackingTeam;
		this.defendingTeam = defendingTeam;
		this.calculations = calculations;
		this.matchLogs = matchLogs;
	}
	
	public int playRound() {
		matchLogs += attackingTeam.getName() + " is going to attack with the ball. \n";
		int returnValue=-1;
		if(!goingToAttack()) returnValue = 0;
		else if(!goingToShoot()) returnValue = 0;
		else {
			int currentRebounds=0;
			while(currentRebounds<MAX_REBOUNDS && returnValue==-1) {
			currentRebounds++;
			int shootResult = shoot();
			if(shootResult==3) returnValue = 3;
			if(shootResult==2) returnValue = 2;
			if(shootResult==0)
				if(!rebound()) returnValue = 0;
		}
		}

		if(returnValue == -1) {
			returnValue = 0;
			matchLogs += attackingTeam.getName() + " looses the ball. \n";
		}
		return returnValue;
	}
	
	private boolean goingToAttack() {
		PlayerGame attackingPlayer = calculations.getGoingToAttackAttackingPlayer(attackingTeam);
		PlayerGame defendingPlayer = calculations.getRandomPlayer(defendingTeam);
		staminaLoss(attackingPlayer, defendingPlayer);

		boolean returnValue = calculations.goingToAttackResult(attackingPlayer,defendingPlayer);
		if(returnValue) matchLogs += attackingTeam.getName() + " is on the enemy's side. \n";
		else matchLogs += attackingTeam.getName() + " looses the ball. \n";
		return returnValue;
	}
	
	private boolean goingToShoot() {
		PlayerGame attackingPlayer = calculations.getRandomPlayer(attackingTeam);
		PlayerGame defendingPlayer = calculations.getRandomPlayer(defendingTeam);
		staminaLoss(attackingPlayer, defendingPlayer);

		boolean returnValue = calculations.goingToShootResult(attackingPlayer,defendingPlayer);
		if(returnValue) matchLogs += attackingTeam.getName() + " is ready to shoot. \n";
		else matchLogs += attackingTeam.getName() + " looses the ball. \n";
		return returnValue;
	}
	
	private int shoot() {
		int pointsForShoot = new OurRandom().randomBetween(2,3);
		PlayerGame attackingPlayer = calculations.getShootAttackingPlayer(attackingTeam,pointsForShoot);
		PlayerGame defendingPlayer = calculations.getRandomPlayer(defendingTeam);
		staminaLoss(attackingPlayer, defendingPlayer);

		int returnValue = calculations.shootResult(attackingPlayer,defendingPlayer,pointsForShoot);
		if(returnValue == 0) matchLogs += attackingTeam.getName() + " looses the ball. \n";
		if(returnValue == 2) matchLogs += attackingTeam.getName() + " scores for 2 points. \n";
		if(returnValue == 3) matchLogs += attackingTeam.getName() + " scores for 3 points. \n";
		return returnValue;
	}
	
	private boolean rebound() {
		PlayerGame attackingPlayer = calculations.getReboundPlayer(attackingTeam);
		PlayerGame defendingPlayer = calculations.getReboundPlayer(defendingTeam);
		staminaLoss(attackingPlayer, defendingPlayer);

		boolean returnValue = calculations.reboundResult(attackingPlayer,defendingPlayer);
		if(returnValue) matchLogs += attackingTeam.getName() + " gets the ball again after rebound. \n";
		else matchLogs += attackingTeam.getName() + " looses the ball. \n";
		return returnValue;
	}
	
	private void staminaLoss(PlayerGame playerA, PlayerGame playerB) {
		attackingTeam.staminaLoss();
		defendingTeam.staminaLoss();
		playerA.staminaLoss();
		playerB.staminaLoss();
	}
	
	public String getMatchLogs() {
		return matchLogs;
	}

}
