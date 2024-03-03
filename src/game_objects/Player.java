package game_objects;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import logic.Model;
import logic.MyCanvas;

public class Player extends GameObject {
	
	private int lifes = 1;
	private int score = 0;
	private int timeCounter = 0; //60 is one second
	private int bootCounter = 0;
	private boolean hasBoots = false;
	private double speedX = 0;
	
	public Player(String image, String imageWithBoots, int width, int height, double x, double y, double speed) {
		super(image, imageWithBoots, width, height, x, y, speed);
	}

	@Override
	public void update() {
//		if (getPosY() <= 0) {
//			setSpeed(0);
//			setPosY(10);
//		}
		
		if (bootCounter > 480) { //Has boots for 8 seconds
			setHasBoots(false);
			bootCounter = 0;
		}
		
		double speedY;
		if (hasBoots) {
			speedY = getSpeedY() - 4; // Jumps higher with the boots
			bootCounter += 1;
		} else {
			speedY = getSpeedY();
		}
		
		increasePosX(speedX);
		increaseSpeedY(0.5);
		increasePosY(speedY);
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
				
//		if (getPosY() > MyCanvas.height-getHeight()) {
//			setSpeedY(-10);
//		}

		if (hasBoots) {
			gc.drawImage(getSecondGameObjImg(), getPosX(), getPosY(), getWidth(), getHeight());
		} else {
			gc.drawImage(getGameObjImg(), getPosX(), getPosY(), getWidth(), getHeight());
		}
	}
	
	public boolean isDead(Model model) {
		if (lifes <= 0) {
			return true;
		} else if (getPosY() >= MyCanvas.height) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void collideHandler(ArrayList<GameObject> objects) {
		for (GameObject gameObj : objects) {
			if (collides(this, gameObj)) {
				gameObj.collidesWithPlayer(this);
			}
		}
	}
	
	public void addLife() {
		lifes += 1;
	}
	
	public void removeLife() {
		lifes -= 1;
	}
	
	public int getLifes() {
		return lifes;
	}
	
	@Override
	public int getScore() {
		return score;
	}

	public void setHasBoots(boolean b) {
		hasBoots = b;
	}
	
	public boolean getHasBoots() {
		return hasBoots;
	}
	
	@Override
	public boolean getIsPlayer() {
		return true;
	}
	
	@Override
	public void setSpeedX(double speed) {
		speedX = speed;
	}
}
