package game_objects;

import javafx.scene.canvas.GraphicsContext;
import logic.MyCanvas;

public class Monster extends GameObject {
	private boolean isGoingRight;
	
	public Monster(String imageString, int width, int height, double x, double y, double speed) {
		super(imageString, width, height, x, y, speed);
		isGoingRight = true;
	}

	@Override
	public void update() {
		if (isGoingRight) {
			increasePosX(getSpeedY());
			if (getPosX() + 70 > MyCanvas.width) {
				isGoingRight = false;
			} 
		} else {
			increasePosX(-3);
			if (getPosX() < 20) {
				isGoingRight = true;
			}
		}
		increasePosY(2);
	}

	@Override
	public void drawYourself(GraphicsContext gc) {
		if (getPosY() > 900) {
			setPosY(-200);
			setIsShown(true);
		}
		if (isShown()) {
			gc.drawImage(getGameObjImg(), getPosX(), getPosY(), getWidth(), getHeight());
		}
	}

	@Override
	public void collidesWithPlayer(Player player) {
		setIsShown(false);
		player.removeLife();
	}
}
