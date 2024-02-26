
import javax.swing.JButton;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainClass extends Application{

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Model model = new Model();
		
		Button startButton = new Button("Press me!");
		Scene startScene = new Scene(startButton);
		
		startButton.setFill()
		MyCanvas frame = new MyCanvas(model);
		HBox layout = new HBox();
		layout.getChildren().add(frame);
		Scene primaryScene = new Scene(layout);
		
		primaryStage.setScene(startScene);
		
		startButton.setOnAction(event -> {
			primaryStage.setScene(primaryScene);
		});
		
		primaryScene.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ESCAPE) {
				primaryStage.setScene(startScene);
			} else {
				model.keyPressed(event);
			}
		});
		
		new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				model.update();
				frame.repaint(model);				
			}
		}.start();
		
		primaryStage.show();
		
	}

}