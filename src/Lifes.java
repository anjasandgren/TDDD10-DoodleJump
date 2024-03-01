import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public class Lifes extends GameObject {

	public Lifes(String imageString, int width, int height, double x, double y) {
		super(imageString, width, height, x, y, false);
	}

	@Override
	public void update() {
		increasePosY(4);
	}

	@Override
	public void drawYourself(GraphicsContext gc) {
		gc.drawImage(getGameObj(), getPosX(), getPosY(), getWidth(), getHeight());
	}
	
	public void collision(Player player, Model model) {
		Rectangle2D playerRec = player.getRectangle();
		Rectangle2D lifesRec = this.getRectangle();
	
		if (playerRec.intersects(lifesRec)) {
			System.out.println("lifes!");
			model.removeObject(this);
			player.addObject(this);
		} 			
	}
}
