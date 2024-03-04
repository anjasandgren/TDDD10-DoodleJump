package main;

import graphic.GameLevel;
import graphic.SidePanel;
import logic.HighScore;
import game_objects.*;
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
	private boolean gameIsRunning = true;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
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
		
	// Create hsScene
		VBox hsMenu = new VBox();
		Label hsLabel = new Label("Top 3 Scores: ");
		Label hs1Label = new Label(String.valueOf(highscore.getScores().get(0)));
		Label hs2Label = new Label(String.valueOf(highscore.getScores().get(1)));
		Label hs3Label = new Label(String.valueOf(highscore.getScores().get(2)));
		hsMenu.getChildren().addAll(hsLabel, hs1Label, hs2Label, hs3Label);
		hsMenu.setAlignment(Pos.CENTER);
		Scene hsScene = new Scene(hsMenu, MyCanvas.width, MyCanvas.height);
		
		
		// Actions for pressed keys
		hsScene.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ESCAPE) {
				primaryStage.setScene(startMenuScene);
			} else {
				model.keyPressed(event);
			}
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
			
	// Run game
		new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				model.update();
				frame.repaint();

				try {
					player.collideHandler(model.getObjects());
					sidePanel.update(player.getScore(), player.getLifes(), player.getHasBoots());
					
					if (player.isDead(model) && gameIsRunning) {
						gameIsRunning = false;
						boolean newhighScore = player.getScore() > highscore.getScores().get(0);
						reset(highscore);
						primaryStage.setScene(buildGameOverScene(highscore, playAgainButton, startMenuButton, newhighScore));
						model.clearObjects();
					}
				} catch (Exception e) {}
			}
		}.start();
		
		
	// Actions for buttons
		startButton.setOnAction(event -> {
			if (highscore.getScores().get(0) > 500) {
				primaryStage.setScene(levelScene);
			} else {
				level = new GameLevel(model, false);
				createPlayer(model);
				primaryStage.setScene(gameScene);
			}
		});
		
		hsButton.setOnAction(event -> {
			primaryStage.setScene(hsScene);
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
			if (highscore.getScores().get(0) > 500) {
				primaryStage.setScene(levelScene);
				gameIsRunning = true;
			} else {
				level = new GameLevel(model, false); // Easy level
				createPlayer(model);
				primaryStage.setScene(gameScene);
				gameIsRunning = true;
			}
		});
		
		startMenuButton.setOnAction(event -> {
			primaryStage.setScene(startMenuScene);
		});
		
		primaryStage.show();
	}
	
	public void createPlayer(Model model) {
		LooseLife looseLife = new LooseLife("loose_life.png", 30, 20, 0, 0);
		player = new Player("elephant.png", "elephant_with_boots.png", 60, 80, MyCanvas.width/2, MyCanvas.height/2, 0, -8, looseLife);
		model.addObjects(player);
		model.addObjects(looseLife);
	}
	
	public void reset(HighScore highscore) {
		highscore.updateScores(player);
		player.setIsShown(false);
	}
	
	public Scene buildGameOverScene(HighScore highscore, Button playAgainButton, Button startMenuButton, boolean newHighScore) {
			VBox gameOver = new VBox();
	
			if (newHighScore) {
				Label gameOverLabel = new Label ("G A M E   O V E R ! \n\n\n");
				gameOver.getChildren().add(gameOverLabel);
				Label newHighScoreLabel = new Label("NEW HIGH SCORE!!!\n\n");
				Label scoreLabel = new Label(player.getScore() + "\n\n\n");
				gameOver.getChildren().addAll(newHighScoreLabel, scoreLabel);
			} else {
				Label scoreLabel = new Label("Your Score: " + player.getScore() + "\n\n\n");
				Label highScoreLabel = new Label ("Top 3 Scores: ");
				Label highScore1Label = new Label(String.valueOf(highscore.getScores().get(0)));
				Label highScore2Label = new Label(String.valueOf(highscore.getScores().get(1)));
				Label highScore3Label = new Label(String.valueOf(highscore.getScores().get(2)) + "\n \n");
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