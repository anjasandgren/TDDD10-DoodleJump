import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Model {
	
	ArrayList<GameObject> objects = new ArrayList<>();
	
	public void update() {
		for (GameObject gameObject : objects) {
			gameObject.update();
		}
	}
	
	public void keyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.RIGHT) {
			getPlayer().increasePosX(10);
		} else if (event.getCode() == KeyCode.LEFT) {
			getPlayer().increasePosX(-10);
		} else {
			
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

	public void removeLife() {
		getPlayer().removeLife();
	}

	public ArrayList<GameObject> getObjects() {
		return objects;
	}
}
