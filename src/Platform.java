import javafx.scene.canvas.GraphicsContext;
import java.util.Random;

public class Platform extends GameObject {

	private boolean isLavaPlatform;
	
	public Platform(String imageString, int width, int height, int x, int y) {
		super(imageString, width, height, x, y, false);
	}

	public Platform(String imageString, int width, int height, int x, int y, boolean isLavaPlatform) {
		this(imageString, width, height, x, y);
		this.isLavaPlatform = isLavaPlatform;
	}
	
	public void update(double width, double height) {
		increasePosY(2);
	}

	public void drawYourself(GraphicsContext gc, double width, double height) {
		if (getPosY() >= height) {
			Random rand = new Random();
			int x = rand.nextInt((int)width - (int)getWidth());

			setPosX(x);
			setPosY(0.0);
		}
		
		if (getPosY() < 0) {
			setPosY(height);
		}
		gc.drawImage(getGameObj(), getPosX(), getPosY(), getWidth(), getHeight());
	}
}
