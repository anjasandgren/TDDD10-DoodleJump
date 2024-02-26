

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
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
		
		VBox startMenu = new VBox();
		
		Button startButton = new Button("PLAY");
		Button hsButton = new Button("High Score");
		Button quitButton = new Button("QUIT");
		
		startMenu.getChildren().add(startButton);
		startMenu.getChildren().add(hsButton);
		startMenu.getChildren().add(quitButton);

		startMenu.setAlignment(Pos.CENTER);

		Scene startScene = new Scene(startMenu, 800, 400);

		
		GameObject player = new Player("elephant.png", 50, 70, 0, 0);
		model.addObjects(player);
		
		MyCanvas frame = new MyCanvas(model);


		int y = 0;
		for (int i = 0; i<10; ++i) {
			Random randX = new Random();
			int x = randX.nextInt(700);

			GameObject platform = new Platform("platform.png", 50, 30, x, y);
			y += frame.getHeight() / 10;
			model.addObjects(platform);
		}
		
		
		VBox SidePanel = new VBox();
		Label scorelabel = new Label("Score " + getScore());
		HBox layout = new HBox();
		layout.getChildren().add(frame);
		SidePanel.getChildren().add(scorelabel);
		layout.getChildren().add(SidePanel);
		Scene primaryScene = new Scene(layout);
		
		primaryStage.setScene(startScene);
		
		startButton.setOnAction(event -> {
			primaryStage.setScene(primaryScene);
		});
		
		hsButton.setOnAction(event -> {
			System.out.println("hs pressed");
		});
		
		quitButton.setOnAction(event -> {
			System.out.println("quit pressed");
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
				frame.repaint(model, frame.getWidth(), frame.getHeight());
			}
		}.start();
		
		primaryStage.show();
		
	}

	private String getScore() {
		return "300";
	}

}