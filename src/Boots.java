
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public class Boots extends GameObject {

	public Boots(String imageString, int width, int height, double x, double y) {
		super(imageString, width, height, x, y, false, 3);
	}

	@Override
	public void update() {
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
	public void collidesWithPlayer(GameObject player) {
		System.out.println("Boots!");
		setIsShown(false);
		player.setHasBoots(true);
	}
	
//	@Override
//	public void collision(Player player, Model model) {
//		Rectangle2D playerRec = player.getRectangle();
//		Rectangle2D bootsRec = this.getRectangle();
//	
//		if (playerRec.intersects(bootsRec)) {
//			System.out.println("Boots!");
//			model.removeObject(this);
//			player.setHasBoots(true);
//		}
//	}
}
