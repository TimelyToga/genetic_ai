package units;

import game.G;
import game.Renderable;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

import ai_methods.Action;
import util.Logging;
import util.Vector2d;
import util.VectorUtil;
import static util.Logging.*;
import static util.GeneticUtil.*;

public class Scrum extends Renderable {

	public Vector2d velocity;
	public double heading;
	public double maxMagnitude = 5;

	public int size;
	public int energy;
	public double energyUseRate; 			// = 100; // energy used per 100 pixels moved
	public double wallBounceEnergyCoef; 	// = 0.95; // Must be (0-1]
	public double accelK;               	// = 1.0 / 60.0; // Divide by 60 to spread over 60 frames (1s)
	public double rotK; 					// = 180.0 / 60.0; 
	public double smallestRot = 0.05;
	public double rotK2 = 0.4;
	
	public int numSensors;
	public double sensorAngleSpread; 		// = 45.0;
	public double sensorRange; 				// = 300.0;
	public double sensorMappingSlope; 		// = -1.0 / 40.0;
	public double sensorOffset; 			// = 5;

	
	public double[] sensorOutput;
	public int[] sensorAdjustedValues;
	public double[] sensorAngles;
	
	private Color color;
	private double angleStep;
	private double sensorB;
	private double turnSpeed = 2.0;
	
	
	public Scrum(int x, int y, Vector2d velocity, int size, int energy,
			int energyUseRate, int numSensors, double maxMagnitude,
			double sensorOffset, double sensorAngleSpread, double sensorRange,
			double sensorMappingSlope, double wallBounceEnergyCoef,
			double accelK, double rotK) {
		// Set traits
		this.xCood = x;
		this.yCood = y;
		this.velocity = velocity;
		this.heading = velocity.getAngle();
		this.size = 10; // = size
		this.energy = energy;
		this.energyUseRate = energyUseRate;
		this.numSensors = numSensors;
		this.sensorOutput = new double[numSensors];
		this.sensorAngles = new double[numSensors];
		this.sensorAdjustedValues = new int[numSensors];
		this.maxMagnitude = maxMagnitude;
		this.sensorOffset = sensorOffset;
		this.sensorAngleSpread = sensorAngleSpread;
		this.sensorRange = sensorRange;
		this.sensorMappingSlope = sensorMappingSlope;
		this.wallBounceEnergyCoef = wallBounceEnergyCoef;
		this.accelK = accelK;
		this.rotK = rotK;
		
		// Calculate Traits
		this.angleStep = 360 / numSensors;
		sensorB = (sensorRange * sensorMappingSlope * -1.0) + 1.0;
		this.color = new Color(G.rgen.nextInt(255), G.rgen.nextInt(255),
				G.rgen.nextInt(255));
		
		double curSensorAngle = 0;
		for (int a = 0; a < numSensors; a++) {
			sensorOutput[a] = sensorRange;
			sensorAngles[a] = curSensorAngle;
			curSensorAngle += angleStep;
		}
		
//		Logging.log("Number of sensors on current Scrum: " + numSensors, Logging.ALL); 
	}

	public static Scrum randScrum(int x, int y) {
		double mag = G.rgen.nextInt(5) + 1;
		double dir = G.rgen.nextInt(360);
		Vector2d velocity = new Vector2d(mag, dir);
		
		int size = perturbInt(G.D_SIZE);
		int energy = perturbInt(G.D_ENERGY);
		int energyUseRate = perturbInt(G.D_ENERGY_USE_RATE);
		int numSensors = perturbInt(G.D_NUM_SENSORS);
		double maxMagnitude = perturbDouble(G.D_MAX_MAGNITUDE);
		double sensorOffset = perturbDouble(G.D_SENSOR_OFFSET);;
		double sensorAngleSpread = perturbDouble(G.D_SENSOR_ANGLE_SPREAD);
		double sensorRange = perturbDouble(G.D_SENSOR_RANGE);
		double sensorMappingSlope = perturbDouble(G.D_SENSOR_MAPPING_SLOPE);
		double wallBounceEnergyCoef = perturbDouble(G.D_WALL_BOUNCE_ENERGY_COEF);
		double accelK = perturbDouble(G.D_ACCEL_K);
		double rotK = perturbDouble(G.D_ROT_K);

		return new Scrum(x, y, velocity, size, energy, energyUseRate,
				numSensors, maxMagnitude, sensorOffset, sensorAngleSpread, sensorRange, sensorMappingSlope, wallBounceEnergyCoef, accelK, rotK);
	}

	@Override
	public void update(int xDelta, int yDelta, int timeDelta) {
		// Update position
		this.xCood += velocity.getX();
		this.yCood += velocity.getY();

		// Direction correction from bounce
		if ((xCood <= 0 && velocity.getX() < 0) || (xCood >= G.world.xSize && velocity.getX() > 0)) {
			this.velocity.reflectY();
			// Minorly reduce speed from bounce
			this.velocity.setMagnitude(this.velocity.getMagnitude() * this.wallBounceEnergyCoef);
		} if ((yCood <= 0 && velocity.getY() < 0) || (yCood >= G.world.ySize && velocity.getY() > 0)) {
			this.velocity.reflectX();
			// Minorly reduce speed from bounce
			this.velocity.setMagnitude(this.velocity.getMagnitude() * this.wallBounceEnergyCoef);
		}
				
		// sense
		sense();

		// AI
		Action plannedAction = G.aiEngine.computeAction(this);
		System.out.println(plannedAction);
		this.velocity = plannedAction.velocity;
		// accelerateToVelocity(plannedAction.velocity, timeDelta);
	}

	public void sense() {
		Food[] foods = G.world.getFoods();

		for (int a = 0; a < numSensors; a++) {
			for (int b = 0; b < foods.length; b++) {
				if (foods[b] == null)
					continue;
				Food curFood = foods[b];
				if (VectorUtil.isFoodInSector(this, this.sensorAngles[a],
						sensorAngleSpread, curFood.toVector2d(),
						this.sensorRange)) {
					double offsetAngle = sensorAngles[a];
					this.sensorOutput[a] = distanceToFood(curFood, new Vector2d(sensorOffset, offsetAngle));
				} else {
					this.sensorOutput[a] = sensorRange;
				}
			}
		}
	}

	@Override
	public void render(Graphics g, int xOffset, int yOffset) {
		g.setColor(this.color);
		Circle c = new Circle(xCood, yCood, size);
		g.fill(c);

		// Initialize rotating vector to use to find positions of sensors
		Vector2d rotV = new Vector2d(size + sensorOffset, 0);
		for (int a = 0; a < numSensors; a++) {
			// Making sure sensor has output
			if(sensorOutput[a] < 0) continue;
			
			int newX = (int) (xCood + rotV.getX());
			int newY = (int) (yCood + rotV.getY());
			sensorAdjustedValues[a] = (int) ((sensorMappingSlope * (sensorOutput[a] + 1)) + sensorB);
			
			Circle c1 = new Circle(newX, newY, sensorAdjustedValues[a]);
			g.fill(c1);
			
			rotV.setAngle(rotV.getAngle() + angleStep);
		}
	}

	public double distanceToFood(Food f, Vector2d sensorOffset) {
		return f.toVector2d().add(sensorOffset).distTo(this.toVector2d());
	}
	
	public double distanceToFood(Food f) {
		return distanceToFood(f, new Vector2d(0,0));
	}

	public Vector2d toVector2d() {
		Vector2d output = new Vector2d(0, 0);
		output.setX(xCood);
		output.setY(yCood);
		return output;
	}
	
	public void consumeFood(Food f) {
		this.energy += f.consume();
		log(String.valueOf(this.energy));
		
		for(int a = 0; a < numSensors; a++) {
			sensorAdjustedValues[a] = 0;
		}
		System.exit(0);;
	}
	
	/**
	 * This method makes maximal changes to Scrum's velocity to make it more move like a given vector
	 * 
	 * @param otherVector Vector to be matched after several iterations 
	 * @param accelK Acceleration Constant
	 * @param rotationK Rotation Constant
	 */
	public void accelerateToVelocity(Vector2d otherVector, int timeDelta) {
		double difference = otherVector.getAngle() - velocity.getAngle();
		if(difference > smallestRot)
			this.velocity.setMagnitude(turnSpeed);
		
		double rotAmount = difference * rotK2;
		rotAmount = (rotAmount > smallestRot) ? rotAmount : 0.0;
		double newAngle = velocity.getAngle() + rotAmount;
	
		velocity.setAngle(newAngle);
	}
}
