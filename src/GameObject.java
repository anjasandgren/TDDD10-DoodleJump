
import java.io.FileInputStream;

import graphicscontext_test.GcTester.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public abstract class GameObject {

	public class Point {
		double x;
		double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	public class Size {
		double width;
		double height;

		public Size(double width, double height) {
			this.width = width;
			this.height = height;
		}
	}
	private Point position;
	private Image gameObj;
	private Size size;
	
	public GameObject(String imageString, int width, int height, int x, int y) {
		try {
			gameObj = new Image(new FileInputStream(imageString));
		} catch (Exception e) {
			System.out.println("Couldn't load image");
		}
		position = new Point(x, y);
		
		size = new Size(width, height);
	}
	
	public abstract void update();

	public abstract void drawYourself(GraphicsContext gc, double width, double height);

	public void increasePosX(double i) {
		position.x += i;
	}
	
	public void increasePosY(double i) {
		position.y += i;
	}
	
	public void setPosX(double i) {
		position.x = i;
	}
	
	public void setPosY(double i) {
		position.y = i;
	}
	
	public double getPosX() {
		return position.x;
	}
	
	public double getPosY() {
		return position.y;
	}
	
	public double getWidth() {
		return size.width;
	}
	
	public double getHeight() {
		return size.height;
	}
	
	public Image getGameObj() {
		return gameObj;
	}
}
