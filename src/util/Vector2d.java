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
	
	public void reflectX() {	
		double twoTheta;
		if(inFirstOrThirdQuadrant()) {
			// CW -2theta
			twoTheta = getCWAngleToAxis()*2.0;
			this.setAngle(this.getAngle() + -1.0*twoTheta);
		} else {
			// CCW +2theta
			twoTheta = getCCWAngleToAxis()*2.0;
			this.setAngle(this.getAngle() + twoTheta);
		}
	}
	
	public void reflectY() {
		double twoTheta;
		if(!inFirstOrThirdQuadrant()) {
			// CW -2theta
			twoTheta = getCWAngleToAxis()*2.0;
			this.setAngle(this.getAngle() + -1.0*twoTheta);
		} else {
			// CCW +2theta
			twoTheta = getCCWAngleToAxis()*2.0;
			this.setAngle(this.getAngle() + twoTheta);
		}
	}
	
	public boolean inFirstOrThirdQuadrant() {
		return (this.getX() * this.getY() > 0);
	}
	
	public double getCWAngleToAxis() {
		double angle = this.getAngle();
		while(angle >= 90) {
			angle -= 90;
		}
		
		return angle;
	}
	
	public double getCCWAngleToAxis() {
		double angle = this.getAngle();
		while(angle >= 90) {
			angle -= 90;
		}
		
		return 90 - angle;
	}
	
	/**
	 * Increase magnitude by %, by accelerating.
	 * 
	 * v' = aC * v
	 * @param accelerationConstant # >0 that represents % increase
	 */
	public void accelerate(double accelerationConstant) {
		this.setMagnitude(this.getMagnitude() * (1.0 + accelerationConstant));
	}
	
	/**
	 * Decrease magnitude by %, by decelerating.
	 * 
	 * v' = aC * v
	 * @param accelerationConstant # >0 that represents % increase
	 */
	public void decelerate(double accelerationConstant) {
		this.setMagnitude(this.getMagnitude() * (1.0 - accelerationConstant));
	}
}
