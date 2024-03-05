package game_objects;

import javafx.scene.canvas.GraphicsContext;
import logic.MyCanvas;

/**
 * This class handles functions for Boots and is responsible for drawing them. 
 * @author cajbj386
 */

public class Boots extends GameObject {

	public Boots(String imageString, int width, int height, double x, double y) {
		super(imageString, width, height, x, y, 0, 2);
	}

	@Override
	public void update() {
		increasePosY(getSpeedY());
	}

	@Override
	public void drawYourself(GraphicsContext gc) {
		if (getPosY() > MyCanvas.height) {
			reset(-3000);
		}
		
		if (isShown()) {
			gc.drawImage(getGameObjImg(), getPosX(), getPosY(), getWidth(), getHeight());
		}
	}
	
	@Override
	public void collidesWithPlayer(Player player) {
		setIsShown(false);
		player.setHasBoots(true);
	}
}
