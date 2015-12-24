package ai_methods;

import util.Vector2d;

public class Action {

	public Vector2d velocity;
	
	public Action(Vector2d v){
		this.velocity = v;
	}
	
	public Action(int x, int y) {
		Vector2d temp = new Vector2d(0, 0);
		temp.setX(x);
		temp.setY(y);
		this.velocity = temp;
	}
	
	@Override
	public String toString() {
		return "(" + this.velocity.getX() + ", " + this.velocity.getY() + ")";
	}
}
