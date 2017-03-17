import javafx.scene.control.Button;
import javafx.scene.shape.Circle;

/* This class is a simple CircleButton that allows dynamic changes
 * to size, position, and visibility while giving feedback to users
 * on clickability.
 */

public class CircleButton extends Button{

	//Data members for location, size and color
	private double X;
	private double Y;
	private double radius;
	private boolean isOn;
	private Circle c;
	
	//Here are the methods
	
	//Constructor
	// Here we create the button and circle at the specified location at the specified size
	CircleButton(double x, double y, double r){
		
		//Some formalities
		X = x - r; //Augment to center
		Y = y - r; //Augment to center
		radius = r;
		isOn = true;
		
		//Add a circle shape to the button
		c = new Circle(r);
		super.setShape(c);
		super.setMinSize(2*r, 2*r);
		super.setMaxSize(2*r, 2*r);
		
		//Set the style to remove the ugly javafx default style
		super.setStyle(
				
		//Remove the 3d highlighting
		"-fx-shadow-highlight-color : transparent;" +
		
		//Remove the border colors and shading
		"-fx-outer-border : transparent;" +
		"-fx-inner-border : transparent;" + 
		
		//Remove the focus ring
		"-fx-focus-color: transparent;" +
		"-fx-faint-focus-color : transparent;" + 
		
		//Change the color to black, this also lightens the button when pointed to 
		// and allows darkening when clicked. This is good for the user as they will have 
		// feedback during testing.
		"-fx-base : black;"
		
		);
		
		//Finally set the location
		super.setLayoutX(X);
		super.setLayoutY(Y);

	}
	
	//Here we define some methods to get and set the data members
	public void setX(double x){ 
		X = x;
		super.setLayoutX(x);
		
	}
	public void setY(double y){
		Y = y;
		super.setLayoutY(y);
		
	}
	public void setRad(double r){ 
		
		radius = r;
		c.setRadius(r);
		super.setMinSize(2*r, 2*r);
		super.setMaxSize(2*r, 2*r);
		
	}
	
	//Here we define some methods to get the values of data members
	public double getX(){return X;}
	public double getY(){return Y;}
	public double getRad(){return radius;}
	
	//This function allows us to turn the button on and off
	public void magician(){
		
		isOn = !isOn;
		super.setVisible(isOn);
		
	}
	
	
}
