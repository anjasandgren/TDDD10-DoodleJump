import javafx.scene.canvas.GraphicsContext;

public class Player extends GameObject {

	private int lifes;
	
	public Player(String imageString, int width, int height, int x, int y) {
		super(imageString, width, height, x, y, true);
		lifes = 0;
	}

	@Override
	public void update(double width, double height) {
		increasePosY(0);
	}

	@Override
	public void drawYourself(GraphicsContext gc, double width, double height) {
		
		if (getPosX() >= width) {
			setPosX(0.0);
		}
				
		if (getPosY() < 0-getHeight()) {
			setPosY(height - getHeight());
		}

		gc.drawImage(getGameObj(), getPosX(), getPosY(), getWidth(), getHeight());
	}
	
	@Override
	public boolean isDead() {
		if (lifes > -1) {
			return false;
		} else {
			return true;
		}
	}
	
	public void removeLife() {
		lifes -= 1;
	}
}
