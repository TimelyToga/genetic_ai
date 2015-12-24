package game;

import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.geom.Circle;

import ai_methods.NaiveAI;
import util.Logging;
import util.Vector2d;

public class EvolutionGame extends BasicGame{

	public EvolutionGame() {
		super("EvolutionGame");
	}

	private static int screenX = 640;
	private static int screenY = 480;

    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new EvolutionGame());
            app.setDisplayMode(screenX, screenY, false);
            
            Logging.log("Screen width, height: " + screenX + ", " + screenY, Logging.VERBOSE);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }	
    }

    @Override
    public void render(GameContainer container, Graphics g)
            throws SlickException {
    	
        G.world.render(g, 0, 0);

    }

	@Override
	public void init(GameContainer container) throws SlickException {
		container.setTargetFrameRate(60);
		container.setVSync(true);
		container.getGraphics().setBackground(new Color(255, 255, 255));
		
		G.rgen = new Random();
		G.world = new World(screenX, screenY);
		G.aiEngine = new NaiveAI();
		
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
        // Game exit
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
            System.exit(0);
        }
        
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
        	G.pauseNextIT = true;
        }
        
        G.world.update(delta);
	}
    
}
