package genetic_ai;

import java.time.zone.ZoneOffsetTransitionRule.TimeDefinition;
import java.util.HashSet;
import java.util.Set;

import org.newdawn.slick.Graphics;

public class World {

	
	private int xSize;
	private int ySize;
	
    private Set<Renderable> renderables;
	
	public World(int xSize, int ySize){
		this.xSize = xSize;
		this.ySize = ySize;
		
		renderables = new HashSet<Renderable>();
		Vector2d s1Direction = new Vector2d(3, 45);
		Scrum s1 = new Scrum(100, 100, s1Direction, 50, 1000, 10, 0);
		renderables.add(s1
				);
	}
	
	public void render(Graphics g, int xOffset, int yOffset){
		for(Renderable r : renderables){
			r.render(g, xOffset, yOffset);
		}
	}
	
	public void update(int timeDelta){
		for(Renderable r: renderables){
			r.update(0, 0, timeDelta);
		}
	}
	
	
}
