import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Model {
	
	ArrayList<GameObject> objects = new ArrayList<>();
	
	public void update(double width, double height) {
		for (GameObject gameObject : objects) {
			gameObject.update(width, height);
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

	public void repaint(GraphicsContext gc, double width, double height) {
		gc.clearRect(0, 0, width, height);
		gc.setFill(Color.LIGHTBLUE);
		gc.fillRect(0, 0, width, height);
		
		for (GameObject gameObject : objects) {
			gameObject.drawYourself(gc, width, height);
		}
	}

	public void addObjects(GameObject object) {
		objects.add(object);
	}

	public boolean isDead() {
		return getPlayer().isDead();
	}

	public void removeLife() {
		getPlayer().removeLife();
	}
}
