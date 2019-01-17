package edu.eskola.muba.gamemechanics;

import java.util.List;
import java.util.Random;

public class OurRandom {
	private Random random;
	
	public OurRandom() {
		random = new Random();
	}
	
	public int randomBetween(int from, int to) {
	
		return random.nextInt(to-from+1) + from;
	}
	
	public PlayerGame ruletteWheelSelection(List<PlayerGame> players) {
		int totalSum = 0;

		for(int i = 0; i< players.size(); i++) {
			totalSum+= players.get(i).getNeededStatValue();
		}
		
	
		int rand = random.nextInt(totalSum);
		int partialSum = 0;
		for(int i = 0; i < players.size(); i++) {
			partialSum += players.get(i).getNeededStatValue();
			if(partialSum >= rand) {
				return players.get(i);
			}
		}
		return null;
	}
	
}
