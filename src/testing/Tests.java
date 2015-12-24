package testing;

import util.Vector2d;

public class Tests {
	
	public void testLinearMapping() {
    	double maxDist = 200.0;
    	double slope = -1.0 / 20.0;
    	double b = maxDist * slope * -1.0; 
    	
    	double curDist = 200.0;
    	double distDelta = 50.0; 
    	double curValue = 0.0;
    	while(curDist > 0) {
    		curDist -= distDelta;
    		
    		curValue = (int) (slope * curDist + b);
    		System.out.println("curValue: " + curValue + "; curDist: " + curDist);
    	}
	}
	
	public boolean testGetCWAngleToAxis() {
    	Vector2d v = new Vector2d(5, 45);
    	boolean b1 = v.getCWAngleToAxis() == 45;
    	v.setAngle(v.getAngle() + 80);
    	boolean b2 = v.getCWAngleToAxis() == 45;
    	v.setAngle(v.getAngle() + 90);
    	boolean b3 = v.getCWAngleToAxis() == 45;
    	v.setAngle(v.getAngle() + 90);
    	boolean b4 = v.getCWAngleToAxis() == 45;	
    	
    	return b1 && b2 && b3 && b4;
	}

}
