package edu.eskola.muba.gameMechanisTests;

import static org.junit.Assert.*;

import java.sql.Date;

import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import edu.eskola.muba.characteristics.entity.Characteristics;
import edu.eskola.muba.gameMechanics.Calculations;
import edu.eskola.muba.gameMechanics.PlayerGame;
import edu.eskola.muba.gameMechanics.Round;
import edu.eskola.muba.gameMechanics.TeamGame;

public class RoundTest extends EasyMockSupport {
	PlayerGame teamAPlayer1;
	PlayerGame teamAPlayer2;
	PlayerGame teamAPlayer3;
	PlayerGame teamAPlayer4;
	PlayerGame teamAPlayer5;
	
	PlayerGame teamBPlayer1;
	PlayerGame teamBPlayer2;
	PlayerGame teamBPlayer3;
	PlayerGame teamBPlayer4;
	PlayerGame teamBPlayer5;
	
	TeamGame teamA;
	TeamGame teamB;
	
    @Rule
    public EasyMockRule rule = new EasyMockRule(this);
	
	@Mock
	private Calculations calculationsMock;
	
	@TestSubject
	private Round round = new Round(teamA, teamB, calculationsMock, "");

	@BeforeClass
	public static void setUpBeforeClass() {	
	}

	@Before
	public void setUp()  {
		Characteristics stat = new Characteristics(80,80,80,80,80,80,21,new Date(0),-1);
		teamAPlayer1 = new PlayerGame(stat);
		teamAPlayer2 = new PlayerGame(stat);
		teamAPlayer3 = new PlayerGame(stat);
		teamAPlayer4 = new PlayerGame(stat);
		teamAPlayer5 = new PlayerGame(stat);
		teamBPlayer1 = new PlayerGame(stat);
		teamBPlayer2 = new PlayerGame(stat);
		teamBPlayer3 = new PlayerGame(stat);
		teamBPlayer4 = new PlayerGame(stat);
		teamBPlayer5 = new PlayerGame(stat);
		teamA = new TeamGame("teamA",teamAPlayer1, teamAPlayer2, teamAPlayer3, teamAPlayer4, teamAPlayer5);
		teamB = new TeamGame("teamB",teamBPlayer1, teamBPlayer2, teamBPlayer3, teamBPlayer4, teamBPlayer5);
		round = new Round(teamA,teamB,calculationsMock,"");
	}

	@Test
	public void roundFailedGoingToAttack() {
		//going to attack
		EasyMock.expect(calculationsMock.getGoingToAttackAttackingPlayer(teamA)).andReturn(teamAPlayer1);
		EasyMock.expect(calculationsMock.getRandomPlayer(teamB)).andReturn(teamBPlayer1);
		EasyMock.expect(calculationsMock.goingToAttackResult(teamAPlayer1,teamBPlayer1)).andReturn(false);
    	
		replayAll();
		assertEquals(round.playRound(),0);
		verifyAll();
	}
	
	@Test
	public void roundFailedGoingToShoot() {
		//going to attack
		EasyMock.expect(calculationsMock.getGoingToAttackAttackingPlayer(teamA)).andReturn(teamAPlayer1);
		EasyMock.expect(calculationsMock.getRandomPlayer(teamB)).andReturn(teamBPlayer1);
		EasyMock.expect(calculationsMock.goingToAttackResult(teamAPlayer1,teamBPlayer1)).andReturn(true);
		//going to shoot
		EasyMock.expect(calculationsMock.getRandomPlayer(teamA)).andReturn(teamAPlayer1);
		EasyMock.expect(calculationsMock.getRandomPlayer(teamB)).andReturn(teamBPlayer1);
		EasyMock.expect(calculationsMock.goingToShootResult(teamAPlayer1, teamBPlayer1)).andReturn(false);
    	
		replayAll();
		assertEquals(round.playRound(),0);
		verifyAll();
	}
	
	@Test
	public void roundFailedShootNoRebound() {
		//going to attack
		EasyMock.expect(calculationsMock.getGoingToAttackAttackingPlayer(teamA)).andReturn(teamAPlayer1);
		EasyMock.expect(calculationsMock.getRandomPlayer(teamB)).andReturn(teamBPlayer1);
		EasyMock.expect(calculationsMock.goingToAttackResult(teamAPlayer1,teamBPlayer1)).andReturn(true);
		//going to shoot
		EasyMock.expect(calculationsMock.getRandomPlayer(teamA)).andReturn(teamAPlayer1);
		EasyMock.expect(calculationsMock.getRandomPlayer(teamB)).andReturn(teamBPlayer1);
		EasyMock.expect(calculationsMock.goingToShootResult(teamAPlayer1, teamBPlayer1)).andReturn(true);
		//shoot failed
		EasyMock.expect(calculationsMock.getShootAttackingPlayer(EasyMock.anyObject(TeamGame.class), EasyMock.anyInt())).andReturn(teamAPlayer1);
		EasyMock.expect(calculationsMock.getRandomPlayer(teamB)).andReturn(teamBPlayer1);
		EasyMock.expect(calculationsMock.shootResult(EasyMock.anyObject(PlayerGame.class), EasyMock.anyObject(PlayerGame.class), EasyMock.anyInt())).andReturn(0);
		//rebound failed
		EasyMock.expect(calculationsMock.getReboundPlayer(teamA)).andReturn(teamAPlayer1);
		EasyMock.expect(calculationsMock.getReboundPlayer(teamB)).andReturn(teamBPlayer1);
		EasyMock.expect(calculationsMock.reboundResult(teamAPlayer1, teamBPlayer1)).andReturn(false);
		
    	replayAll();
		assertEquals(round.playRound(),0);
		verifyAll();
	}
	
	@Test
	public void roundShoot2Rebound() {
		//going to attack
		EasyMock.expect(calculationsMock.getGoingToAttackAttackingPlayer(teamA)).andReturn(teamAPlayer1);
		EasyMock.expect(calculationsMock.getRandomPlayer(teamB)).andReturn(teamBPlayer1);
		EasyMock.expect(calculationsMock.goingToAttackResult(teamAPlayer1,teamBPlayer1)).andReturn(true);
		//going to shoot
		EasyMock.expect(calculationsMock.getRandomPlayer(teamA)).andReturn(teamAPlayer1);
		EasyMock.expect(calculationsMock.getRandomPlayer(teamB)).andReturn(teamBPlayer1);
		EasyMock.expect(calculationsMock.goingToShootResult(teamAPlayer1, teamBPlayer1)).andReturn(true);
		//shoot failed
		EasyMock.expect(calculationsMock.getShootAttackingPlayer(EasyMock.anyObject(TeamGame.class), EasyMock.anyInt())).andReturn(teamAPlayer1);
		EasyMock.expect(calculationsMock.getRandomPlayer(teamB)).andReturn(teamBPlayer1);
		EasyMock.expect(calculationsMock.shootResult(EasyMock.anyObject(PlayerGame.class), EasyMock.anyObject(PlayerGame.class), EasyMock.anyInt())).andReturn(0);
		//rebound positive
		EasyMock.expect(calculationsMock.getReboundPlayer(teamA)).andReturn(teamAPlayer1);
		EasyMock.expect(calculationsMock.getReboundPlayer(teamB)).andReturn(teamBPlayer1);
		EasyMock.expect(calculationsMock.reboundResult(teamAPlayer1, teamBPlayer1)).andReturn(true);
		//shoot 2 positive
		EasyMock.expect(calculationsMock.getShootAttackingPlayer(EasyMock.anyObject(TeamGame.class), EasyMock.anyInt())).andReturn(teamAPlayer1);
		EasyMock.expect(calculationsMock.getRandomPlayer(teamB)).andReturn(teamBPlayer1);
		EasyMock.expect(calculationsMock.shootResult(EasyMock.anyObject(PlayerGame.class), EasyMock.anyObject(PlayerGame.class), EasyMock.anyInt())).andReturn(2);
		
		
    	replayAll();
		assertEquals(round.playRound(),2);
		verifyAll();
	}
	
	@Test
	public void roundShoot3() {
		//going to attack
		EasyMock.expect(calculationsMock.getGoingToAttackAttackingPlayer(teamA)).andReturn(teamAPlayer1);
		EasyMock.expect(calculationsMock.getRandomPlayer(teamB)).andReturn(teamBPlayer1);
		EasyMock.expect(calculationsMock.goingToAttackResult(teamAPlayer1,teamBPlayer1)).andReturn(true);
		//going to shoot
		EasyMock.expect(calculationsMock.getRandomPlayer(teamA)).andReturn(teamAPlayer1);
		EasyMock.expect(calculationsMock.getRandomPlayer(teamB)).andReturn(teamBPlayer1);
		EasyMock.expect(calculationsMock.goingToShootResult(teamAPlayer1, teamBPlayer1)).andReturn(true);
		//shoot failed
		EasyMock.expect(calculationsMock.getShootAttackingPlayer(EasyMock.anyObject(TeamGame.class), EasyMock.anyInt())).andReturn(teamAPlayer1);
		EasyMock.expect(calculationsMock.getRandomPlayer(teamB)).andReturn(teamBPlayer1);
		EasyMock.expect(calculationsMock.shootResult(EasyMock.anyObject(PlayerGame.class), EasyMock.anyObject(PlayerGame.class), EasyMock.anyInt())).andReturn(3);
		
    	replayAll();
		assertEquals(round.playRound(),3);
		verifyAll();
	}
	
	@Test
	public void failedShootTooManyRebounds() {
		//going to attack
		EasyMock.expect(calculationsMock.getGoingToAttackAttackingPlayer(teamA)).andReturn(teamAPlayer1);
		EasyMock.expect(calculationsMock.getRandomPlayer(teamB)).andReturn(teamBPlayer1);
		EasyMock.expect(calculationsMock.goingToAttackResult(teamAPlayer1,teamBPlayer1)).andReturn(true);
		//going to shoot
		EasyMock.expect(calculationsMock.getRandomPlayer(teamA)).andReturn(teamAPlayer1);
		EasyMock.expect(calculationsMock.getRandomPlayer(teamB)).andReturn(teamBPlayer1);
		EasyMock.expect(calculationsMock.goingToShootResult(teamAPlayer1, teamBPlayer1)).andReturn(true);
		
		for(int i=0;i < Round.MAX_REBOUNDS; i++) {
		//shoot failed
		EasyMock.expect(calculationsMock.getShootAttackingPlayer(EasyMock.anyObject(TeamGame.class), EasyMock.anyInt())).andReturn(teamAPlayer1);
		EasyMock.expect(calculationsMock.getRandomPlayer(teamB)).andReturn(teamBPlayer1);
		EasyMock.expect(calculationsMock.shootResult(EasyMock.anyObject(PlayerGame.class), EasyMock.anyObject(PlayerGame.class), EasyMock.anyInt())).andReturn(0);
		//rebound positive
		EasyMock.expect(calculationsMock.getReboundPlayer(teamA)).andReturn(teamAPlayer1);
		EasyMock.expect(calculationsMock.getReboundPlayer(teamB)).andReturn(teamBPlayer1);
		EasyMock.expect(calculationsMock.reboundResult(teamAPlayer1, teamBPlayer1)).andReturn(true);
		}
	
		
    	replayAll();
		assertEquals(round.playRound(),0);
		verifyAll();
	}

}
