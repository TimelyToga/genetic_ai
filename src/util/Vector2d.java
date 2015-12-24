package util;

import units.Food;

public class Vector2d {

	private double x;
	private double y;
	
	/**
	 * 
	 * @param magnitude Magnitude in units
	 * @param direction Angle of vector in degrees
	 */
	public Vector2d(double magnitude, double direction){
		
		this.x = Math.cos(Math.toRadians(direction)) * magnitude;
		this.y = Math.sin(Math.toRadians(direction)) * magnitude;
	}
	
	public Vector2d(Vector2d original){
		this.x = original.x;
		this.y = original.y;
	}
	

//	public double getAngle(){
////		return Math.toDegrees(Math.atan2(x, y));
//		double factor;
//		if(y/x > 0){
//			factor = 1;
//		} else {
//			factor = -1;
//		}
//		double tempx = ((x > 0) ? x : (2*Math.PI + x) * 360 / (2*Math.PI));
//		tempx = Math.toRadians(tempx);
//		return Math.toDegrees(Math.atan2(tempx, y));
//	}
	
	/**
	 * 
	 * @return Angle of vector in degrees
	 */
	public double getAngle() {
	    double angle = Math.toDegrees(Math.atan2(this.y, this.x));

	    if(angle < 0){
	        angle += 360;
	    }

	    return angle;
	}

	
	public void setAngle(double angle){
		double magnitude = this.getMagnitude();
		this.x = Math.cos(Math.toRadians(angle)) * magnitude;
		this.y = Math.sin(Math.toRadians(angle)) * magnitude;
	}
	
	/**
	 * 
	 * @return Magnitude of the vector
	 */
	public double getMagnitude(){
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	public void setMagnitude(double magnitude){
		double angle = this.getAngle();
		this.x = Math.cos(Math.toRadians(angle)) * magnitude;
		this.y = Math.sin(Math.toRadians(angle)) * magnitude;
	}
	
	public Vector2d ccwNormal(){
		Vector2d copy = new Vector2d(this);
		
		double temp = copy.x; 
		copy.x = copy.y;
		copy.y = temp;
		
		return copy;
	}
	
	public double angleBetween(Vector2d otherVector){
		return Math.abs(this.getAngle() - otherVector.getAngle());
	}
	
	public Vector2d projectionVector(Vector2d other){
		double projMagnitude = Math.cos(this.angleBetween(other)) * other.getMagnitude();
		return new Vector2d(projMagnitude, this.getAngle());
	}
	
	public double distTo(Vector2d v){
		return Math.sqrt(Math.pow(this.getX() - v.getX(), 2) + Math.pow(this.getY() - v.getY() , 2));
	}
	
	
	// Getters and Setters 
	public void setX(double d){
		this.x = d;
	}
	
	public void setY(double d){
		this.y = d;
	}
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
}
