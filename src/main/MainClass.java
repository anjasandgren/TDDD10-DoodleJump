package main;

import graphic.GameLevel;
import graphic.SidePanel;
import game_objects.*;
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
import logic.Model;
import logic.MyCanvas;

public class MainClass extends Application{

	private GameLevel level;
	private Player player;
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
		SidePanel sidePanel = new SidePanel();
		MyCanvas frame = new MyCanvas(model);
		HBox layout = new HBox();
		layout.getChildren().add(frame);
		layout.getChildren().add(sidePanel);
		Scene gameScene = new Scene(layout, MyCanvas.width + 70, MyCanvas.height);
		
		
	// Game Over Scene
		VBox gameOver = new VBox();
		int score = 300;
		try {
			score = player.getScore(); //TODO: Detta blir alltid noll nu eftersom scoret hämtas en gång, och det är innan spelet börjar... Hämta scoret efter att player dött!!
		} catch (Exception e) {}
		String highScore1 = "500", highScore2 = "465", highScore3 = "355"; //TODO: Läs in istället för hårdkoda
		
		Label gameOverLabel = new Label ("G A M E   O V E R ! \n\n\n");
		gameOver.getChildren().add(gameOverLabel);
		
		if (score > Integer.parseInt(highScore1)) {
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
			int highscore = 300; //TODO, läs in från fil istället
			if (highscore > 500) {
				primaryStage.setScene(levelScene);
			} else {
				level = new GameLevel(model, false);
				createPlayer(model);
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
			level = new GameLevel(model, false); // Easy level
			createPlayer(model);
			primaryStage.setScene(gameScene);
		});
		
		level2Button.setOnAction(event -> {
			level = new GameLevel(model, true); // Hard level
			createPlayer(model);
			primaryStage.setScene(gameScene);
		});
		
		playAgainButton.setOnAction(event -> {
			int highscore = 600; //TODO: Läs in från fil istället
			if (highscore > 500) {
				primaryStage.setScene(levelScene);
			} else {
				level = new GameLevel(model, false); // Easy level
				createPlayer(model);
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

		gameScene.setOnKeyReleased(event -> {
			model.keyReleased(event);
		});
		
		startMenu.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				int highscore = 300; //TODO, läs in från fil istället
				if (highscore > 500) {
					primaryStage.setScene(levelScene);
				} else {
					level = new GameLevel(model, false);
					createPlayer(model);
					primaryStage.setScene(gameScene);
				}
			}
		});
		
//	// High Score handler (new class in logic?)
//		if (Integer.parseInt(score) >= Integer.parseInt(highScore3)) {
//			ObjectOutputStream out = null;
//			System.out.println("Success!");
//
//				if (Integer.parseInt(score) >= Integer.parseInt(highScore1)) {
//					// New HS
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
					player.collideHandler(model.getObjects());
					sidePanel.update(player.getScore(), player.getLifes(), player.getHasBoots());
					
					if (player.isDead(model)) {
						primaryStage.setScene(gameOverScene);
						reset();
					}
				} catch (Exception e) {}
			}
		}.start();
		primaryStage.show();
	}
	
	public void createPlayer(Model model) {
		LooseLife looseLife = new LooseLife("loose_life.png", 30, 20, 0, 0);
		player = new Player("elephant.png", "elephant_with_boots.png", 60, 80, MyCanvas.width/2, MyCanvas.height/2, -8, looseLife);
		model.addObjects(player);
		model.addObjects(looseLife);
	}
	
	public void reset() {
		// Update high score here!!
	}
}