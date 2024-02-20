package functionality;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// grafiken här om hur knappen ser ut?
public class MyButton extends Canvas {
	private static final int width = 35, height = 35;
	private GraphicsContext gc;
	
	public MyButton() {
		this.gc = getGraphicsContext2D();
		setWidth(width);
		setHeight(height);
	}
	
	public void select() {
		getGc().setStroke(Color.BLACK);
		getGc().setLineWidth(3);
		getGc().strokeRect(0, 0, getWidth(), getHeight());
	}
	
	public void unselect() {
		getGc().setStroke(Color.WHITE);
		getGc().setLineWidth(3);
		getGc().strokeRect(0, 0, getWidth(), getHeight());
	}

	public GraphicsContext getGc() {
		return gc;
	}
}
