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
	private double lastX, lastY;
	private int trialNum;
	private int screenBufferX, screenBufferY;
	
	//Now we need to set up the experiment
	Scientist(int screenX, int screenY){
		
		screenBufferX = (int) (screenX * 0.2);
		screenBufferY = (int) (screenY * 0.2);
		
		xFact = new NumberFactory(screenX - screenBufferX, 0);
		yFact = new NumberFactory(screenY - screenBufferY,  0);
		
		screenBufferX = screenX - screenBufferX;
		screenBufferY = screenY - screenBufferY;
		
		int sizeUB = ((screenX+screenY)/10);
		int sizeLB = sizeUB/24;
		radFact = new NumberFactory(sizeUB, sizeLB);
		
		pen = new Notebook();
		timer = new Stopwatch();

		dot = new CircleButton(0.0,0.0,50);
		dot.magician();
		
		lastX = 0.00;
		lastY = 0.00;
		
		trialNum = 0;
		
	}
	
	//We need a method to get the circle so we can show it
	CircleButton getDot(){
		
		return dot;
		
	}
	
	void startExperiment(double startingX, double startingY){
		
		lastX = startingX;
		lastY = startingY;
		
	}
	
	//Begins a trial by setting a new attributes for the dot and then starting the timer.
	void startTrial(){
		
		double x,y, r;
		x = xFact.getNumber();
		y = yFact.getNumber();
		r = radFact.getNumber();
		
		if(x + r > screenBufferX + r){
			
			x =- r;
			dot.setX(x);
			
		} else { dot.setX(x); }
		
		if(y+r > screenBufferY + r){
			
			y =- r;
			dot.setY(y);
			
		} else {dot.setY(y);}
		
		dot.setRad(r);
		
		trialNum++;
		dot.magician();
		timer.Start();
		
	}
	
	//Stops the timer and catalogs the data and sets the startingX/Y values to the ending
	// location for the next trial.
	void endTrial(){

		long time = timer.Stop();
		dot.magician();
		
		double dx = dot.getX() - lastX;
		double dy = dot.getY() - lastY;
		
		dx *= dx;
		dy *= dy;
		
		double dist = Math.sqrt(dx + dy);
		
		double size = dot.getRad();
		size = 2 * size;
		
		pen.addTrial(trialNum, size, dist, time);
		
	}
	
	//Exports the data
	void endExperiment(){
		
		pen.export();
		
	}
	
	int getTrialNumber(){
		
		return trialNum;
		
	}
	
	
}
