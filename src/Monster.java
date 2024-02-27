import javafx.scene.canvas.GraphicsContext;

public class Monster extends GameObject {

	private boolean goingRight;
	
	public Monster(String imageString, int width, int height, int x, int y) {
		super(imageString, width, height, x, y, false);
		goingRight = true;
	}

	@Override
	public void update(double width, double height) {
		if (goingRight) {
			increasePosX(5);
			if (getPosX() + 70 > width) {
				goingRight = false;
			} 
		} else {
			increasePosX(-5);
			if (getPosX() < 20) {
				goingRight = true;
			}
		}
		increasePosY(2);
	}

	@Override
	public void drawYourself(GraphicsContext gc, double width, double height) {
		gc.drawImage(getGameObj(), getPosX(), getPosY(), getWidth(), getHeight());
	}

}
