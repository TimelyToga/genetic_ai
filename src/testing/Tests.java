package testing;

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

}
