package units;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

import util.Vector2d;
import game.G;
import game.Renderable;
import game.World;

public class Food extends Renderable {

	private int energyValue = 300;
	private float size = (energyValue / 100) + 1;
	private boolean isAvailable = true;
	Color color = new Color(255, 0,0);
	
	public Food(int x, int y){
		this.xCood = x;
		this.yCood = y;
	}
	
	/**
	 * Creates food in center of world
	 */
	public Food() {
		this.xCood = G.world.xSize / 2;
		this.yCood = G.world.ySize / 2;
	}
	
	public int consume(){
		this.isAvailable = false;
		G.world.foods.remove(this);
		G.world.renderables.remove(this);
		return this.energyValue;
	}
	
	public Vector2d toVector2d(){
		Vector2d output = new Vector2d(0, 0);
		output.setX(xCood);
		output.setY(yCood);
		return output;
	}
	
	@Override
	public void update(int xDelta, int yDelta, int timeDelta) {
	}

	@Override
	public void render(Graphics g, int xOffset, int yOffset) {
		g.setColor(this.color);
		Circle c = new Circle(this.xCood, this.yCood, size);
		g.fill(c);
	}
	
}
