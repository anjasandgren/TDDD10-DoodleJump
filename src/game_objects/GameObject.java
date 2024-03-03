package game_objects;

import java.io.FileInputStream;
import java.util.ArrayList;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

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
	
	private Image gameObjImg;
	private Image secondGameObjImg;
	private Point position;
	private Size size;
	private double speedY;
	private boolean isShown = true;
	
	public GameObject(String imageString, String secondImageString, int width, int height, double x, double y, double speedY) {
		try {
			gameObjImg = new Image(new FileInputStream(imageString));
		} catch (Exception e) {
			System.out.println("Couldn't load image");
		}
		if (!secondImageString.equals("")) {
			try {
				secondGameObjImg = new Image(new FileInputStream(secondImageString));
			} catch (Exception e) {
				System.out.println("Couldn't load second image");
			}
		}
		position = new Point(x, y);
		size = new Size(width, height);
		this.speedY = speedY;
	}
	
	public GameObject(String imageString, int width, int height, double x, double y, double speed) {
		this(imageString, "", width, height, x, y, speed);
	}
	
	public abstract void update();
	
	public abstract void drawYourself(GraphicsContext gc);
	
	public boolean collides(GameObject obj1, GameObject obj2) {
		Rectangle2D obj1Rec = obj1.getRectangle();
		Rectangle2D obj2Rec = obj2.getRectangle();
		
		if (obj1 == obj2 || obj1.getPosY() <= 0 || obj2.getPosY() <= 0 || !obj1.isShown() || !obj2.isShown()) {
			return false;
		} else if (obj1Rec.intersects(obj2Rec)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void collideHandler(ArrayList<GameObject> objects) {
	}
	
	public void collidesWithPlayer(Player player) {
	}
	
	public Rectangle2D getRectangle() {
		return new Rectangle2D(position.x, position.y, size.width, size.height);
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
	
	public void increasePosX(double i) {
		position.x += i;
	}
	
	public void increasePosY(double i) {
		position.y += i;
	}
	
	public void setSpeedX(double speed) {
	}
	
	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}
	
	public double getSpeedY() {
		return speedY;
	}
	
	public void increaseSpeedY(double acceleration) {
		speedY += acceleration;
	}
	
	public double getWidth() {
		return size.width;
	}
	
	public double getHeight() {
		return size.height;
	}
	
	public Image getGameObjImg() {
		return gameObjImg;
	}
	
	public Image getSecondGameObjImg() {
		return secondGameObjImg;
	}
	
	public boolean getIsPlayer() {
		return false;
	}

	public boolean diesFromCollision(GameObject player) {
		return false;
	}
	
	public void setIsShown(boolean isShown) {
		this.isShown = isShown;
	}
	
	public boolean isShown() {
		return isShown;
	}

	public int getScore() {
		return 0;
	}
}
