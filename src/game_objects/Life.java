package game_objects;

import javafx.scene.canvas.GraphicsContext;

public class Life extends GameObject {

	public Life(String imageString, int width, int height, double x, double y) {
		super(imageString, width, height, x, y, 3);
	}

	@Override
	public void update() {
		increasePosY(getSpeedY());
	}

	@Override
	public void drawYourself(GraphicsContext gc) {
		if (getPosY() > 5000) {
			setPosY(-100);
			setIsShown(true);
		}
		
		if (isShown()) {
			gc.drawImage(getGameObjImg(), getPosX(), getPosY(), getWidth(), getHeight());
		}
	}
	
	@Override
	public void collidesWithPlayer(Player player) {
		setIsShown(false);
		player.addLife();
		
	}
}
