package graphic;

import java.io.FileInputStream;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Player {

	public Player(Stage primaryStage) throws Exception {
			Label text = new Label("My Pokemons HP");
			Image pokemon = new Image(new FileInputStream("/home/emmen85/Java/TDDE10/Pokemon.png"));
			ImageView image = new ImageView(pokemon);
			HealthComponent hc = new HealthComponent(80.0, pokemon.getHeight());
			
			layout.getChildren().add(text);
			layout.getChildren().add(image);
			layout.getChildren().add(hc);
			
		}

	}
}
