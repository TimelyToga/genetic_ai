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

public class Scrum extends Renderable {

	public Vector2d velocity;

	public int size;
	public int energy;
	public double energyUseRate = 100; // energy used per 100 pixels moved
	public double maxMagnitude = 5;

	public Color color;

	public int numSensors;
	private double angleStep;
	
	public double sensorAngleSpread = 45.0;
	public double sensorRange = 200.0;
	public double sensorMappingSlope = -1.0 / 40.0;
	public double sensorB = (sensorRange * sensorMappingSlope * -1.0) + 1.0;
	
	public double[] sensorOutput;
	public int[] sensorAdjustedValues;
	public double[] sensorAngles;

	public Scrum(int x, int y, Vector2d velocity, int size, int energy,
			int energyUseRate, int numSensors) {
		this.xCood = x;
		this.yCood = y;
		this.velocity = velocity;
		// this.size = size;
		this.size = 10;
		this.energy = energy;
		this.energyUseRate = energyUseRate;
		this.numSensors = numSensors;
		this.angleStep = 360 / numSensors;
		this.sensorOutput = new double[numSensors];
		this.sensorAngles = new double[numSensors];
		this.sensorAdjustedValues = new int[numSensors];
		double curSensorAngle = 0;
		for (int a = 0; a < numSensors; a++) {
			sensorOutput[a] = sensorRange;
			sensorAngles[a] = curSensorAngle;
			curSensorAngle += angleStep;
		}
		this.color = new Color(G.rgen.nextInt(255), G.rgen.nextInt(255),
				G.rgen.nextInt(255));
		
		Logging.log("Number of sensors on current Scrum: " + numSensors, Logging.ALL); 
	}

	public static Scrum randScrum(int x, int y) {
		Vector2d velocity = new Vector2d(G.rgen.nextInt(5) + 1,
				G.rgen.nextInt(360));
		int size = G.rgen.nextInt(20) + 5;
		int energy = G.rgen.nextInt(1000) + 200;
		int energyUseRate = G.rgen.nextInt(100);
		int numSensors = G.rgen.nextInt(4) + 20;

		return new Scrum(x, y, velocity, size, energy, energyUseRate,
				numSensors);
	}

	public void think() {
		// sense
		sense();

		// AI
		Action plannedAction = G.aiEngine.computeAction(this);
		this.velocity = plannedAction.velocity;
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
					this.sensorOutput[a] = distanceToFood(curFood);
				} else {
					this.sensorOutput[a] = sensorRange;
				}
			}
		}
	}

	@Override
	public void update(int xDelta, int yDelta, int timeDelta) {
		this.xCood += velocity.getX();
		this.yCood += velocity.getY();

		// Do velocity correction
		if (xCood <= 0 && velocity.getX() < 0)
			velocity.setX(velocity.getX() * -1.0);
		if (yCood <= 0 && velocity.getY() < 0)
			velocity.setY(velocity.getY() * -1.0);
		if (xCood >= G.world.xSize && velocity.getX() > 0)
			velocity.setX(velocity.getX() * -1.0);
		if (yCood >= G.world.ySize && velocity.getY() > 0)
			velocity.setY(velocity.getY() * -1.0);

		think();
	}

	@Override
	public void render(Graphics g, int xOffset, int yOffset) {
		g.setColor(this.color);
		Circle c = new Circle(xCood, yCood, size);
		g.fill(c);

		// Initialize rotating vector to use to find positions of sensors
		Vector2d rotV = new Vector2d(size + 5, 0);
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

	public double distanceToFood(Food f) {
		return f.toVector2d().distTo(this.toVector2d());
	}

	public Vector2d toVector2d() {
		Vector2d output = new Vector2d(0, 0);
		output.setX(xCood);
		output.setY(yCood);
		return output;
	}
}
