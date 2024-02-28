import javafx.scene.canvas.GraphicsContext;

public class Lifes extends GameObject {

	public Lifes(String imageString, int width, int height, double x, double y) {
		super(imageString, width, height, x, y, false);
	}

	@Override
	public void update() {
	}

	@Override
	public void drawYourself(GraphicsContext gc) {
	}
}
