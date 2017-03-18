import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Vector;

/* This class acts as the record keeper for the experiment
 * it stores data collected from the trials and exports it
 * to a file when told to do so.
 */

public class Notebook {

	//The log of trials
	private Vector<Trial> log;
	
	//Constructor
	// We just make our log
	Notebook(){
		
		log = new Vector<Trial>();
		
	}
	
	//To add a trial
	public void addTrial(int n, double s, double d, long t){

		log.add(new Trial(n,s,d,t));
		
	}
	
	//To export to csv
	public void export(){
		
			//We have to be careful here as we can have exceptions thrown.
			try {
				
				//Were going to append the date to the CSV name for record keepint
				String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				
				//We need to use the print writer as it can print strings and is much
				//	easier to use.
				//Here we also make our file.
				PrintWriter out = new PrintWriter(date + "_FittsLawExperiment_Log.csv");
				
				//Get the log size and maek a temporary trial for data extraction
				int size = log.size();
				Trial t; 
				
				//We need some delimiters for organization
				String delim = ", ";
				String nl = "\n";
				
				//Now we are going to iterate over the entire log
				//		extracting and writing the trials
				for (int iii = 0; iii < size; iii++){
					
					t = log.get(iii);
					
					out.write(Integer.toString(t.trialNumber));
					out.write(delim);
					
					out.write(Double.toString(t.targetSize));
					out.write(delim);
					
					out.write(Double.toString(t.targetDist));
					out.write(delim);
					
					out.write(Long.toString(t.time));
					out.write(nl);
					
					
				}
				
				//We need to close our file.
				out.close();
				
			//If an exception is thrown we print and error and die.
			} catch (IOException e) {
				
				System.out.println("Exportation Error | " + e.getMessage());
				System.exit(0);
				
			}
			
		
	}
		
	
}
