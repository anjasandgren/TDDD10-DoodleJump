
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
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

	private GameLevel level;
	private ArrayList<String> highscores;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
	
	//Model
		Model model = new Model();		
		
	// Start Menu Scene
		VBox startMenu = new VBox();
		Button startButton = new Button("PLAY");
		Button hsButton = new Button("High Score");
		Button quitButton = new Button("QUIT");
		startMenu.getChildren().add(startButton);
		startMenu.getChildren().add(hsButton);
		startMenu.getChildren().add(quitButton);
		startMenu.setAlignment(Pos.CENTER);Scene startMenuScene = new Scene(startMenu, MyCanvas.width, MyCanvas.height);
		primaryStage.setScene(startMenuScene);
		
		
	// Level Menu Scene
		VBox levelMenu = new VBox();
		Button level1Button = new Button("Level1");
		Button level2Button = new Button("Level2");
		levelMenu.getChildren().add(level1Button);
		levelMenu.getChildren().add(level2Button);
		levelMenu.setAlignment(Pos.CENTER);
		Scene levelScene = new Scene(levelMenu, MyCanvas.width, MyCanvas.height);
		
		
	// Game Scene
		VBox SidePanel = new SidePanel();
		MyCanvas frame = new MyCanvas(model);
		HBox layout = new HBox();
		layout.getChildren().add(frame);
		layout.getChildren().add(SidePanel);
		Scene gameScene = new Scene(layout, MyCanvas.width + 70, MyCanvas.height);
		
		
	// Game Over Scene
		VBox gameOver = new VBox();
		String score = "600"; //TODO: int score = player.getScore(); eller level.getScore();
		String highScore1 = "500", highScore2 = "465", highScore3 = "355"; //TODO: Läs in istället för hårdkoda
		
		Label gameOverLabel = new Label ("G A M E   O V E R ! \n\n\n");
		gameOver.getChildren().add(gameOverLabel);
		
		if (Integer.parseInt(score) > Integer.parseInt(highScore1)) {
			highScore3 = highScore2;
			highScore2 = highScore1;
			highScore1 = String.valueOf(score);

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
		buttonsPanel.getChildren().add(playAgainButton);
		buttonsPanel.getChildren().add(startMenuButton);
		buttonsPanel.setAlignment(Pos.CENTER);
		gameOver.getChildren().add(buttonsPanel);
		gameOver.setAlignment(Pos.CENTER);

		Scene gameOverScene = new Scene(gameOver, MyCanvas.width, MyCanvas.height);
		

	// Actions for buttons
		startButton.setOnAction(event -> {
			int highscore = 600; //TODO, läs in från fil istället
			if (highscore > 500) {
				primaryStage.setScene(levelScene);
			} else {
				level = new GameLevel(model, false);
				primaryStage.setScene(gameScene);
			}
		});
		
		hsButton.setOnAction(event -> {
			System.out.println("hs pressed");
		});
		
		quitButton.setOnAction(event -> {
			primaryStage.close();
		});
		
		level1Button.setOnAction(event -> {
			GameLevel level1 = new GameLevel(model, false);
			level = level1;
//			gameIsRunning = true;
			primaryStage.setScene(gameScene);
		});
		
		level2Button.setOnAction(event -> {
			GameLevel level2 = new GameLevel(model, true);
			level = level2;
//			gameIsRunning = true;
			primaryStage.setScene(gameScene);
		});
		
		playAgainButton.setOnAction(event -> {
			int highscore = 600; //TODO: Läs in från fil istället
			if (highscore > 500) {
				primaryStage.setScene(levelScene);
			} else {
				level = new GameLevel(model, false);
				primaryStage.setScene(gameScene);
			}
		});
		
		startMenuButton.setOnAction(event -> {
			primaryStage.setScene(startMenuScene);
		});
		
		gameScene.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ESCAPE) {
				primaryStage.setScene(startMenuScene);
			} else {
				model.keyPressed(event);
			}
		});
		
		
		
//	// High Score handler (new class in logic?)
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
//		}
//		ObjectInputStream in = null;
//		in = new ObjectInputStream(new FileInputStream(highScore1));
//		highScore1 = (String) in.readObject();
//		System.out.println(highScore1);
//		in.close();

		new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				model.update();
				frame.repaint();
				
				try {
					GameObject player = model.getPlayer();
					if (player.isDead(model)) {
						primaryStage.setScene(gameOverScene);
						reset(level, model);
					}
					
					player.collideHandler(model.getObjects());
					
					
					if (level.getBoots().collides(player, level.getBoots())) {
						//
					}
					if (level.getLife().collides(player, level.getLife())) {
						//
					}
				} catch (Exception e) {}
			}
		}.start();
		primaryStage.show();
	}
	
	protected void reset(GameLevel level, Model model) {
//		gameIsRunning = false;
		// Update high score here
	}
}