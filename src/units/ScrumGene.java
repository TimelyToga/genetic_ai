package units;

import game.G;

import java.util.ArrayList;
import java.util.List;

import util.GeneticUtil;

public class ScrumGene {
	
	public double[] genes;
	public Scrum owner; 
	public int length; 
	
	public ScrumGene(double[] genes, Scrum owner) {
		this.genes = genes;
		this.owner = owner;
		this.length = genes.length;
	}
	
	/**
	 * This method performs a genetic crossover by altering the THIS ScrumGene
	 * and returning the altered version of the ScrumGene passed in as 'other'.
	 * 
	 * @param other
	 * @return Altered version of ScrumGene passed in
	 */
	public ScrumGene crossWith(ScrumGene other) {		
		List<Integer> possibilities = new ArrayList<Integer>(Scrum.NUM_PARAMETERS);
		List<Integer> crossList = new ArrayList<Integer>();
		
		// Generate variables to cross
		int numCrosses = GeneticUtil.perturbInt(G.AVG_NUM_CROSSES);
		for(int a = 0; a < numCrosses; a++) {
			crossList.add(G.rgen.nextInt(possibilities.size()));
		}
		
		// Cross genes
		for(Integer i: crossList) {
			double temp = this.genes[i];
			this.genes[i] = other.genes[i];
			other.genes[i] = temp;
		}
		
		return other;
	}

}
