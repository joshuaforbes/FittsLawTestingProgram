//Libraries needed for the classes we use
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.layout.FlowPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.paint.Color;

//Main class as an extension of application because we are using Javafx
public class Main extends Application {
	
	//These are needed to initiate the tests
	int windowHW = 700; //Our core window size
	double startingX, startingY; //Our initial location of the mouse pointer
	
	//This method is needed to start the application under Javafx
	public void start(Stage primaryStage) {
		
		//Here we need to be careful as event handling can cause exceptions to be thrown
		try {
			
			//Here we initialize our stage -primary window- size
			primaryStage.setHeight(windowHW);
			primaryStage.setWidth(windowHW);
			
			//We do this so the user can't play with the window size and ruin our calculations.
			primaryStage.setResizable(false);
			
			//We set our stage title as well
			primaryStage.setTitle("HCI HW4 - jmforbe | Fits's Law Experiment Application");
			
			//Make the go button and set the correct size via the font.
			Button goButton = new Button("GO!");
			goButton.setFont(Font.font("Times New Roman", 45));
			
			//Here we make our Lables to give a message to the user
			Label msg1 = new Label("Welcome to the SAMU Fitts's Law Tester!");
			Label msg2 = new Label("");
			Label msg3 = new Label("");
			Label msg4 = new Label("");
			Label msg5 = new Label("Here we will be evaluating your reactivity");
			Label msg6 = new Label("to different button sizes and distances.");
			Label msg7 = new Label("");
			Label msg8 = new Label("The data from this test will be stored in");
			Label msg9 = new Label("a CSV file after the test has concluded.");
			Label msg10 = new Label("");
			Label msg11 = new Label("");
			Label msg12 = new Label("When you are ready to proceed click Go!");
			Label msg13 = new Label("");
			Label msg14 = new Label("");
			
			//Here we set the fonts
			msg1.setFont(Font.font("Times New Roman", 33));
			msg5.setFont(Font.font("Times New Roman", 25));
			msg6.setFont(Font.font("Times New Roman", 25));
			msg8.setFont(Font.font("Times New Roman", 25));
			msg9.setFont(Font.font("Times New Roman", 25));
			msg12.setFont(Font.font("Times New Roman", 25));
			
			/* We use a border pane as we need everything centred
			 * We use a flow pane because we need the text aligned to 
			 * 		typical American reading cultural norms.
			 * Here we add all of the items to a vertical flow pane first.
			 */
			 
			//Make a vertical flow pane
			FlowPane items = new FlowPane();
			items.setOrientation(Orientation.VERTICAL);
			items.setAlignment(Pos.CENTER);
			items.setColumnHalignment(HPos.CENTER);
			items.setRowValignment(VPos.CENTER);
			
			//Add everything to it
			items.getChildren().add(msg1);
			items.getChildren().add(msg2);
			items.getChildren().add(msg3);
			items.getChildren().add(msg4);
			items.getChildren().add(msg5);
			items.getChildren().add(msg6);
			items.getChildren().add(msg7);
			items.getChildren().add(msg8);
			items.getChildren().add(msg9);
			items.getChildren().add(msg10);
			items.getChildren().add(msg11);
			items.getChildren().add(msg12);
			items.getChildren().add(msg13);
			items.getChildren().add(msg14);
			items.getChildren().add(goButton);
			
			//Make out border pane
			BorderPane root = new BorderPane();
			
			//Add out items to the center of the border pane and then			
			root.setCenter(items);
			
			//Make our scene
			Scene scene = new Scene(root,windowHW,windowHW);
			scene.setFill(Color.WHITE);
			
			//Stage the scene
			primaryStage.setScene(scene);
			
			//Now we initialize our initial positions to use in calculations
			startingX = goButton.getLayoutX();
			startingY = goButton.getLayoutY();
			
			//Now the stage goes live
			primaryStage.show();
			
			//Here we handle the event of the go button being clicked.
			//		If it ever is we move to the game stage.
			goButton.setOnAction(new EventHandler<ActionEvent>(){

				public void handle(ActionEvent arg0) {
					
					game(primaryStage);
					
				}
				
			});
		
		//If an exception is thrown we die with and error
		} catch(Exception e) {
			
			System.out.println("Start-up Error | " + e.getMessage());
			System.exit(0);
		}
	}

	//This is the function where we collect data and conduct trials.
	public void game (Stage primaryStage){
		
		//We make our scientist and start the experiment
		Scientist s = new Scientist(windowHW, windowHW);
		s.startExperiment(startingX, startingY);
		
		//We need our button in order to display it
		CircleButton c = s.getDot();
		
		//We stage a scene with the button.
		//		Here we can be more loose with this process.
		Scene scene = new Scene(new Group(c), windowHW, windowHW);
		scene.setFill(Color.WHITE);
		primaryStage.setScene(scene);
		
		//We start out trial
		s.startTrial();
		
		//As each circle is clicked we stop and start another trial
		c.setOnAction(
				
				new EventHandler<ActionEvent>(){

					public void handle(ActionEvent arg0) {
						
						s.endTrial();
						
						//If we are done with the experiment we end it, send export the data and head to the end scene.
						if(s.getTrialNumber() == 50){
							
							s.endExperiment();
							end(primaryStage);
							
						}
						
						s.startTrial();
						
					}
					
				}
				
		);
		
		
	}
	
	//Here at the end we print a happy message to the user and allow them to exit.
	//		We have already exported the data so if the user accidentally exits
	//		another way we still completed our function.
	public void end (Stage primaryStage){
		
		Button endButton = new Button("Quit");
		endButton.setFont(Font.font("Times New Roman", 45));
	
		Label msg1 = new Label("Thank you for Participating!");
		Label msg2 = new Label("");
		Label msg3 = new Label("");
		
		//Here we set the fonts
		msg1.setFont(Font.font("Times New Roman", 33));
		
		/* We use a border pane as we need everything centred
		 * We use a flow pane because we need the text aligned to 
		 * 		typical American reading cultural norms.
		 * Here we add all of the items to a vertical flow pane first.
		 */
		 
		//Make a vertical flow pane
		FlowPane items = new FlowPane();
		items.setOrientation(Orientation.VERTICAL);
		items.setAlignment(Pos.CENTER);
		items.setColumnHalignment(HPos.CENTER);
		items.setRowValignment(VPos.CENTER);
		
		//Add everything to it
		items.getChildren().add(msg1);
		items.getChildren().add(msg2);
		items.getChildren().add(msg3);
		items.getChildren().add(endButton);
		
		//Make out border pane
		BorderPane root = new BorderPane();
		
		//Add out items to the center of the border pane and then			
		root.setCenter(items);
		
		//Make our scene
		Scene scene = new Scene(root,windowHW,windowHW);
		scene.setFill(Color.WHITE);
			
		//Stage the scene
		primaryStage.setScene(scene);
		
		//Then if the user clicks quit we exit the program
		endButton.setOnAction(new EventHandler<ActionEvent>(){

			public void handle(ActionEvent arg0) {
				
				System.exit(0);
				
			}
			
		});
		
	}
	
	//Our main consists of initiating the swing application as is normal.
	public static void main(String[] args) {
		
		launch(args);
		
		
	}
	
}
