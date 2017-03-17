package application;
	

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class Main extends Application {
	
	int windowHW = 700;
	double startingX, startingY;
	
	public void start(Stage primaryStage) {
		try {
			primaryStage.setHeight(windowHW);
			primaryStage.setWidth(windowHW);
			
			Button goButton = new Button("GO!");
			goButton.setFont(Font.font("Times New Roman", 45));
			
			BorderPane root = new BorderPane();
			root.setCenter(goButton);
			Scene scene = new Scene(root,windowHW,windowHW);
			
			primaryStage.setScene(scene);
			
			primaryStage.setResizable(false);
			
			primaryStage.setTitle("HCI HW4 - jmforbe | Fits Law Experiment Application");
			
			startingX = goButton.getLayoutX();
			startingY = goButton.getLayoutY();
			
			primaryStage.show();
			
			goButton.setOnAction(new EventHandler<ActionEvent>(){

				public void handle(ActionEvent arg0) {
					
					countDown(primaryStage);
					
				}
				
			});
			
		} catch(Exception e) {
			System.out.println("Error | " + e.getMessage());
			System.exit(0);
		}
	}
	
	public void countDown (Stage primaryStage){
		
        game(primaryStage);
		
	}
	
	public void game (Stage primaryStage){
		
		Scientist s = new Scientist(windowHW, windowHW);
		s.startExperiment(startingX, startingY);
		
		CircleButton c = s.getDot();
		primaryStage.setScene(new Scene(new Group(c), windowHW, windowHW));
		
		s.startTrial();
		c.setOnAction(
				
				new EventHandler<ActionEvent>(){

					public void handle(ActionEvent arg0) {
						
						s.endTrial();
						if(s.getTrialNumber() == 50){
							
							s.endExperiment();
							end(primaryStage);
							
						}
						s.startTrial();
						
					}
					
				}
				
		);
		
		
	}
	
	public void end (Stage primaryStage){
		
		Button endButton = new Button("Done");
		endButton.setFont(Font.font("Times New Roman", 45));
		
		BorderPane root = new BorderPane();
		root.setCenter(endButton);
		
		primaryStage.setScene(new Scene(root, windowHW, windowHW));
		
		endButton.setOnAction(new EventHandler<ActionEvent>(){

			public void handle(ActionEvent arg0) {
				
				System.exit(0);
				
			}
			
		});
		
	}
	
	public static void main(String[] args) {
		launch(args);
		
		
	}
}
