
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class MyButton extends Canvas {
	private static final int width = 35, height = 35;
	private GraphicsContext gc;
	
	public MyButton() {
		this.gc = getGraphicsContext2D();
		setWidth(width);
		setHeight(height);
	}

	public abstract void setOnAction(Object object);

}
