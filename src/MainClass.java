
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
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
		
		MyCanvas frame = new MyCanvas(model);
		Scene startScene = new Scene(startMenu, MyCanvas.width, MyCanvas.height);

		// Create platforms
		ArrayList<Rectangle2D> platformRecs = new ArrayList<>();
		double y = 0;
		
		for (int i = 0; i<10; ++i) {
			Random randX = new Random();
			double x = randX.nextInt(700);

			GameObject platform = new Platform("platform.png", 60, 40, x, y);
			y += frame.getHeight() / 10;
			
			model.addObjects(platform);
			
			Rectangle2D platformRec = platform.getRectangle();
			platformRecs.add(platformRec);
		}
		
		// Create monsters
		GameObject monster = new Monster("monster.png", 70, 80, 0, -500);
		GameObject lavaPlatform = new Platform("lava_platform.png", 70, 17, 0, 0, true);
		model.addObjects(monster);
		model.addObjects(lavaPlatform);
		
		// Create powerups
		GameObject boots = new Boots("boots.png", 30, 50, 40, -600);
		GameObject lifes = new Lifes("lifes.png", 60, 50, 150, -600);
		model.addObjects(boots);
		model.addObjects(lifes);
		
		// Create player
		GameObject player = new Player("elephant.png", 60, 80, 0, 700, -8);
		model.addObjects(player);
		
		// Create structure
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
		
		VBox gameOver = new VBox();
		String score = "345", highScore1 = "500", highScore2 = "465", highScore3 = "355";
		
		// If new highscore
//		if (Integer.parseInt(score) >= Integer.parseInt(highScore3)) {
//			ObjectOutputStream out = null;
//			System.out.println("Success!");
//
//				if (Integer.parseInt(score) >= Integer.parseInt(highScore1)) {
//					// Nytt HS
//					out = new ObjectOutputStream(new FileOutputStream(highScore1));
//					out.writeObject(score);
//					highScore3 = highScore2;
//					highScore2 = highScore1;
//					highScore1 = score;
//					System.out.println("Success!");
//
//				} else if (Integer.parseInt(score) >= Integer.parseInt(highScore2)) {
//					// If second best
//					out = new ObjectOutputStream(new FileOutputStream(highScore2));
//					out.writeObject(score);
//					highScore3 = highScore2;
//					highScore2 = score;
//				} else {
//					// Third best
//					out = new ObjectOutputStream(new FileOutputStream(highScore3));
//					out.writeObject(score);
//					highScore3 = score;
//				}
//				out.close();
////	}
//		ObjectInputStream in = null;
//		in = new ObjectInputStream(new FileInputStream(highScore1));
//		highScore1 = (String) in.readObject();
//		System.out.println(highScore1);
//		in.close();


		
		//TODO, läs in istället för hårdkoda
		Label gameOverLabel = new Label ("G A M E   O V E R ! \n\n\n");
		gameOver.getChildren().add(gameOverLabel);
		
		if (Integer.parseInt(score) > Integer.parseInt(highScore1)) {
			highScore3 = highScore2;
			highScore2 = highScore1;
			highScore1 = score;

			Label newHighScoreLabel = new Label("NEW HIGH SCORE!!!\n\n");
			Label scoreLabel = new Label(score + "\n\n\n");
			gameOver.getChildren().addAll(newHighScoreLabel, scoreLabel);
		} else {
			Label scoreLabel = new Label("Your Score: " + score + "\n\n\n");
			Label highScoreLabel = new Label ("Top 3 Scores: ");
			Label highScore1Label = new Label(highScore1);
			Label highScore2Label = new Label(highScore2);
			Label highScore3Label = new Label(highScore3 + "\n \n");
			gameOver.getChildren().addAll(scoreLabel, highScoreLabel, highScore1Label, highScore2Label, highScore3Label);
		}
		
		HBox buttonsPanel = new HBox();
		Button playAgainButton = new Button("PLAY AGAIN");
		Button startMenuButton = new Button("START MENU");
		
		playAgainButton.setOnAction(event -> {
			primaryStage.setScene(primaryScene);
		});
		
		startMenuButton.setOnAction(event -> {
			primaryStage.setScene(startScene);
		});
		
		buttonsPanel.getChildren().add(playAgainButton);
		buttonsPanel.getChildren().add(startMenuButton);
		buttonsPanel.setAlignment(Pos.CENTER);

		gameOver.getChildren().add(buttonsPanel);
		gameOver.setAlignment(Pos.CENTER);

		Scene gameOverScene = new Scene(gameOver, 800, 400);

		new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				model.update();
				frame.repaint();
				
				if (player.isDead(model)) {
					primaryStage.setScene(gameOverScene);
					reset(player, model);
				}
				player.jumps(model);
				boots.collision(player, model);
				lifes.collision(player, model);


			}}.start();
		
		primaryStage.show();
	}

	protected void reset(GameObject player, Model model) {
		//TODO: Reset all gameObjects and the model
		player.setPosX(0);
		player.setPosY(500);
	}

	private String getScore() {
		return "300";
	}
}