/* This class represents a specific trial of the experiment
 */

public class Trial {
	
	//Data collected from the experiment
	public int trialNumber;
	public double targetSize;
	public double targetDist;
	public long time;
	
	//Constructor
	Trial(int n, double s, double d, long t){
		
		trialNumber = n;
		targetSize = s;
		targetDist = d;
		time = t;
		
	}
	
	

}
