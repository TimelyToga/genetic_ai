package util;

import game.G;
import units.Food;
import units.Scrum;

public class VectorUtil {
	
	public static boolean isFoodInSector(Scrum s, double angle, double angleSpread, Vector2d food, double radius){
		// Calculate relV
		Vector2d relV = new Vector2d(food);
		relV.setX(food.getX() - s.xCood);
		relV.setY(food.getY() - s.yCood);
		
		// Calculate sectorStart / end vectors
		double halfAngle = angleSpread / 2;
		Vector2d sectorStart = new Vector2d(radius, angle - halfAngle);
		Vector2d sectorEnd = new Vector2d(radius, angle + halfAngle);
		
		boolean ccw = !areCW(sectorStart, relV);
		boolean cw = areCW(sectorEnd, relV);
		boolean rad = withinRadius(relV, Math.pow(radius, 2));
		
		if(G.pauseNextIT){
			System.out.println("Breakline to pause?");
		}
		
		return ( ccw && cw && rad);
	}
	
	public static boolean areCW(Vector2d v1, Vector2d v2){
		return ((v1.getY() * v2.getX()) - (v1.getX() * v2.getY())) > 0;
	}
	
	public static boolean withinRadius(Vector2d v, double radiusSquared){
		return (v.getX()*v.getX() + v.getY()*v.getY()) <= radiusSquared;
	}

}
