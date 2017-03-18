/* This class represents the 'scientist' conducting the experiment.
 * It wraps the proceedings of data facilitation, storage, collection,
 * and exportation into a neat interface.
 */

public class Scientist {

	//First we need to assemble the tools
	private NumberFactory xFact, yFact , radFact;
	private Notebook pen;
	private CircleButton dot;
	private Stopwatch timer;
	
	//The last recorded location so we can calculate distance
	private double lastX, lastY;
	
	//To track trials for the record
	private int trialNum;
	
	//For alignment of the dots to keep them from wandering too far.
	private int screenBufferX, screenBufferY;
	
	//Now we need to set up the experiment
	Scientist(int screenX, int screenY){
		
		//Initialize our buffers
		screenBufferX = (int) (screenX * 0.2);
		screenBufferY = (int) (screenY * 0.2);
		
		//Initialize our location factories with the buffered ranges
		// So we can check
		xFact = new NumberFactory(screenX - screenBufferX, 0);
		yFact = new NumberFactory(screenY - screenBufferY,  0);
		
		//Initialize our buffers for checks
		screenBufferX = screenX - screenBufferX;
		screenBufferY = screenY - screenBufferY;
		
		//Initialize our size factory with corrected bounds for reasonable variation
		int sizeUB = ((screenX+screenY)/10);
		int sizeLB = sizeUB/24;
		radFact = new NumberFactory(sizeUB, sizeLB);
		
		//Initialize our stopwatch and notebook
		pen = new Notebook();
		timer = new Stopwatch();

		//Initialise our circle button and hide it.
		dot = new CircleButton(0.0,0.0,0);
		dot.magician();
		
		//Initialize our location tracking
		lastX = 0.00;
		lastY = 0.00;
		
		//We have not done any trials yet.
		trialNum = 0;
		
	}
	
	//We need a method to get the circle so we can show it
	CircleButton getDot(){
		
		return dot;
		
	}
	
	//To start our experiment we initialize our location tracker with our initial location.
	void startExperiment(double startingX, double startingY){
		
		lastX = startingX;
		lastY = startingY;
		
	}
	
	//Begins a trial by setting new attributes for the dot and then starting the timer.
	void startTrial(){
		
		//Get our new attributes
		double x,y, r;
		x = xFact.getNumber();
		y = yFact.getNumber();
		r = radFact.getNumber();
		
		//Check to see if we are too far and correct.
		//	Afterwards we set the attributes.
		if(x + r > screenBufferX + r){
			
			x =- r;
			dot.setX(x);
			
		} else { dot.setX(x); }
		
		if(y+r > screenBufferY + r){
			
			y =- r;
			dot.setY(y);
			
		} else {dot.setY(y);}
		
		//Set out size
		dot.setRad(r);
		
		//Increment our trials
		trialNum++;
		
		//Show the button
		dot.magician();
		
		//Start the timer.
		timer.Start();
		
	}
	
	//Stops the timer and catalogs the data and sets the startingX/Y values to the ending
	// location for the next trial.
	void endTrial(){

		//Stop the timer and hide the button.
		long time = timer.Stop();
		dot.magician();
		
		//Calculate out distance
		double dx = dot.getX() - lastX;
		double dy = dot.getY() - lastY;
		dx *= dx;
		dy *= dy;
		double dist = Math.sqrt(dx + dy);
		
		//Calculate out size
		double size = dot.getRad();
		size = 2 * size;
		
		//Record the data.
		pen.addTrial(trialNum, size, dist, time);
		
	}
	
	//Exports the data to a CSV.
	void endExperiment(){
		
		pen.export();
		
	}
	
	//Gets our trial number for experiment length.
	int getTrialNumber(){
		
		return trialNum;
		
	}
	
	
}
