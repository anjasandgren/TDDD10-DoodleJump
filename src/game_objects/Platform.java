package game_objects;

import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import logic.MyCanvas;

/**
 * This class handles functions for the different Platforms and is responsible for drawing them. 
 * Main function is to handle the differences between LavaPlatform and Platform, meanwhile
 * Player jumps on Platforms, player looses a life when trying to jump on a LavaPlatform.
 * @author cajbj386
 */

public class Platform extends GameObject {
	
	private boolean isLavaPlatform;
	
	public Platform(String imageString, int width, int height, double x, double y) {
		super(imageString, width, height, x, y, 0, 2);
		this.isLavaPlatform = false;
	}

	public Platform(String imageString, int width, int height, double x, double y, boolean isLavaPlatform) {
		this(imageString, width, height, x, y);
		this.isLavaPlatform = isLavaPlatform;
	}
	
	@Override
	public void update() {
		increasePosY(getSpeedY());
	}
	
	@Override
	public void drawYourself(GraphicsContext gc) {
		if (getPosY() >= MyCanvas.height) {
			Random rand = new Random();
			int x = rand.nextInt((int)MyCanvas.width - (int)getWidth());
			setPosX(x);
			setPosY(-10);
			setIsShown(true);
			setIsTaken(false);
		}
		
		if (isShown()) {
			gc.drawImage(getGameObjImg(), getPosX(), getPosY(), getWidth(), getHeight());
		}
	}
	
	@Override 
	public void collidesWithPlayer(Player player) {
		if (diesFromCollision(player)) {
			setIsShown(false);
			setIsTaken(true);
			player.removeLife();
		} else if (!isLavaPlatform) {
			jumpHandler(player);
		}
	}
	
	@Override
	public boolean diesFromCollision(GameObject player) {
		if (!isLavaPlatform) {
			return false;
		} else if (player.getSpeedY() > 0 && getPosY() >= player.getPosY() + player.getHeight() - 10) {
			return true;
		} else {
			return false;
		}
	}
	
	public void jumpHandler(Player player) {
		if (player.getSpeedY() > 0.0 && player.getPosY() + player.getHeight() - 25 <= getPosY()) {
			player.setSpeedY(-10);
		}
	}
}
