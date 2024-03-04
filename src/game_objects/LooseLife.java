package game_objects;

import javafx.scene.canvas.GraphicsContext;
import logic.MyCanvas;

public class LooseLife extends GameObject{
	
	public LooseLife(String imageString, int width, int height, double x, double y) {
		super(imageString, width, height, x, y, false);
	}

	@Override
	public void update() {
		if (isTaken()) {
			setWidth(getWidth() + 3);
			setHeight(getHeight() + 2.3);
			increasePosX(-1);
			increasePosY(-0.75);
			setIsShown(true);
		}
		
		if (getWidth() > 120) {
			setIsShown(false);
			setIsTaken(false);
			setWidth(getOriginalWidth());
			setHeight(getOriginalHeight());
		}
	}

	@Override
	public void drawYourself(GraphicsContext gc) {
		if (isShown()) {
			gc.drawImage(getGameObjImg(), MyCanvas.width/2 - getWidth()/2 , MyCanvas.height/2 - getHeight()/2, getWidth(), getHeight());
		}
	}
}
