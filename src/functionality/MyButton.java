package functionality;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyButton extends Canvas {
	private static final int width = 35, height = 35;
	private GraphicsContext gc;
	
	public MyButton() {
		this.gc = getGraphicsContext2D();
		setWidth(width);
		setHeight(height);
	}
}
