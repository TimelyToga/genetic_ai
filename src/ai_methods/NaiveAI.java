package ai_methods;

import units.Scrum;
import util.Vector2d;

public class NaiveAI implements AI{

	@Override
	public Action computeAction(Scrum s) {
		// Find sensor with min distance.
		int minSensorIndex = -1;
		double minSensorValue = Integer.MAX_VALUE;
		for(int a = 0; a < s.numSensors; a++) {
			if(s.sensorAdjustedValues[a] > 0 && s.sensorOutput[a] < minSensorValue) {
				minSensorIndex = a;
				minSensorValue = s.sensorOutput[a];
			}
		}
		
		if(minSensorIndex >= 0) {
			// Find ideal sensor angle, and then create vector to travel in
			int lean = 0;
			for(int a = 0; a < s.numSensors; a++) {
				System.out.print(s.sensorOutput[a] + ", ");
			}
			System.out.println("\nAnd: " + minSensorValue);
			if(minSensorIndex != 0 && minSensorIndex != s.numSensors) {
				
			}
			Vector2d proposedVelocity = new Vector2d(s.maxMagnitude, s.sensorAngles[minSensorIndex]);
			return new Action(proposedVelocity);
		}
		
		return new Action(s.velocity);
		
	}

}
