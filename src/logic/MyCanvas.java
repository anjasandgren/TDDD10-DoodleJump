package logic;

import javafx.scene.canvas.Canvas;

/**
 * This class is the window of the game where all game objects in model are drawn on.
 * The animation timer calls for the repaint method in this class 60 times per second.
 * It also contains static final variables of the width and height of the game window, which are reachable anywhere in the code.
 * @author anjsa296
 */

public class MyCanvas extends Canvas{
	private Model model;
	public static final double width=800, height=800;
	
	public MyCanvas(Model model) {
		setWidth(width);
		setHeight(height);
		this.model = model;
	}
	
	public void repaint() {
		model.repaint(getGraphicsContext2D());
	}
}
