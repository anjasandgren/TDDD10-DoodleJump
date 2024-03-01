package logic;

import java.util.ArrayList;

import game_objects.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class Model {
	ArrayList<GameObject> objects = new ArrayList<>();
	
	public void update() {
		for (GameObject gameObject : objects) {
			gameObject.update(this);
			try {
				if (getPlayer().collides(getPlayer(), gameObject)) {
					gameObject.diesFromCollision(getPlayer());
				}
			} catch (Exception e) {}
		}
	}
	
	public void keyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.RIGHT) {
			getPlayer().increasePosX(20);
		} else if (event.getCode() == KeyCode.LEFT) {
			getPlayer().increasePosX(-20);
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
		gc.clearRect(0, 0, MyCanvas.width, MyCanvas.height);
		gc.setFill(Color.LIGHTBLUE);
		gc.fillRect(0, 0, MyCanvas.width, MyCanvas.height);
		
		for (GameObject gameObject : objects) {
			gameObject.drawYourself(gc);
		}
	}
	
	public void addObjects(GameObject object) {
		objects.add(object);
	}

	public ArrayList<GameObject> getObjects() {
		return objects;
	}

	public void removeObject(GameObject gameObj) {
		objects.remove(gameObj);
	}
}
