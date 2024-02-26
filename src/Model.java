import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
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
		// TODO Auto-generated method stub
	}

	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub
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

}
