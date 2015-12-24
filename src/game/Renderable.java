package game;

import org.newdawn.slick.Graphics;

/**
 * Created by Tim on 17/12/14.
 */
public abstract class Renderable {

    public boolean shouldRender = true;
    public boolean shouldUpdate = true;

    public int xCood = 0;
    public int yCood = 0;
    public int size = 0;

    public abstract void update(int xDelta, int yDelta, int timeDelta);

    public abstract void render(Graphics g, int xOffset, int yOffset);
    
    public double distanceBetween(Renderable r) {
    	return Math.sqrt(Math.pow(this.xCood - r.xCood, 2) + Math.pow(this.yCood - r.yCood, 2));
    }

}
