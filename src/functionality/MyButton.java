package functionality;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyButton extends Canvas {
	private PaintSurface myCanvas;
	private static final int width = 35, height = 35;
	private GraphicsContext gc;
	
	public MyButton(PaintSurface myCanvas) {
		this.myCanvas = myCanvas;
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
	
	public PaintSurface getMyCanvas() {
		return myCanvas;
	}
	
	public GraphicsContext getGc() {
		return gc;
	}
}
