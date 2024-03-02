package game_objects;

import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import logic.MyCanvas;

public class Platform extends GameObject {
	
	private boolean isLavaPlatform;
	
	public Platform(String imageString, int width, int height, double x, double y) {
		super(imageString, width, height, x, y, 2);
	}

	public Platform(String imageString, int width, int height, double x, double y, boolean isLavaPlatform) {
		this(imageString, width, height, x, y);
		System.out.println("lava");
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
		}
		
		if (isShown()) {
			gc.drawImage(getGameObjImg(), getPosX(), getPosY(), getWidth(), getHeight());
		}
	}
	
	@Override 
	public void collidesWithPlayer(Player player) {
		if (diesFromCollision(player)) {
			setIsShown(false);
			player.removeLife();
		} else if (!isLavaPlatform) {
			jumpHandler(player);
		}
	}
	
	@Override
	public boolean diesFromCollision(GameObject player) {
		if (!isLavaPlatform) {
			return false;
		} else if (player.getSpeedY() > 0 && getPosY() >= player.getHeight() + player.getPosY() - 5) {
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
