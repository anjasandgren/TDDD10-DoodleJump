import javafx.scene.canvas.GraphicsContext;

public class Boots extends GameObject {

	public Boots(String imageString, int width, int height, double x, double y) {
		super(imageString, width, height, x, y, false);
	}

	@Override
	public void update() {
	}

	@Override
	public void drawYourself(GraphicsContext gc) {
	}
}
