package logic;

import game_objects.GameObject;
import java.io.FileInputStream;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Model {
	
	private Image backgroundImg;
	ArrayList<GameObject> objects = new ArrayList<>();
	
	public Model() {
		try {
			backgroundImg = new Image(new FileInputStream("background.png"));
		} catch (Exception e) {
			System.out.println("Couldn't load background image");
		}
	}
	
	public void update() {
		for (GameObject gameObject : objects) {
			gameObject.update();
			try {
				if (getPlayer().collides(getPlayer(), gameObject)) {
					gameObject.diesFromCollision(getPlayer());
				}
			} catch (Exception e) {}
		}
	}
	
	public void keyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.RIGHT) {
			getPlayer().setSpeedX(6);
		} else if (event.getCode() == KeyCode.LEFT) {
			getPlayer().setSpeedX(-6);
		}
	}

	public void keyReleased(KeyEvent event) {
		if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT) {
			getPlayer().setSpeedX(0);
		}
	}
	
	public GameObject getPlayer() {
		for (GameObject gameObj : objects) {
			if (gameObj.getIsPlayer()) {
				return gameObj;
			}
		}
		return null;
	}

	public void repaint(GraphicsContext gc) {
		gc.drawImage(backgroundImg, 0, 0,  MyCanvas.width,  MyCanvas.height);
		for (GameObject gameObject : objects) {
			gameObject.drawYourself(gc);
		}
	}
	
	public void addObjects(GameObject object) {
		objects.add(object);
	}
	
	public void removeObject(GameObject gameObj) {
		objects.remove(gameObj);
	}
	
	public void clearObjects() {
		objects.clear();
	}

	public ArrayList<GameObject> getObjects() {
		return objects;
	}
}
