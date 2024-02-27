
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

		Scene startScene = new Scene(startMenu, 800, 400);
		MyCanvas frame = new MyCanvas(model);

		
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
		GameObject lavaPlatform = new Platform("lava_platform.png", 60, 12, 0, 0, true);
		model.addObjects(monster);
		model.addObjects(lavaPlatform);
		
		// Create player
		GameObject player = new Player("elephant.png", 60, 80, 0, 700);
		model.addObjects(player);

		
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
			System.out.println("Key Pressed");
			if (event.getCode() == KeyCode.ESCAPE) {
				primaryStage.setScene(startScene);
			} else {
				model.keyPressed(event);
			}
		});
		
		VBox gameOver = new VBox();
		
		String score, highScore1, highScore2, highScore3;
		
		//TODO, läs in istället för hårdkoda
		score = "300";
		highScore1 = "500";
		highScore2 = "465";
		highScore3 = "567";
		
		Label gameOverLabel = new Label ("G A M E   O V E R ! \n\n\n");
		gameOver.getChildren().add(gameOverLabel);
		
		if (Integer.parseInt(score) > Integer.parseInt(highScore1)) {
			highScore1 = score;
			highScore2 = highScore1;
			highScore3 = highScore2;
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
				model.update(frame.getWidth(), frame.getHeight());
				frame.repaint(model, frame.getWidth(), frame.getHeight());
				
				if (player.isDead(model.objects)) {
					primaryStage.setScene(gameOverScene);
					reset(player, model);
				}
				
//				//Hämtar dessas rektanglar utifrån deras position just nu
//				Rectangle2D playerRec = player.getRectangle();
//				Rectangle2D monsterRec = monster.getRectangle();
//				Rectangle2D lavaRec = lavaPlatform.getRectangle();
//				
//				//Kollar kollision
//				if (playerRec.intersects(monsterRec)) {
//					model.getPlayer().removeLife();
//					if (player.isDead()) {
//						primaryStage.setScene(gameOverScene);
//						player.setPosX(0);
//						player.setPosY(700);
//					}
//				}
//				
//				if (playerRec.intersects(lavaRec)) {
//					player.removeLife();
//					if (player.isDead()) {
//						primaryStage.setScene(gameOverScene);
//						player.setPosX(0);
//						player.setPosY(700);
//					}
//				}

				player.jumps(platformRecs);
				
				
			}
		}.start();
		
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