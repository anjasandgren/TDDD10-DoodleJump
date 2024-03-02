package graphic;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SidePanel extends VBox {

	private int score = 0;
	private int lifes = 0;
	
	public SidePanel() {
		Label scorelabel = new Label("Score: " + score);
		Label lifeslabel = new Label("Lifes: " + lifes);

		this.getChildren().add(scorelabel);
		this.getChildren().add(lifeslabel);
	}
	
	public void update(int score, int lifes, boolean hasBoots) {
		if (score != this.score || lifes != this.lifes) {
			this.score = score;
			this.lifes = lifes;
			
			Label scorelabel = new Label("Score: " + score);
			Label lifeslabel = new Label("Lifes: " + lifes);
			
			this.getChildren().clear();
			this.getChildren().add(scorelabel);
			this.getChildren().add(lifeslabel);
		}
	}
}
