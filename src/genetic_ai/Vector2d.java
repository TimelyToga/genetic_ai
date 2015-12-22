package genetic_ai;

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
	
	/**
	 * 
	 * @return Angle of vector in degrees
	 */
	public double getAngle(){
		return Math.toDegrees(Math.atan(y / x));
	}
	
	public void setAngle(double angle){
		this.x = Math.cos(Math.toRadians(angle)) * this.getMagnitude();
		this.y = Math.sin(Math.toRadians(angle)) * this.getMagnitude();
	}
	
	/**
	 * 
	 * @return Magnitude of the vector
	 */
	public double getMagnitude(){
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	public void setMagnitude(double magnitude){
		this.x = Math.cos(Math.toRadians(this.getAngle())) * magnitude;
		this.y = Math.sin(Math.toRadians(this.getAngle())) * magnitude;
	}
	
	
	// Getters and Setters 
	public void setX(int newX){
		this.x = newX;
	}
	
	public void setY(int newY){
		this.y = newY;
	}
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
}
