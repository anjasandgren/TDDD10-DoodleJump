import java.util.ArrayList;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public class Player extends GameObject {

	private int lifes;
	private int counter;
	private double speed;
	
	public Player(String imageString, int width, int height, double x, double y, double speed) {
		super(imageString, width, height, x, y, true);
		lifes = 2;
		counter = 0;
		this.speed = speed;
	}

	@Override
	public void update() {
		speed += 0.18;
		increasePosY(speed);
	}

	@Override
	public void drawYourself(GraphicsContext gc) {
		
		if (getPosX() >= MyCanvas.width) {
			setPosX(0.0);
		}
				
		if (getPosY() > MyCanvas.height-getHeight()) {
			speed = -10;
		}

		gc.drawImage(getGameObj(), getPosX(), getPosY(), getWidth(), getHeight());
	}
	
	@Override
	public boolean isDead(ArrayList<GameObject> objects) {
		for (GameObject gameObj : objects) {
			if (gameObj.diesFromCollision(this)) {
				lifes -= 1;
			}
		}
		
		if (lifes < 0) {
			return true;
		} else if (getPosY() >= MyCanvas.height-getHeight()-3) {
			return false; //ÄNDRA TILL TRUE SEN!
		} else {
			return false;
		}
	}
	
	public void jumps(Model model) {
		
		ArrayList<GameObject> objects = model.getObjects();
		Rectangle2D playerRec = this.getRectangle();

		for (GameObject gameObj : objects) {
			if (gameObj.isPlatform() && !gameObj.isLavaPlatform()) {
				Rectangle2D platformRec = gameObj.getRectangle();
				if (playerRec.intersects(platformRec) && speed > 0) {
					speed = -10;
				}
			}
		}
	}

	public void removeLife() {
		lifes -= 1;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public double getSpeed() {
		return speed;
	}
}
