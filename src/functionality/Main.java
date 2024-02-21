import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class Main extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		VBox window 				= new VBox();
		HBox myBottomPanel  		= new BottomPanel();
		
		Label text = new Label("My play HP");

		// detta sker väl i main graphic
//		Image play = new Image(new FileInputStream("/home/cajbj386/TDDE10/projekt/projekt/play.png"));
//		ImageView image = new ImageView(play);
//		
//		window.getChildren().add(myBottomPanel);
//		window.getChildren().add(text);
//		window.getChildren().add(image);
//		
//		Scene mainScene = new Scene(window);
//
//		primaryStage.setTitle("Welcome to Doodle");
//		primaryStage.setScene(mainScene);
//		primaryStage.show();
	}
}
