
import java.io.FileInputStream;
import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import graphicscontext_test.GcTester.Point;
import javafx.geometry.Rectangle2D;
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
	private boolean isPlayer;
	
	public GameObject(String imageString, int width, int height, double x, double y, boolean isPlayer) {
		try {
			gameObj = new Image(new FileInputStream(imageString));
		} catch (Exception e) {
			System.out.println("Couldn't load image");
		}
		position = new Point(x, y);
		size = new Size(width, height);
		this.isPlayer = isPlayer;
	}
	
	public abstract void update(double width, double height);

	public abstract void drawYourself(GraphicsContext gc, double width, double height);
	
	public Rectangle2D getRectangle() {
		return new Rectangle2D(position.x, position.y, size.width, size.height);
	}
	
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
	
	public boolean getIsPlayer() {
		return isPlayer;
	}

	public boolean isDead(ArrayList<GameObject> objects) {
		return false;
	}
	
	public void removeLife() {
	}

	public boolean isGoingUp() {
		return false;
	}

	public void setToGoingUp() {
	}

	public boolean diesFromCollision(Player player) {
		return false;
	}
}
