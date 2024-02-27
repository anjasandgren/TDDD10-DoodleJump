import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

public class Player extends GameObject {

	private int lifes;
	private boolean isGoingUp;
	private int counter;
	
	public Player(String imageString, int width, int height, double x, double y) {
		super(imageString, width, height, x, y, true);
		lifes = 0;
		counter = 0;
		isGoingUp = true;
	}

	@Override
	public void update(double width, double height) {
		if (isGoingUp) {
			increasePosY(-3);
			if (counter > 1000) {
				isGoingUp = false;
			}
		} else {
			increasePosY(3);
		}
		counter += 1;
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
	public boolean isDead(ArrayList<GameObject> objects) {
		if (getPosY() >= 800-getHeight()-3) {
			return true;
		} else if (lifes < 0) {
			return true;
		} else {
			for (GameObject gameObj : objects) {
				if (gameObj.diesFromCollision(this)) {
					return true;
				}
			}
			return false;
		}
	}

	public void removeLife() {
		lifes -= 1;
	}
	
	public boolean isGoingUp() {
		return isGoingUp;
	}
	
	public void setToGoingUp() {
		isGoingUp = true;
	}
}
