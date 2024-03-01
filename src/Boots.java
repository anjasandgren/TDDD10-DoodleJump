import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public class Boots extends GameObject {

	public Boots(String imageString, int width, int height, double x, double y) {
		super(imageString, width, height, x, y, false);
	}

	@Override
	public void update() {
		increasePosY(3);
	}

	@Override
	public void drawYourself(GraphicsContext gc) {
		gc.drawImage(getGameObj(), getPosX(), getPosY(), getWidth(), getHeight());
	}
	
	public void collision(Player player, Model model) {
		Rectangle2D playerRec = player.getRectangle();
		Rectangle2D bootsRec = this.getRectangle();
	
		if (playerRec.intersects(bootsRec)) {
			System.out.println("Boots!");
			model.removeObject(this);
			player.addObject(this);
		} 			
	}
}
