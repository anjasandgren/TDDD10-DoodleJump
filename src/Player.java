
import java.util.ArrayList;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public class Player extends GameObject {
	
	private int lifes = 1;
	private int score = 0;
	private int timeCounter = 0; //60 is one second
	private int bootCounter = 0;
	private boolean hasBoots = false;
	
	public Player(String imageString, int width, int height, double x, double y, double speed) {
		super(imageString, width, height, x, y, true, speed);
	}

	@Override
	public void update(Model model) {
		if (bootCounter > 300) {
			setHasBoots(false);
			bootCounter = 0;
		}
		
		double speed; 
		if (hasBoots) {
			speed = getSpeed() - 5;
			bootCounter += 1;
		} else {
			speed = getSpeed();
		}
		jumps(model);
		increaseSpeed(0.4);
		increasePosY(speed);
		if (timeCounter >= 30) {
			score += 5;
			timeCounter = 0;
		}
		timeCounter += 1;
	}

	@Override
	public void drawYourself(GraphicsContext gc) {
		
		if (getPosX() >= MyCanvas.width) {
			setPosX(0.0 - getWidth());
		} else if (getPosX() + getWidth() <= 0) {
			setPosX(MyCanvas.width);
		}
				
		if (getPosY() > MyCanvas.height-getHeight()) {
			setSpeed(-10);
		}

		gc.drawImage(getGameObj(), getPosX(), getPosY(), getWidth(), getHeight());
	}
	
	@Override
	public boolean isDead(Model model) {
		if (lifes < 0) {
			return true;
		} else if (getPosY() >= MyCanvas.height-getHeight()) {
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
				if (playerRec.intersects(platformRec) && getSpeed() > 0.0) {
					setSpeed(-10);
				}
			}
		}
	}
	
	public void collideHandler(ArrayList<GameObject> objects) {
		for (GameObject gameObj : objects) {
			if (collides(this, gameObj)) {
				gameObj.collidesWithPlayer(this);
			}
		}
	}
	
	public void removeLife() {
		lifes -= 1;
	}
	
	@Override
	public void addLife() {
		lifes += 1;
	}
	@Override
	public int getScore() {
		return score;
	}

	public void setHasBoots(boolean b) {
		hasBoots = b;
	}
}
