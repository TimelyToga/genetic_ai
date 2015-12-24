package units;

import game.G;
import game.Renderable;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

import util.Logging;
import util.Vector2d;
import util.VectorUtil;

import static util.Logging.*;

public class Scrum extends Renderable {

	public int xCood;
	public int yCood;

	public Vector2d velocity;

	public int size;
	public int energy;
	public int energyUseRate;

	public Color color;

	public int numSensors;
	public double sensorAngleSpread = 15;
	public double sensorRange = 400;
	public double sensorVisualizationMappingSlope = 1/10;
	public double[] sensorOutput;

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
		this.sensorOutput = new double[numSensors];
		for (int a = 0; a < numSensors; a++) {
			sensorOutput[a] = -1;
		}
		this.color = new Color(G.rgen.nextInt(255), G.rgen.nextInt(255),
				G.rgen.nextInt(255));
	}

	public static Scrum randScrum(int x, int y) {
		Vector2d velocity = new Vector2d(G.rgen.nextInt(5) + 1,
				G.rgen.nextInt(360));
		int size = G.rgen.nextInt(20) + 5;
		int energy = G.rgen.nextInt(1000) + 200;
		int energyUseRate = G.rgen.nextInt(100);
		int numSensors = G.rgen.nextInt(8) + 1;

		return new Scrum(x, y, velocity, size, energy, energyUseRate,
				numSensors);
	}

	public void think() {
		// sense
		sense();

		// weigh needs / situation

		// decide
	}

	public void sense() {
		double curAngle = 0;
		double angleStep = 360 / numSensors;
		Food[] foods = G.world.getFoods();

		for (int a = 0; a < numSensors; a++) {
			for (int b = 0; b < foods.length; b++) {
				if (foods[b] == null)
					continue;
				Food curFood = foods[b];
				if (VectorUtil.isFoodInSector(this, curAngle,
						sensorAngleSpread, curFood.toVector2d(),
						this.sensorRange)) {
					this.sensorOutput[a] = distanceToFood(curFood);
				} else {
					this.sensorOutput[a] = 0;
				}
			}
			curAngle += angleStep;
		}
	}

	@Override
	public void update(int xDelta, int yDelta, int timeDelta) {
		this.xCood += velocity.getX();
		this.yCood += velocity.getY();

		if (xCood <= 0)
			velocity.setX(velocity.getX() * -1.0);
		if (yCood <= 0)
			velocity.setY(velocity.getY() * -1.0);
		if (xCood >= G.world.xSize)
			velocity.setX(velocity.getX() * -1.0);
		if (yCood >= G.world.ySize)
			velocity.setY(velocity.getY() * -1.0);

		think();
	}

	@Override
	public void render(Graphics g, int xOffset, int yOffset) {
//		Logging.log(xCood + ", " + yCood);

		g.setColor(this.color);
		Circle c = new Circle(xCood, yCood, size);
		g.fill(c);

		// Render sensors
		Vector2d rotV = new Vector2d(size + 5, 0);
		if (G.oneTime) {
			log(rotV.getX() + ", " + rotV.getY());
			
			logWord("[ ");
			for(double d : this.sensorOutput){
				logWord(d + ", ");
			}
			logWord(" ]\n");
		}
		double angleStep = 360 / numSensors;
		
		for (int a = 0; a < numSensors; a++) {
			int newX = (int) (xCood + rotV.getX());
			int newY = (int) (yCood + rotV.getY());
			int size = (int) ((sensorVisualizationMappingSlope * sensorOutput[a]) + 2);
			Circle c1 = new Circle(newX, newY, size);
			g.fill(c1);
//			g.drawString(String.valueOf(sensorOutput[a]), newX, newY);
			rotV.setAngle(rotV.getAngle() + angleStep);
			if(G.oneTime){
				if(a == numSensors - 1){
					G.oneTime = false;
				}
				Logging.log("(" + newX + ", " + newY + ")", Logging.DEBUG); 
			}
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
