package game_objects;

import javafx.scene.canvas.GraphicsContext;

public class Life extends GameObject {
	
	public Life(String imageString, int width, int height, double x, double y) {
		super(imageString, width, height, x, y, 3);
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
		if (getPosY() > 5000) {
			reset(-100);
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
