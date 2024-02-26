package graphicscontext_test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

public class GcTester {

	// Internal class, mostly to show how it works in case you need it.
	public class Point {
		double x;
		double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	private Point position;
	private Image banana;
	private Image car;
	private Image smile;
	private int angle = 0;
	
	public GcTester() {
		position = new Point(0, 0);

		try {
			banana = new Image(new FileInputStream("Banana.png"));
			car = new Image(new FileInputStream("Car.png"));
			smile = new Image(new FileInputStream("Smiley.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

    /**
     * Sets the transform for the GraphicsContext to rotate around a pivot point.
     *
     * @param gc the graphics context the transform to applied to.
     * @param angle the angle of rotation.
     * @param px the x pivot coordinate for the rotation (in canvas coordinates).
     * @param py the y pivot coordinate for the rotation (in canvas coordinates).
     */
    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    /**
     * Draws an image on a graphics context.
     *
     * The image is drawn at (topLeftX, topLeftY) rotated by angle pivoted around the point:
     *   (topLeftX + image.getWidth() / 2, topLeftY + image.getHeight() / 2)
     *
     * @param g the graphics context the image is to be drawn on.
     * @param angle the angle of rotation.
     * @param topLeftX the top left x coordinate where the image will be plotted (in canvas coordinates).
     * @param topLeftY the top left y coordinate where the image will be plotted (in canvas coordinates).
     */
    private void drawRotatedImage(GraphicsContext g, Image image, double angle, double topLeftX, double topLeftY) {
    	g.save(); // saves the current state on stack, including the current transform
        rotate(g, angle, topLeftX + image.getWidth() / 2, topLeftY + image.getHeight() / 2);
        g.drawImage(image, topLeftX, topLeftY);
        g.restore(); // back to original state (before rotation)
    }
	
	/**
	 * Updates the position of the car and the banana.
	 */
	public void update() {
		// We move the position for the images each "tick", or call from the timer.
		// Can be adjusted to accomodate how fast you wish to move.
		position.x += 0;
		position.y += 2;
		angle += 3; // Updates the angle of the rotating image
	}

	/**
	 * Draws the items in the tester
	 * @param g the graphics context the tester is to be drawn on.
	 * @param maxX the width of the window
	 * @param maxY the height of the window
	 */
	public void draw(GraphicsContext g, double maxX, double maxY) {
		g.clearRect(0, 0, maxX, maxY);
		
		if (position.x >= maxX || position.y >= maxY) {
			// The position is exiting the screen, so we reset it
			position.x = 0.0;
			position.y = 0.0;
		}

		drawRotatedImage(g, smile, angle, 300, 300);
		// The car picture is scaled to 10% of its original size
		g.drawImage(car, position.x, position.y, car.getWidth()*0.1, car.getHeight()*0.1);
		// The banana has a set size of 100 x 100 
		g.drawImage(banana, position.x + 150, position.y + 150, 100, 100);
	}

}