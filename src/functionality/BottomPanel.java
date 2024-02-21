
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BottomPanel extends HBox {
	private Button hsButton;
	private Button exitButton;
	private Button playButton;
	
	public BottomPanel() {
		
		// göra nya knappar med annan  grafik?
		MyButton hsButton = new hsButton();
		MyButton exitButton = new exitButton();
		MyButton playButton = new playButton();

//		hsButton.setOnAction(event -> {
//		});
//		
//		exitButton.setOnAction(event -> {
//		});
//		
//		playButton.setOnAction(event -> {
//		});

		this.getChildren().addAll(hsButton, exitButton, playButton);
	}
}
