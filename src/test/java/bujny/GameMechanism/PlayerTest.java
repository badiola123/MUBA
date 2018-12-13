package bujny.GameMechanism;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class PlayerTest {
	private Player player;
	
	@Before
	public void setUp() {
		player = new Player(100,100,90,80,70,60);
	}
	
	@Test
	public void staminaLossTest() {
		player.staminaLoss();
		
		assertEquals(player.getStamina(),99.4f,2);
	}
	
	@Test
	public void restTest() {
		player.staminaLoss();
		player.staminaLoss();
		player.staminaLoss();
		player.rest();
		
		assertEquals(player.getStamina(),100.0f,2);
	}
	
	@Test
	public void staminaMinTest() {
		for(int i=0;i<3000;i++) {
			player.staminaLoss();
		}

		assertEquals(player.getStamina(),10.0f,2);
	}
	
	@Test
	public void getBallControlTest() {
		assertEquals(player.getBallControl(),90);
	}
	
	@Test
	public void getDefenceTest() {
		for(int i=0;i<5;i++) {
			player.staminaLoss();
		}
		System.out.println(player.getStamina());
		assertEquals(player.getDefence(),79);
	}
	
	@Test
	public void getLongShootSkillTest() {
		assertEquals(player.getLongShootSkill(),70);
	}
	
	@Test
	public void getShortShootSkillTest() {
		assertEquals(player.getShortShootSkill(),60);
	}

}
