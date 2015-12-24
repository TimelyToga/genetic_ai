package game;

import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.geom.Circle;

import util.Vector2d;

public class EvolutionGame extends BasicGame{

	public EvolutionGame() {
		super("EvolutionGame");
	}

	private static int screenX = 640;
	private static int screenY = 480;

	World world;
    public static void main(String[] args) {
//        try {
//            AppGameContainer app = new AppGameContainer(new EvolutionGame());
//            app.setDisplayMode(screenX, screenY, false);
//
//            System.out.println("Screen width, height: " + screenX + ", " + screenY);
//            app.start();
//        } catch (SlickException e) {
//            e.printStackTrace();
//        }
    	
    	double size = 10; 
    	double numSensors = 4;
    	int xCood = 0;
    	int yCood = 0;
		Vector2d rotV = new Vector2d(size + 5, 0);
		double angleStep = 360 / numSensors;
		for (int a = 0; a < numSensors; a++) {
			int newX = (int) (xCood + rotV.getX());
			int newY = (int) (xCood + rotV.getY());
			
			int size2 = 3;
			Circle c1 = new Circle(newX, newY, size2);
			rotV.setAngle(rotV.getAngle() + angleStep);
			System.out.println(rotV.getAngle());
			System.out.println("(" + newX + ", " + newY + ")"); 
		}
		
//		for(int a = 0; a < 4; a++){
//			System.out.println(Math.sin(Math.toRadians(a*90)));
//		}
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
		
		G.rgen = new Random();
		G.world = new World(screenX, screenY);
		
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
        // Game exit
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
            System.exit(0);
        }
        
        G.world.update(delta);
	}
    
}
