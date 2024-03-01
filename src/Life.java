
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public class Life extends GameObject {

	public Life(String imageString, int width, int height, double x, double y) {
		super(imageString, width, height, x, y, false, 4);
	}

	@Override
	public void update() {
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
	public void collidesWithPlayer(GameObject player) {
		System.out.println("+ 1 liv");
		setIsShown(false);
		player.addLife();
		
	}
}
