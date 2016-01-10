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
import sim.Simulation;
import util.GeneticUtil;
import util.Logging;
import util.Vector2d;

public class EvolutionGame extends BasicGame{

	public EvolutionGame() {
		super("EvolutionGame");
	}

    public static void main(String[] args) {
		// Create Game
	    AppGameContainer app;
		try {
			app = new AppGameContainer(new EvolutionGame());
		    app.setDisplayMode(G.SCREEN_X, G.SCREEN_Y, false);
		    Logging.log("Screen width, height: " + G.SCREEN_X + ", " + G.SCREEN_Y, Logging.VERBOSE);

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
		G.world = new World(G.SCREEN_X, G.SCREEN_Y);
		G.aiEngine = new NaiveAI();
		G.se = new Simulation();
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
