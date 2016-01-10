package testing;

import java.util.Random;

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
	
	public void testGaussionDist() {
    	Random r = new Random();
    	double total = 0;
    	double iterations = 1000000;
    	
    	// Setup buckets
    	int numBuckets = 10;
    	int[] counts = new int[numBuckets];
    	double range = 20;
    	double min = range/2 *-1;
    	double max = min *-1;
    	double bucketSize = range / (double) numBuckets;
    	
    	for(int a = 0; a < iterations; a++) {
    		double b = r.nextGaussian();
    		
    		int c = 0;
    		boolean placed = false;
    		while (c < numBuckets || !placed) {
    			double lRange = c*bucketSize + min;
    			double rRange = lRange + bucketSize;
    			if(b > lRange && b <= rRange) {
    				counts[c]++; 
    				placed = true;
    			}
    			c++;
    		}
    		total += b;
    	}
    	
    	System.out.println("Avg: " + total / iterations);
    	for(Integer i : counts) {
    		System.out.println(i);
    	}
	}

}
