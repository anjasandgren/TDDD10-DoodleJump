package logic;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import graphic.Frame;

public class Main extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		VBox menu = new VBox();
//
//		MyButton play = new PlayButton();
//		ImageView playButton = play.getButton();
//		
//		MyButton quit = new QuitButton();
//		ImageView quitButton = quit.getButton();
//		
//		menu.getChildren().add(playButton);
//		menu.getChildren().add(quitButton);
		
		Frame myFrame = new Frame();
		
//		menu.getChildren().add(myFrame);
//		Scene mainScene = new Scene(myFrame);
//
//		primaryStage.setTitle("Welcome to Doodle Jump");
//		primaryStage.setScene(mainScene);
//		primaryStage.show();
	}
}
