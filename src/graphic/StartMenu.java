package graphic;

import functionality.MyButton;
import functionality.PlayButton;
import functionality.QuitButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class StartMenu extends VBox {

	public StartMenu() {
		MyButton play = new PlayButton();
		ImageView playButton = play.getButton();
		
		MyButton quit = new QuitButton();
		ImageView quitButton = quit.getButton();
		
		this.getChildren().add(playButton);
		this.getChildren().add(quitButton);
	}
}
