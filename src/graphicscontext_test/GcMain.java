package graphicscontext_test;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GcMain extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox layout = new HBox();
		Canvas canvas = new Canvas(800, 600);
		layout.getChildren().add(canvas);
		Scene scene = new Scene(layout);
		primaryStage.setScene(scene);

		GcTester gcTester = new GcTester();
		
		new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				gcTester.update();
				gcTester.draw(canvas.getGraphicsContext2D(), primaryStage.getWidth(), primaryStage.getHeight());
			}
		}.start();
		
		primaryStage.show();
	}

}