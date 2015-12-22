package genetic_ai;


import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.geom.Circle;

public class EvolutionGame extends BasicGame{

	public EvolutionGame() {
		super("EvolutionGame");
	}

	private static int screenX = 640;
	private static int screenY = 480;

	World world;
    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new EvolutionGame());
            app.setDisplayMode(screenX, screenY, false);

            System.out.println("Screen width, height: " + screenX + ", " + screenY);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(GameContainer container, Graphics g)
            throws SlickException {
//        for(Integer key: main.G.servantFactory.servantList.keySet()){
//            main.G.servantFactory.servantList.get(key).render(g);
//        }
    	
        world.render(g, 0, 0);

    }

	@Override
	public void init(GameContainer container) throws SlickException {
		container.setTargetFrameRate(60);
		container.setVSync(true);
		
		G.rgen = new Random();
		world = new World(screenX, screenY);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		// TODO Auto-generated method stub
        // Game exit
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
            System.exit(0);
        }
        
        world.update(delta);
	}
    
}
