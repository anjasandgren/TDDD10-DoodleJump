package logic;

import game_objects.GameObject;
import game_objects.Player;
import javafx.scene.canvas.GraphicsContext;

public class Boots extends GameObject {

	public Boots(String imageString, int width, int height, double x, double y) {
		super(imageString, width, height, x, y, 2);
	}

	@Override
	public void update(Model model) {
		increasePosY(getSpeed());
	}

	@Override
	public void drawYourself(GraphicsContext gc) {
		if (getPosY() > 5000) {
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
		player.setHasBoots(true);
	}
}
