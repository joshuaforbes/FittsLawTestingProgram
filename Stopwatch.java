/* This class acts as a timing device for the trials.
 * It can handle multiple timings in succession.
 */

public class Stopwatch {

	//Our interval for the interval
	private long startTime, endTime;
	
	Stopwatch(){
		
		startTime = endTime = 0;
		
	}
	
	//Starts timing, clears out startTime beforehand.
	public void Start(){
		
		startTime = System.nanoTime();
		
	}
	
	//Ends Timing, clears out endTime beforehand.
	//Returns result of timing in milliseconds.
	//Clears out values.
	public long Stop(){

		endTime = System.nanoTime();
		long timeMS = (endTime - startTime)/1000000;
		startTime = endTime = 0;
		return timeMS;
		
		
	}
	
}
