package edu.eskola.muba.gameMechanisTests;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import edu.eskola.muba.characteristics.entity.Characteristics;
import edu.eskola.muba.gameMechanics.PlayerGame;


public class PlayerTest {
	private PlayerGame player;
	
	@Before
	public void setUp() {
		Characteristics stat = new Characteristics(100,100,90,80,70,60,21,new Date(0),-1);
		player = new PlayerGame(stat);
	}
	
	@Test
	public void staminaLossTest() {
		player.staminaLoss();
		
		assertEquals(99.4f,player.getStamina(),2);
	}
	
	@Test
	public void restTest() {
		player.staminaLoss();
		player.staminaLoss();
		player.staminaLoss();
		player.rest();
		
		assertEquals(100.0f,player.getStamina(),2);
	}
	
	@Test
	public void staminaMinTest() {
		for(int i=0;i<3000;i++) {
			player.staminaLoss();
		}

		assertEquals(10.0f,player.getStamina(),2);
	}
	
	@Test
	public void getBallControlTest() {
		assertEquals(90,player.getBallControl());
	}
	
	@Test
	public void getDefenceTest() {
		for(int i=0;i<5;i++) {
			player.staminaLoss();
		}
		System.out.println(player.getStamina());
		assertEquals(79,player.getDefence());
	}
	
	@Test
	public void getLongShootSkillTest() {
		assertEquals(70,player.getLongShootSkill());
	}
	
	@Test
	public void getShortShootSkillTest() {
		assertEquals(60,player.getShortShootSkill());
	}

}
