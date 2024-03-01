package game_objects;

import javafx.scene.canvas.GraphicsContext;
import logic.MyCanvas;

import java.util.ArrayList;
import java.util.Random;
import logic.Model;

public class Platform extends GameObject {
	private boolean isLavaPlatform;
	
	public Platform(String imageString, int width, int height, double x, double y) {
		super(imageString, width, height, x, y, 0);
	}

	public Platform(String imageString, int width, int height, double x, double y, boolean isLavaPlatform) {
		this(imageString, width, height, x, y);
		this.isLavaPlatform = isLavaPlatform;
	}
	
	@Override
	public void update(Model model) {
		increasePosY(2);
	}
	
	@Override
	public void drawYourself(GraphicsContext gc) {
		if (getPosY() >= MyCanvas.height) {
			Random rand = new Random();
			int x = rand.nextInt((int)MyCanvas.width - (int)getWidth());
			setPosX(x);
			setPosY(-10);
			setIsShown(true);
		}
		
		if (isShown()) {
			gc.drawImage(getGameObj(), getPosX(), getPosY(), getWidth(), getHeight());
		}
	}
	
	@Override 
	public void collidesWithPlayer(Player player) {
		if (diesFromCollision(player)) {
			setIsShown(false);
			player.removeLife();
		}
	}
	
	@Override
	public boolean diesFromCollision(GameObject player) {
		if (!isLavaPlatform) {
			return false;
		} else if (collides(player, this) && player.getSpeed() > 0 && getPosY() >= player.getHeight() + player.getPosY() - 5) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean isPlatform() {
		return true;
	}
	
	@Override
	public boolean isLavaPlatform() {
		return isLavaPlatform;
	}
}
