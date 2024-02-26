import javafx.scene.canvas.Canvas;

public class MyCanvas extends Canvas{

	//private Model model; 
	
	public MyCanvas(Model model) {
		setWidth(800);
		setHeight(400);
		
		setOnMouseClicked(event -> {
			System.out.println("Mouse Clicked");
			model.mouseClicked(event);
		});
	}
	
	public void repaint(Model model) {
		model.repaint(getGraphicsContext2D());
	}
	
}
