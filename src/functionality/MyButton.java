package functionality;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;

public abstract class MyButton extends Canvas{
	private ImageView button;
	
	public MyButton() {
	}

	public ImageView getButton() {
		return button;
	}
	
	public void setButton(ImageView button) {
		this.button = button;
	}
}
