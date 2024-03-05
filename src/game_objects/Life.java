package game_objects;

import javafx.scene.canvas.GraphicsContext;
import logic.MyCanvas;

/**
 * This class handles functions for Life. Main function is to draw the LifeImage.
 * Moreover it also enhances the effects of how the LifeImage is shown on screen when taken.
 * @author cajbj386
 */

public class Life extends GameObject {
	
	public Life(String imageString, int width, int height, double x, double y) {
		super(imageString, width, height, x, y, 0, 3);
	}

	@Override
	public void update() {
		increasePosY(getSpeedY());
		
		if (isTaken() && isShown()) {
			setWidth(getWidth() + 40);
			setHeight(getHeight() + 20);
			increasePosX(-20);
			increasePosY(-10);
		}
		
		if (getWidth() > 300) {
			setIsShown(false);
		}
	}

	@Override
	public void drawYourself(GraphicsContext gc) {
		if (getPosY() > MyCanvas.height) {
			reset(-5000);
		}
		
		if (isShown()) {
			gc.drawImage(getGameObjImg(), getPosX(), getPosY(), getWidth(), getHeight());
		}
	}
	
	@Override
	public void collidesWithPlayer(Player player) {
		setIsTaken(true);
		player.addLife();
	}
}
