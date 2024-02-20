package projekt;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BottomPanel extends HBox {
	private Button hsButton;
	private Button exitButton;
	private Button playButton;
	
	public BottomPanel {
		// göra nya knappar med annan  grafik?
		Button hsButton = new hsButton();
		exitButton = new Button("Undo");
		playButton = new Button("Open");

		clearButton.setOnAction(event -> {
			myCanvas.clear();
		});
		
		undoButton.setOnAction(event -> {
			myCanvas.undo();
		});
		
		openButton.setOnAction(event -> {
			try {
				open();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		saveButton.setOnAction(event -> {
			try {
				save();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		this.getChildren().addAll(clearButton, undoButton, openButton, saveButton);
	}
}
