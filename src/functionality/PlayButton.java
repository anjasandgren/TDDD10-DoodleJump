package functionality;

import java.io.FileInputStream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlayButton extends MyButton {
		
	public PlayButton() {
		try {
			Image play = new Image(new FileInputStream("play_button.png"));
			ImageView playImageView = new ImageView(play);
			playImageView.setFitHeight(100);
			playImageView.setFitWidth(130);
			setButton(playImageView);
		} catch (Exception e) {
			System.out.println("Could not load playButton");
		}
		
		setOnMouseClicked(event -> {
			System.out.println("PLAY!");
		});
	}
}
