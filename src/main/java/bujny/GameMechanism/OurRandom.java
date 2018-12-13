package bujny.GameMechanism;

import java.util.ArrayList;
import java.util.Random;

public class OurRandom {
	
	public int randomBetween(int from, int to) {
		Random rand = new Random();
		return rand.nextInt(to-from+1) + from;
	}
	
	public Player ruletteWheelSelection(ArrayList<Player> players) {
		int totalSum = 0;
		Random r = new Random();
		for(int i = 0; i< players.size(); i++) {
			totalSum+= players.get(i).getNeededStatValue();
		}
		
		int rand = r.nextInt(totalSum);
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
