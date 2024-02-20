package graphic;

import functionality.BottomPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		
		VBox window 				= new VBox();
		HBox myBottomPanel  		= new BottomPanel();

		window.getChildren().add(myBottomPanel);
		Scene mainScene = new Scene(window);

		primaryStage.setTitle("Doodle Jump");
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}
}
