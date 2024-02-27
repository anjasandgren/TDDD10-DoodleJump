package buffer_pics_test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import graphicscontext_test.GcTester.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameObject {

	private double x, y;
	// För förslag 1
	private Image image;
	// För förslag 2
	private String imageName;
	
	public GameObject(String picture, double x, double y) {
		try {
			image = new Image(new FileInputStream(picture));
		} catch (FileNotFoundException e) {
			System.out.println("Picture not found!");
		}
		
		this.x = x;
		this.y = y;
		this.imageName = picture;
	}

	public void drawYourOwnPicture(GraphicsContext g) {
		g.drawImage(image, x, y, image.getWidth()*0.1, image.getHeight()*0.1);
	}

	public void drawFromSpriteMap(GraphicsContext g) {
		g.drawImage(SpriteManager.getImage(imageName), x, y);
	}
}