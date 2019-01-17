package edu.eskola.muba.gameMechanisTests;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Test;

import edu.eskola.muba.characteristics.entity.Characteristics;
import edu.eskola.muba.gamemechanics.Match;
import edu.eskola.muba.gamemechanics.PlayerGame;
import edu.eskola.muba.gamemechanics.TeamGame;

public class MatchTest extends EasyMockSupport {
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
	}
	
	@Test
	public void matchTest() {
		boolean correctResult = true;
		
		for(int i=0; i<100; i++) {
		Match match = new Match(teamA,teamB);
		TeamGame winnerTeam = match.startMatch();
		if(match.getTeamApoints()==match.getTeamBpoints()) correctResult = false;
		if(match.getTeamApoints() > match.getTeamBpoints() && winnerTeam == teamB) correctResult = false;
		if(match.getTeamBpoints() > match.getTeamApoints() && winnerTeam == teamA) correctResult = false;
		}
		
		assertEquals(true, correctResult);
	}

}
