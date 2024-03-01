
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class MyCanvas extends Canvas{
	private Model model;
	private GraphicsContext gc;
	public static final double width=800, height=800;
	
	public MyCanvas(Model model) {
		setWidth(width);
		setHeight(height);
		this.model = model;
	}
	
	public void repaint() {
		model.repaint(getGraphicsContext2D());
	}

	public Model getModel() {
		return model;
	}
}
