import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
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
			if (counter > 120) {
				isGoingUp = false;
				counter = 0;
			}
			counter += 1;
		} else {
			increasePosY(3);
		}
		
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
			isGoingUp = true;
			return false;
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
	
	public void jumps(ArrayList<Rectangle2D> platformRecs) {
		Rectangle2D playerRec = getRectangle();

		for (Rectangle2D platformRec : platformRecs) {
			if (playerRec.intersects(platformRec) && !isGoingUp) {
				isGoingUp = true;
			}
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
