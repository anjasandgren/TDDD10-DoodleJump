package game_objects;

import javafx.scene.canvas.GraphicsContext;
import logic.Model;

public class Life extends GameObject {

	public Life(String imageString, int width, int height, double x, double y) {
		super(imageString, width, height, x, y, 3);
	}

	@Override
	public void update(Model model) {
		increasePosY(getSpeed());
	}

	@Override
	public void drawYourself(GraphicsContext gc) {
		if (getPosY() > 3000) {
			setPosY(-100);
			setIsShown(true);
		}
		
		if (isShown()) {
			gc.drawImage(getGameObj(), getPosX(), getPosY(), getWidth(), getHeight());
		}
	}
	
	@Override
	public void collidesWithPlayer(Player player) {
		setIsShown(false);
		player.addLife();
		
	}
}
