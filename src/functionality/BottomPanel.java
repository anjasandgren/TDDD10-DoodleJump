package functionality;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BottomPanel extends HBox {
	private Button hsButton;
	private Button exitButton;
	private Button playButton;
	
	public BottomPanel() {
		
		// göra nya knappar med annan  grafik?
		Button hsButton = new hsButton();
		exitButton = new exitButton();
		playButton = new playButton();

		hsButton.setOnAction(event -> {
			myCanvas.clear();
		});
		
		exitButton.setOnAction(event -> {
			myCanvas.undo();
		});
		
		playButton.setOnAction(event -> {
			try {
				open();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		this.getChildren().addAll(clearButton, undoButton, openButton, saveButton);
	}
}
