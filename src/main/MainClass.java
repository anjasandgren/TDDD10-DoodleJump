package main;

import graphic.GameLevel;
import graphic.SidePanel;
import logic.HighScore;
import game_objects.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;



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
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
	
	//Model
		Model model = new Model();		
		HighScore highscore = new HighScore();	

		
	// Start Menu Scene
		VBox startMenu = new VBox();
		Button startButton = new Button("PLAY");
		Button hsButton = new Button("HIGHSCORE");
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
		
		// Buttons to gameOverScene
		Button playAgainButton = new Button("PLAY AGAIN");
		Button startMenuButton = new Button("START MENU");
				
	// Actions for buttons
		startButton.setOnAction(event -> {
			if ((Integer.parseInt(highscore.getScores().get(1))) > 500) {
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
				if (Integer.parseInt(highscore.getScores().get(1)) > 500) {
					primaryStage.setScene(levelScene);
				} else {
					level = new GameLevel(model, false);
					createPlayer(model);
					primaryStage.setScene(gameScene);
				}
			}
		});
		
		new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				model.update();
				frame.repaint();

				try {
					player.collideHandler(model.getObjects());
					sidePanel.update(player.getScore(), player.getLifes(), player.getHasBoots());
					
					if (player.isDead(model)) {
						reset(highscore);
						primaryStage.setScene(buildGameOverScene(highscore, playAgainButton, startMenuButton));
					}
				} catch (Exception e) {}
			}
		}.start();
		
		playAgainButton.setOnAction(event -> {
			if ((Integer.parseInt(highscore.getScores().get(1))) > 500) {
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
		
		primaryStage.show();
	}
	
	public void createPlayer(Model model) {
		LooseLife looseLife = new LooseLife("loose_life.png", 30, 20, 0, 0);
		player = new Player("elephant.png", "elephant_with_boots.png", 60, 80, MyCanvas.width/2, MyCanvas.height/2, -8, looseLife);
		model.addObjects(player);
		model.addObjects(looseLife);
	}
	
	public void reset(HighScore highscore) {
		highscore.updateScores(player);
	}
	
	public ArrayList<String> getScores(HighScore highscore) {
			return highscore.getScores();
	}
	
	public Scene buildGameOverScene(HighScore highscore, Button playAgainButton, Button startMenuButton) {
				VBox gameOver = new VBox();
	
			// new best score
				if (player.getScore() > Integer.parseInt((getScores(highscore).get(0)))) {
					Label gameOverLabel = new Label ("G A M E   O V E R ! \n\n\n");
					gameOver.getChildren().add(gameOverLabel);
					Label newHighScoreLabel = new Label("NEW HIGH SCORE!!!\n\n");
					Label scoreLabel = new Label(player.getScore() + "\n\n\n");
					gameOver.getChildren().addAll(newHighScoreLabel, scoreLabel);
				} else {
					Label scoreLabel = new Label("Your Score: " + player.getScore() + "\n\n\n");
					Label highScoreLabel = new Label ("Top 3 Scores: ");
					Label highScore1Label = new Label(getScores(highscore).get(1));
					Label highScore2Label = new Label(getScores(highscore).get(2));
					Label highScore3Label = new Label(getScores(highscore).get(3) + "\n \n");
					gameOver.getChildren().addAll(scoreLabel, highScoreLabel, highScore1Label, highScore2Label, highScore3Label);
				}
				
				HBox buttonsPanel = new HBox();
				buttonsPanel.getChildren().add(playAgainButton);
				buttonsPanel.getChildren().add(startMenuButton);
				buttonsPanel.setAlignment(Pos.CENTER);

				gameOver.getChildren().add(buttonsPanel);
				gameOver.setAlignment(Pos.CENTER);

				Scene gameOverScene = new Scene(gameOver, MyCanvas.width, MyCanvas.height);
				return gameOverScene;
	}
}