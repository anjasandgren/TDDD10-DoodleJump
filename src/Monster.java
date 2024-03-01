
import javafx.scene.canvas.GraphicsContext;

public class Monster extends GameObject {
	private boolean isGoingRight;
	
	public Monster(String imageString, int width, int height, double x, double y, double speed) {
		super(imageString, width, height, x, y, false, speed);
		isGoingRight = true;
	}

	@Override
	public void update() {
		if (isGoingRight) {
			increasePosX(getSpeed());
			if (getPosX() + 70 > MyCanvas.width) {
				isGoingRight = false;
			} 
		} else {
			increasePosX(-3);
			if (getPosX() < 20) {
				isGoingRight = true;
			}
		}
		increasePosY(2);
	}

	@Override
	public void drawYourself(GraphicsContext gc) {
		if (getPosY() > 900) {
			setPosY(-200);
			setIsShown(true);
		}
		if (isShown()) {
			gc.drawImage(getGameObj(), getPosX(), getPosY(), getWidth(), getHeight());
		}
	}

	@Override
	public void collidesWithPlayer(GameObject player) {
		setIsShown(false);
		System.out.println("-1 liv");
		player.removeLife();
	}
}
