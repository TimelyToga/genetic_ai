package util;

import units.Food;
import units.Scrum;

public class VectorUtil {
	
	public static boolean isFoodInSector(Scrum s, double angle, double angleSpread, Vector2d food, double radius){
		// Calculate relV
		Vector2d relV = new Vector2d(food);
		relV.setX(food.getX() - s.xCood);
		relV.setY(food.getY() - s.yCood);
		
		// Calculate sectorStart / end vectors
		double halfAngle = angle / 2;
		Vector2d sectorStart = new Vector2d(radius, angle - halfAngle);
		Vector2d sectorEnd = new Vector2d(radius, angle + halfAngle);
		
		return (!areCW(sectorStart, relV) && areCW(sectorEnd, relV) && withinRadius(Math.pow(radius, 2), relV));
	}
	
	public static boolean areCW(Vector2d v1, Vector2d v2){
		return (-1* v1.getX() * v2.getY() + v1.getY() + v2.getX()) > 0;
	}
	
	public static boolean withinRadius(double radiusSquared, Vector2d v){
		return v.getX()*v.getX() + v.getY()*v.getY() <= radiusSquared;
	}

}
