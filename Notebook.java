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
	Notebook(){
		
		log = new Vector<Trial>();
		
	}
	
	//To add a trial
	public void addTrial(int n, double s, double d, long t){

		log.add(new Trial(n,s,d,t));
		
	}
	
	//To export to csv
	public void export(){
		
			try {
				
				String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				
				PrintWriter out = new PrintWriter(date + "_FittsLawExperiment_Log.csv");
				
				int size = log.size();
				Trial t; 
				String delim = ", ";
				String nl = "\n";
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
				
				out.close();
				
				
			} catch (IOException e) {
				
				System.out.println("Exportation Error | " + e.getMessage());
				System.exit(0);
				
			}
			
		
	}
		
	
}
