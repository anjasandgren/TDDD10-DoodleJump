
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
	
	private Point position;
	private Image gameObj;
	private Size size;
	private double speed;
	private boolean isShown = true;
	private boolean isPlayer;
	
	public GameObject(String imageString, int width, int height, double x, double y, boolean isPlayer, double speed) {
		try {
			gameObj = new Image(new FileInputStream(imageString));
		} catch (Exception e) {
			System.out.println("Couldn't load image");
		}
		position = new Point(x, y);
		size = new Size(width, height);
		this.isPlayer = isPlayer;
		this.speed = speed;
	}
	
	public void update() {
	}
	
	public void update(Model model) {
	}
	
	public abstract void drawYourself(GraphicsContext gc);
	
	public Rectangle2D getRectangle() {
		return new Rectangle2D(position.x, position.y, size.width, size.height);
	}
	
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
	
	public void collidesWithPlayer(GameObject player) {
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

	public boolean isDead(Model model) {
		return false;
	}
	
	public void removeLife() {
	}

	public boolean isGoingUp() {
		return false;
	}

	public void setToGoingUp() {
	}

	public boolean diesFromCollision(GameObject player) {
		return false;
	}

	public void jumps(Model model) {		
	}
	
	public boolean isPlatform() {
		return false;
	}

	public boolean isLavaPlatform() {
		return false;
	}
	
	public void setIsShown(boolean b) {
		isShown = b;
	}
	
	public boolean isShown() {
		return isShown;
	}
	
	public int getScore() {
		return 0;
	}
	
	public void increaseSpeed(double acceleration) {
		speed += acceleration;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public double getSpeed() {
		return speed;
	}

	public void addLife() {
	}

	public void setHasBoots(boolean b) {		
	}
}
