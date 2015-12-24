package genetic_ai;

import java.time.zone.ZoneOffsetTransitionRule.TimeDefinition;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.Graphics;

import units.Food;
import units.Scrum;

public class World {

	
	public int xSize;
	public int ySize;
	
	public int iterations;
	
    public Set<Scrum> scrums;
    public Set<Renderable> renderables;
    public Set<Food> foods;
    
    private int initialNumScrums = 1;
    private int maxNumScrums = 100;
	
	public World(int xSize, int ySize){
		this.xSize = xSize;
		this.ySize = ySize;
		
		scrums = new HashSet<Scrum>();
		renderables = new HashSet<Renderable>();
		foods = new HashSet<Food>();
		
		// Initialize population
		for(int a = 0; a < initialNumScrums; a++){
			addRandScrum();
		}
		
		addRandFood();
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
	
	public Food[] getFoods(){
		Food[] output = new Food[this.foods.size()];
		int a = 0;
		for(Food f: this.foods){
			output[a] = f;
			a++;
		}
		return output;
	}
	
	public void addRandFood(){
		int x = G.rgen.nextInt(this.xSize);
		int y = G.rgen.nextInt(this.ySize);
		Food f = new Food(x, y);
		this.renderables.add(f);
		this.foods.add(f);
	}
	
	public void addRandScrum(){
		int x = G.rgen.nextInt(this.xSize);
		int y = G.rgen.nextInt(this.ySize);
		Scrum s = Scrum.randScrum(x, y);
		this.renderables.add(s);
		this.scrums.add(s);
	}
	
	
}
