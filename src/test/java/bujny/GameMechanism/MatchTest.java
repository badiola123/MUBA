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

public class MatchTest extends EasyMockSupport {
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
	}
	
	@Test
	public void matchTest() {
		boolean correctResult = true;
		
		for(int i=0; i<100; i++) {
		Match match = new Match(teamA,teamB);
		Team winnerTeam = match.startMatch();
		if(match.getTeamApoints()==match.getTeamBpoints()) correctResult = false;
		if(match.getTeamApoints() > match.getTeamBpoints() && winnerTeam == teamB) correctResult = false;
		if(match.getTeamBpoints() > match.getTeamApoints() && winnerTeam == teamA) correctResult = false;
		}
		
		assertEquals(correctResult, true);
	}

}
