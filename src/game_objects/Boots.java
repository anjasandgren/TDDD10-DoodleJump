package game_objects;

import javafx.scene.canvas.GraphicsContext;

public class Boots extends GameObject {

	public Boots(String imageString, int width, int height, double x, double y) {
		super(imageString, width, height, x, y, 2);
	}

	@Override
	public void update() {
		increasePosY(getSpeedY());
	}

	@Override
	public void drawYourself(GraphicsContext gc) {
		if (getPosY() > 5000) {
			reset(-100);
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
