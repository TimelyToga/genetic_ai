package util;

import game.G;

public class GeneticUtil {

	public static int perturbInt(int input) {
		double maxPerturbationAmount = input * G.PERTURBATION_CONSTANT;
		return (int) (G.rgen.nextGaussian() * maxPerturbationAmount);
	}
	
	public static double perturbDouble(double input) {
		double maxPerturbationAmount = input * G.PERTURBATION_CONSTANT;
		return G.rgen.nextGaussian() * maxPerturbationAmount;
	}
	
	public int plusOrMinus() {
		int value;
		return value = G.rgen.nextBoolean() ? -1 : 1;
	}
}
