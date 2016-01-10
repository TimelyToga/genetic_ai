package sim;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import units.Scrum;
import game.G;

public class Simulation {
	
	public int generation = 0; 
	
	public Simulation() {
		// Create Random First Generation
		for(int a = 0; a < G.GENERATION_SIZE; a++) {
			G.world.addRandScrum();
		}
	}
	
	public void nextIteration() {
		// Select highest performing Scrums
		Scrum[] scrumArray = (Scrum[]) G.world.scrums.toArray();
		Arrays.sort(scrumArray);
		Scrum[] aristocrats = new Scrum[G.ARISTOCRAT_POP];
	
		// Perturb and Cross their genes to create new gen
		for(int a = 0; a < aristocrats.length/2; a++) {
			aristocrats[a+1] = aristocrats[a].crossGenes(aristocrats[a+1]);
		}
	}

}
