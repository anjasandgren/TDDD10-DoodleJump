package collision_test;

import javafx.geometry.Rectangle2D;

public class GameObject {

	private double x, y, width, height;
	
	public GameObject(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	//Returnerar en rektangel utifrÃ¥n objektets storlek och position
	public Rectangle2D getRectangle() {
		return new Rectangle2D(x, y, width, height);
	}

	// Uppdaterar positionen pÃ¥ ett objekt
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
}