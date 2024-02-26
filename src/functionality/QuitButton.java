package functionality;

import java.io.FileInputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class QuitButton extends MyButton {

	public QuitButton() {
		try {
			Image quitButton = new Image(new FileInputStream("quit_button.png"));
			ImageView quitImageView = new ImageView(quitButton);
			quitImageView.setFitHeight(80);
			quitImageView.setFitWidth(110);
			setButton(quitImageView);
		} catch (Exception e) {
			System.out.println("Could not load playButton");
		}
	}
}
