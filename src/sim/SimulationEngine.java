package sim;

import org.newdawn.slick.Color;

public class SimulationEngine {
	
	public SimulationEngine() {		
		// Create Random First Generation
		
		
		// Loop
			// Simulate
		
			// Measure performance
		
			// Select highest performers
		
			// Perturb and Cross their genes to create new gen
			
	}
	
	public int size;
	public int energy;
	public double energyUseRate = 100; // energy used per 100 pixels moved
	public double maxMagnitude = 5;
	public double sensorOffset = 5;

	public int numSensors;
	
	public double sensorAngleSpread = 45.0;
	public double sensorRange = 300.0;
	public double sensorMappingSlope = -1.0 / 40.0;
	
	public double wallBounceEnergyCoef = 0.95; // Must be (0-1]
	public double accelK = 1.0 / 60.0; // Divide by 60 to spread over 60 frames (1s)
	public double rotK = 180.0 / 60.0; 
	public double smallestRot = 0.05;
	public double rotK2 = 0.4;
	
	private double turnSpeed = 2.0;

}
