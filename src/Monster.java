import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public class Monster extends GameObject {
	private boolean isGoingRight;
	
	public Monster(String imageString, int width, int height, int x, int y) {
		super(imageString, width, height, x, y, false);
		isGoingRight = true;
	}

	@Override
	public void update() {
		if (isGoingRight) {
			increasePosX(5);
			if (getPosX() + 70 > MyCanvas.width) {
				isGoingRight = false;
			} 
		} else {
			increasePosX(-5);
			if (getPosX() < 20) {
				isGoingRight = true;
			}
		}
		increasePosY(2);
	}

	@Override
	public void drawYourself(GraphicsContext gc) {
		gc.drawImage(getGameObj(), getPosX(), getPosY(), getWidth(), getHeight());
	}

	@Override
	public boolean diesFromCollision(Player player, Model model) {
		Rectangle2D playerRec = player.getRectangle();
		Rectangle2D monsterRec = this.getRectangle();
	
		if (playerRec.intersects(monsterRec)) {
			System.out.println("-1 liv");
			model.removeObject(this);
			return true;
		} else {
			return false;
		}
	}
}
