import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import java.util.Random;

public class Platform extends GameObject {
	private boolean isLavaPlatform;
	
	public Platform(String imageString, int width, int height, double x, double y) {
		super(imageString, width, height, x, y, false);
	}

	public Platform(String imageString, int width, int height, int x, int y, boolean isLavaPlatform) {
		this(imageString, width, height, x, y);
		this.isLavaPlatform = isLavaPlatform;
	}
	
	public void update() {
		increasePosY(2);
	}

	public void drawYourself(GraphicsContext gc) {
		if (getPosY() >= MyCanvas.height) {
			Random rand = new Random();
			int x = rand.nextInt((int)MyCanvas.width - (int)getWidth());
			setPosX(x);
			setPosY(0.0);
		}
		gc.drawImage(getGameObj(), getPosX(), getPosY(), getWidth(), getHeight());
	}
	
	@Override
	public boolean diesFromCollision(Player player, Model model) {
		if (!isLavaPlatform) {
			return false;
		}
		
		Rectangle2D playerRec = player.getRectangle();
		Rectangle2D lavaPlatformRec = this.getRectangle();
		if (playerRec.intersects(lavaPlatformRec) && player.getSpeed() > 0 && getPosY() >= player.getHeight() + player.getPosY() - 3) {
			model.removeObject(this);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isPlatform() {
		return true;
	}
	
	@Override
	public boolean isLavaPlatform() {
		return isLavaPlatform;
	}
}
