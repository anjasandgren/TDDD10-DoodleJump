package buffer_pics_test;

import java.io.FileInputStream;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BufferPicsMain extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Canvas canvas = new Canvas(800, 600);
		HBox layout = new HBox();
		layout.getChildren().add(canvas);
		Scene scene = new Scene(layout);
		primaryStage.setScene(scene);
		
		//FÃ¶rslag 1: Varje objekt sparar sin egen bild		
		GameObject banana = new GameObject("Banana.png", 150, 150);
		banana.drawYourOwnPicture(canvas.getGraphicsContext2D());
		
		//FÃ¶rslag 2: Varje bild sparas i en Manager-klass
		SpriteManager.add("Smiley.png");
		GameObject smiley = new GameObject("Smiley.png", 400, 300);
		smiley.drawFromSpriteMap(canvas.getGraphicsContext2D());
		
		//Sprite maps, eller hur man ritar ut en del av en bild
		Image car = new Image(new FileInputStream("Car.png"));
		double imageStartX = 200;
		double imageStartY = 500;
		double rectWidth = car.getWidth()/6;
		double rectHeight = car.getHeight()/6;
		double canvasPosX = 0;
		double canvasPosY = 0;
		
		canvas.getGraphicsContext2D().drawImage(car, imageStartX, imageStartY, rectWidth, rectHeight, canvasPosX, canvasPosY, rectWidth, rectHeight);
		
		primaryStage.show();
	}
}