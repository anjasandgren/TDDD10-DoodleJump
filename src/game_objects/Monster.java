package game_objects;

import javafx.scene.canvas.GraphicsContext;
import logic.MyCanvas;

public class Monster extends GameObject {
	
	private boolean isGoingRight;
	
	public Monster(String imageString, int width, int height, double x, double y, double speedX) {
		super(imageString, width, height, x, y, speedX, 0);
		isGoingRight = true;
	}

	@Override
	public void update() {
		if (isTaken() && isShown()) {
			setWidth(getWidth() + 40);
			setHeight(getHeight() + 40);
			increasePosX(-20);
			increasePosY(-20);
		} else if (isGoingRight) {
			increasePosX(getSpeedX());
			if (getPosX() + 70 > MyCanvas.width) {
				isGoingRight = false;
			}
		} else {
			increasePosX((-1) * getSpeedX());
			if (getPosX() < 20) {
				isGoingRight = true;
			}
		}
		
		increasePosY(2);
		
		if (getWidth() > 200) {
			setIsShown(false);
		}
	}

	@Override
	public void drawYourself(GraphicsContext gc) {
		if (getPosY() > MyCanvas.height) {
			reset(-200);
		}
		if (isShown()) {
			gc.drawImage(getGameObjImg(), getPosX(), getPosY(), getWidth(), getHeight());
		}
	}

	@Override
	public void collidesWithPlayer(Player player) {
		setIsTaken(true);
		player.removeLife();
	}
}
