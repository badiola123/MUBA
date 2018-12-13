package bujny.GameMechanism;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class CalculationsTest extends EasyMockSupport{
	Player player1;
	Player player2;
	Player player3;
	Player player4;
	Player player5;
	Team team;
	
    @Rule
    public EasyMockRule rule = new EasyMockRule(this);
    
	@Mock
	private OurRandom ourRandomMock;
	
	@TestSubject
	private Calculations calculations = new Calculations(ourRandomMock);
	
	@Before
	public void setUp()  {
		player1 = new Player(80,80,80,80,80,80);
		player2 = new Player(80,80,80,80,80,80);
		player3 = new Player(80,80,80,80,80,80);
		player4 = new Player(80,80,80,80,80,80);
		player5 = new Player(80,80,80,80,80,80);

		team = new Team("team",player1, player2, player3, player4, player5);
		calculations = new Calculations(ourRandomMock);
	}
	
	@Test
	public void getRandomPlayerTest() {
		EasyMock.expect(ourRandomMock.randomBetween(0, 4)).andReturn(0);
		replayAll();
    	
		assertEquals(calculations.getRandomPlayer(team),player1);
		verifyAll();
	}
	
	@Test
	public void getGoingToAttackAttackingPlayerTest() {
		ArrayList<Player> players = team.getPlayers();
		EasyMock.expect(ourRandomMock.ruletteWheelSelection(players)).andReturn(player1);
		replayAll();
    	
		assertEquals(calculations.getGoingToAttackAttackingPlayer(team),player1);
		verifyAll();
	}
	
	@Test
	public void getShootAttackingPlayer2Test() {
		ArrayList<Player> players = team.getPlayers();
		EasyMock.expect(ourRandomMock.ruletteWheelSelection(players)).andReturn(player1);
		replayAll();
    	
		assertEquals(calculations.getShootAttackingPlayer(team, 2),player1);
		verifyAll();
	}
	
	@Test
	public void getShootAttackingPlayer3Test() {
		ArrayList<Player> players = team.getPlayers();
		EasyMock.expect(ourRandomMock.ruletteWheelSelection(players)).andReturn(player1);
		replayAll();
    	
		assertEquals(calculations.getShootAttackingPlayer(team, 3),player1);
		verifyAll();
	}
	
	@Test
	public void getReboundPlayerTest() {
		ArrayList<Player> players = team.getPlayers();
		EasyMock.expect(ourRandomMock.ruletteWheelSelection(players)).andReturn(player1);
		replayAll();
    	
		assertEquals(calculations.getReboundPlayer(team),player1);
		verifyAll();
	}
	
	@Test
	public void goingToAttackResultPositiveTest() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		EasyMock.expect(ourRandomMock.ruletteWheelSelection(players)).andReturn(player1);
		replayAll();
    	
		assertEquals(calculations.goingToAttackResult(player1, player2),true);
		verifyAll();
	}
	
	@Test
	public void goingToAttackResultFailedTest() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		EasyMock.expect(ourRandomMock.ruletteWheelSelection(players)).andReturn(player2);
		replayAll();
    	
		assertEquals(calculations.goingToAttackResult(player1, player2),false);
		verifyAll();
	}
	
	@Test
	public void goingToShootResultFailedTest() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		EasyMock.expect(ourRandomMock.ruletteWheelSelection(players)).andReturn(player2);
		replayAll();
    	
		assertEquals(calculations.goingToShootResult(player1, player2),false);
		verifyAll();
	}
	
	@Test
	public void goingToShootResultPositiveTest() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		EasyMock.expect(ourRandomMock.ruletteWheelSelection(players)).andReturn(player1);
		replayAll();
    	
		assertEquals(calculations.goingToShootResult(player1, player2),true);
		verifyAll();
	}
	
	@Test
	public void reboundResultFailedTest() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		EasyMock.expect(ourRandomMock.ruletteWheelSelection(players)).andReturn(player2);
		replayAll();
    	
		assertEquals(calculations.reboundResult(player1, player2),false);
		verifyAll();
	}
	
	@Test
	public void reboundResultPositiveTest() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		EasyMock.expect(ourRandomMock.ruletteWheelSelection(players)).andReturn(player1);
		replayAll();
    	
		assertEquals(calculations.reboundResult(player1, player2),true);
		verifyAll();
	}
	
	@Test
	public void shootResult2PositiveTest() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		EasyMock.expect(ourRandomMock.ruletteWheelSelection(players)).andReturn(player1);
		replayAll();
    	
		assertEquals(calculations.shootResult(player1, player2,2),2);
		verifyAll();
	}
	
	@Test
	public void shootResult2NegativeTest() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		EasyMock.expect(ourRandomMock.ruletteWheelSelection(players)).andReturn(player2);
		replayAll();
    	
		assertEquals(calculations.shootResult(player1, player2,2),0);
		verifyAll();
	}
	
	@Test
	public void shootResult3PositiveTest() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		EasyMock.expect(ourRandomMock.ruletteWheelSelection(players)).andReturn(player1);
		replayAll();
    	
		assertEquals(calculations.shootResult(player1, player2,3),3);
		verifyAll();
	}
	
	@Test
	public void shootResult3NegativeTest() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		EasyMock.expect(ourRandomMock.ruletteWheelSelection(players)).andReturn(player2);
		replayAll();
    	
		assertEquals(calculations.shootResult(player1, player2,3),0);
		verifyAll();
	}
}
