package edu.eskola.muba.gameMechanisTests;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;

import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import edu.eskola.muba.characteristics.entity.Characteristics;
import edu.eskola.muba.gamemechanics.Calculations;
import edu.eskola.muba.gamemechanics.OurRandom;
import edu.eskola.muba.gamemechanics.PlayerGame;
import edu.eskola.muba.gamemechanics.TeamGame;

public class CalculationsTest extends EasyMockSupport{
	PlayerGame player1;
	PlayerGame player2;
	PlayerGame player3;
	PlayerGame player4;
	PlayerGame player5;
	TeamGame team;
	
    @Rule
    public EasyMockRule rule = new EasyMockRule(this);
    
	@Mock
	private OurRandom ourRandomMock;
	
	@TestSubject
	private Calculations calculations = new Calculations(ourRandomMock);
	
	@Before
	public void setUp()  {
		Characteristics stat = new Characteristics(80,80,80,80,80,80,21,new Date(0),-1);
		player1 = new PlayerGame(stat);
		player2 = new PlayerGame(stat);
		player3 = new PlayerGame(stat);
		player4 = new PlayerGame(stat);
		player5 = new PlayerGame(stat);

		team = new TeamGame("team",player1, player2, player3, player4, player5);
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
		ArrayList<PlayerGame> players = team.getPlayers();
		EasyMock.expect(ourRandomMock.ruletteWheelSelection(players)).andReturn(player1);
		replayAll();
    	
		assertEquals(calculations.getGoingToAttackAttackingPlayer(team),player1);
		verifyAll();
	}
	
	@Test
	public void getShootAttackingPlayer2Test() {
		ArrayList<PlayerGame> players = team.getPlayers();
		EasyMock.expect(ourRandomMock.ruletteWheelSelection(players)).andReturn(player1);
		replayAll();
    	
		assertEquals(calculations.getShootAttackingPlayer(team, 2),player1);
		verifyAll();
	}
	
	@Test
	public void getShootAttackingPlayer3Test() {
		ArrayList<PlayerGame> players = team.getPlayers();
		EasyMock.expect(ourRandomMock.ruletteWheelSelection(players)).andReturn(player1);
		replayAll();
    	
		assertEquals(calculations.getShootAttackingPlayer(team, 3),player1);
		verifyAll();
	}
	
	@Test
	public void getReboundPlayerTest() {
		ArrayList<PlayerGame> players = team.getPlayers();
		EasyMock.expect(ourRandomMock.ruletteWheelSelection(players)).andReturn(player1);
		replayAll();
    	
		assertEquals(calculations.getReboundPlayer(team),player1);
		verifyAll();
	}
	
	@Test
	public void goingToAttackResultPositiveTest() {
		ArrayList<PlayerGame> players = new ArrayList<PlayerGame>();
		players.add(player1);
		players.add(player2);
		EasyMock.expect(ourRandomMock.ruletteWheelSelection(players)).andReturn(player1);
		replayAll();
    	
		assertEquals(calculations.goingToAttackResult(player1, player2),true);
		verifyAll();
	}
	
	@Test
	public void goingToAttackResultFailedTest() {
		ArrayList<PlayerGame> players = new ArrayList<PlayerGame>();
		players.add(player1);
		players.add(player2);
		EasyMock.expect(ourRandomMock.ruletteWheelSelection(players)).andReturn(player2);
		replayAll();
    	
		assertEquals(calculations.goingToAttackResult(player1, player2),false);
		verifyAll();
	}
	
	@Test
	public void goingToShootResultFailedTest() {
		ArrayList<PlayerGame> players = new ArrayList<PlayerGame>();
		players.add(player1);
		players.add(player2);
		EasyMock.expect(ourRandomMock.ruletteWheelSelection(players)).andReturn(player2);
		replayAll();
    	
		assertEquals(calculations.goingToShootResult(player1, player2),false);
		verifyAll();
	}
	
	@Test
	public void goingToShootResultPositiveTest() {
		ArrayList<PlayerGame> players = new ArrayList<PlayerGame>();
		players.add(player1);
		players.add(player2);
		EasyMock.expect(ourRandomMock.ruletteWheelSelection(players)).andReturn(player1);
		replayAll();
    	
		assertEquals(calculations.goingToShootResult(player1, player2),true);
		verifyAll();
	}
	
	@Test
	public void reboundResultFailedTest() {
		ArrayList<PlayerGame> players = new ArrayList<PlayerGame>();
		players.add(player1);
		players.add(player2);
		EasyMock.expect(ourRandomMock.ruletteWheelSelection(players)).andReturn(player2);
		replayAll();
    	
		assertEquals(calculations.reboundResult(player1, player2),false);
		verifyAll();
	}
	
	@Test
	public void reboundResultPositiveTest() {
		ArrayList<PlayerGame> players = new ArrayList<PlayerGame>();
		players.add(player1);
		players.add(player2);
		EasyMock.expect(ourRandomMock.ruletteWheelSelection(players)).andReturn(player1);
		replayAll();
    	
		assertEquals(calculations.reboundResult(player1, player2),true);
		verifyAll();
	}
	
	@Test
	public void shootResult2PositiveTest() {
		ArrayList<PlayerGame> players = new ArrayList<PlayerGame>();
		players.add(player1);
		players.add(player2);
		EasyMock.expect(ourRandomMock.ruletteWheelSelection(players)).andReturn(player1);
		replayAll();
    	
		assertEquals(calculations.shootResult(player1, player2,2),2);
		verifyAll();
	}
	
	@Test
	public void shootResult2NegativeTest() {
		ArrayList<PlayerGame> players = new ArrayList<PlayerGame>();
		players.add(player1);
		players.add(player2);
		EasyMock.expect(ourRandomMock.ruletteWheelSelection(players)).andReturn(player2);
		replayAll();
    	
		assertEquals(calculations.shootResult(player1, player2,2),0);
		verifyAll();
	}
	
	@Test
	public void shootResult3PositiveTest() {
		ArrayList<PlayerGame> players = new ArrayList<PlayerGame>();
		players.add(player1);
		players.add(player2);
		EasyMock.expect(ourRandomMock.ruletteWheelSelection(players)).andReturn(player1);
		replayAll();
    	
		assertEquals(calculations.shootResult(player1, player2,3),3);
		verifyAll();
	}
	
	@Test
	public void shootResult3NegativeTest() {
		ArrayList<PlayerGame> players = new ArrayList<PlayerGame>();
		players.add(player1);
		players.add(player2);
		EasyMock.expect(ourRandomMock.ruletteWheelSelection(players)).andReturn(player2);
		replayAll();
    	
		assertEquals(calculations.shootResult(player1, player2,3),0);
		verifyAll();
	}
}
