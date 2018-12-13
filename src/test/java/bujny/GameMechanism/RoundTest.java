package bujny.GameMechanism;

import static org.junit.Assert.*;
import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

public class RoundTest extends EasyMockSupport {
	Player teamAPlayer1;
	Player teamAPlayer2;
	Player teamAPlayer3;
	Player teamAPlayer4;
	Player teamAPlayer5;
	
	Player teamBPlayer1;
	Player teamBPlayer2;
	Player teamBPlayer3;
	Player teamBPlayer4;
	Player teamBPlayer5;
	
	Team teamA;
	Team teamB;
	
    @Rule
    public EasyMockRule rule = new EasyMockRule(this);
	
	@Mock
	private Calculations calculationsMock;
	
	@TestSubject
	private Round round = new Round(teamA, teamB, calculationsMock);

	@BeforeClass
	public static void setUpBeforeClass() {	
	}

	@Before
	public void setUp()  {
		teamAPlayer1 = new Player(80,80,80,80,80,80);
		teamAPlayer2 = new Player(80,80,80,80,80,80);
		teamAPlayer3 = new Player(80,80,80,80,80,80);
		teamAPlayer4 = new Player(80,80,80,80,80,80);
		teamAPlayer5 = new Player(80,80,80,80,80,80);
		teamBPlayer1 = new Player(80,80,80,80,80,80);
		teamBPlayer2 = new Player(80,80,80,80,80,80);
		teamBPlayer3 = new Player(80,80,80,80,80,80);
		teamBPlayer4 = new Player(80,80,80,80,80,80);
		teamBPlayer5 = new Player(80,80,80,80,80,80);
		teamA = new Team("teamA",teamAPlayer1, teamAPlayer2, teamAPlayer3, teamAPlayer4, teamAPlayer5);
		teamB = new Team("teamB",teamBPlayer1, teamBPlayer2, teamBPlayer3, teamBPlayer4, teamBPlayer5);
		round = new Round(teamA,teamB,calculationsMock);
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
		EasyMock.expect(calculationsMock.getShootAttackingPlayer(EasyMock.anyObject(Team.class), EasyMock.anyInt())).andReturn(teamAPlayer1);
		EasyMock.expect(calculationsMock.getRandomPlayer(teamB)).andReturn(teamBPlayer1);
		EasyMock.expect(calculationsMock.shootResult(EasyMock.anyObject(Player.class), EasyMock.anyObject(Player.class), EasyMock.anyInt())).andReturn(0);
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
		EasyMock.expect(calculationsMock.getShootAttackingPlayer(EasyMock.anyObject(Team.class), EasyMock.anyInt())).andReturn(teamAPlayer1);
		EasyMock.expect(calculationsMock.getRandomPlayer(teamB)).andReturn(teamBPlayer1);
		EasyMock.expect(calculationsMock.shootResult(EasyMock.anyObject(Player.class), EasyMock.anyObject(Player.class), EasyMock.anyInt())).andReturn(0);
		//rebound positive
		EasyMock.expect(calculationsMock.getReboundPlayer(teamA)).andReturn(teamAPlayer1);
		EasyMock.expect(calculationsMock.getReboundPlayer(teamB)).andReturn(teamBPlayer1);
		EasyMock.expect(calculationsMock.reboundResult(teamAPlayer1, teamBPlayer1)).andReturn(true);
		//shoot 2 positive
		EasyMock.expect(calculationsMock.getShootAttackingPlayer(EasyMock.anyObject(Team.class), EasyMock.anyInt())).andReturn(teamAPlayer1);
		EasyMock.expect(calculationsMock.getRandomPlayer(teamB)).andReturn(teamBPlayer1);
		EasyMock.expect(calculationsMock.shootResult(EasyMock.anyObject(Player.class), EasyMock.anyObject(Player.class), EasyMock.anyInt())).andReturn(2);
		
		
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
		EasyMock.expect(calculationsMock.getShootAttackingPlayer(EasyMock.anyObject(Team.class), EasyMock.anyInt())).andReturn(teamAPlayer1);
		EasyMock.expect(calculationsMock.getRandomPlayer(teamB)).andReturn(teamBPlayer1);
		EasyMock.expect(calculationsMock.shootResult(EasyMock.anyObject(Player.class), EasyMock.anyObject(Player.class), EasyMock.anyInt())).andReturn(3);
		
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
		EasyMock.expect(calculationsMock.getShootAttackingPlayer(EasyMock.anyObject(Team.class), EasyMock.anyInt())).andReturn(teamAPlayer1);
		EasyMock.expect(calculationsMock.getRandomPlayer(teamB)).andReturn(teamBPlayer1);
		EasyMock.expect(calculationsMock.shootResult(EasyMock.anyObject(Player.class), EasyMock.anyObject(Player.class), EasyMock.anyInt())).andReturn(0);
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
