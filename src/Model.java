import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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

	public void repaint(GraphicsContext gc) {
		for (GameObject gameObject : objects) {
			gameObject.drawYourself(gc);
		}
		
	}

}
