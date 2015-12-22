package genetic_ai;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.geom.Circle;

public class Scrum implements Renderable{

	private int xCood;
	private int yCood;
	
	private Vector2d velocity;
	
	private int size;
	private int energy;
	private int energyUseRate;
	
	private int aggressivenessFactor;
	private Color color;
	
	public Scrum(int x, int y, Vector2d velocity, int size, int energy, int energyUseRate, int aggressivenessFactor){
		this.xCood = x;
		this.yCood = y;
		this.velocity = velocity; 
		this.size = size;
		this.energy = energy;
		this.energyUseRate = energyUseRate;
		this.aggressivenessFactor = aggressivenessFactor;
		
		System.out.println(velocity.getX() + ", " + velocity.getY());
		
		this.color = new Color(G.rgen.nextInt(255), G.rgen.nextInt(255), G.rgen.nextInt(255));
	}

	@Override
	public void update(int xDelta, int yDelta, int timeDelta) {
		// TODO Auto-generated method stub
		this.xCood += velocity.getX(); 
		this.yCood += velocity.getY();
	}

	@Override
	public void render(Graphics g, int xOffset, int yOffset) {
		// TODO Auto-generated method stub
		g.setColor(this.color);
		Circle c = new Circle(xCood, yCood, size);
		g.fill(c);
	} 
}
