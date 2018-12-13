package bujny.GameMechanism;

public class Round {
	private final Team attackingTeam;
	private final Team defendingTeam;
	private final Calculations calculations;
	static final int MAX_REBOUNDS = 3; 
	
	public Round (Team attackingTeam, Team defendingTeam, Calculations calculations) {
		this.attackingTeam = attackingTeam;
		this.defendingTeam = defendingTeam;
		this.calculations = calculations;
	}
	
	public int playRound() {
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
		if(returnValue == -1) returnValue = 0;
		return returnValue;
	}
	
	private boolean goingToAttack() {
		Player attackingPlayer = calculations.getGoingToAttackAttackingPlayer(attackingTeam);
		Player defendingPlayer = calculations.getRandomPlayer(defendingTeam);
		staminaLoss(attackingPlayer, defendingPlayer);
		return calculations.goingToAttackResult(attackingPlayer,defendingPlayer);
	}
	
	private boolean goingToShoot() {
		Player attackingPlayer = calculations.getRandomPlayer(attackingTeam);
		Player defendingPlayer = calculations.getRandomPlayer(defendingTeam);
		staminaLoss(attackingPlayer, defendingPlayer);
		return calculations.goingToShootResult(attackingPlayer,defendingPlayer);
	}
	
	private int shoot() {
		int pointsForShoot = new OurRandom().randomBetween(2,3);
		Player attackingPlayer = calculations.getShootAttackingPlayer(attackingTeam,pointsForShoot);
		Player defendingPlayer = calculations.getRandomPlayer(defendingTeam);
		staminaLoss(attackingPlayer, defendingPlayer);
		return calculations.shootResult(attackingPlayer,defendingPlayer,pointsForShoot);
	}
	
	private boolean rebound() {
		Player attackingPlayer = calculations.getReboundPlayer(attackingTeam);
		Player defendingPlayer = calculations.getReboundPlayer(defendingTeam);
		staminaLoss(attackingPlayer, defendingPlayer);
		return calculations.reboundResult(attackingPlayer,defendingPlayer);
	}
	
	private void staminaLoss(Player playerA, Player playerB) {
		attackingTeam.staminaLoss();
		defendingTeam.staminaLoss();
		playerA.staminaLoss();
		playerB.staminaLoss();
	}

}
