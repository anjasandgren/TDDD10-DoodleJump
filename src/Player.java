import javafx.scene.canvas.GraphicsContext;

public class Player extends GameObject {

	public Player(String imageString, int width, int height, int x, int y) {
		super(imageString, width, height, x, y);
	}

	public void update() {
		increasePosY(-2);
	}

	public void drawYourself(GraphicsContext gc, double width, double height) {
		
		if (getPosX() >= width || getPosY() >= height) {
			setPosX(0.0);
			setPosY(0.0);
		}
		if (getPosY() < 0-getHeight()) {
			setPosY(height - getHeight());
		}

		gc.drawImage(getGameObj(), getPosX(), getPosY(), getWidth(), getHeight());
	}

}
