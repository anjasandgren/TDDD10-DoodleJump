package graphic;

import java.util.Random;
import game_objects.GameObject;
import game_objects.Life;
import game_objects.Monster;
import game_objects.Platform;
import game_objects.Player;
import logic.Boots;
import logic.Model;
import logic.MyCanvas;

public class GameLevel {
	
	private Player player;
	private Boots boots;
	private Life life;

	public GameLevel(Model model, boolean difficultLevel) { //diffLevel=true, means hard level, level 2
		int nrOfPlatforms;
		int nrOfLavaPlatforms;
		int monsterSpeed;
		
		if (!difficultLevel) {
			nrOfPlatforms = 20;
			nrOfLavaPlatforms = 1;
			monsterSpeed = 3;
		} else {
			nrOfPlatforms = 13;
			nrOfLavaPlatforms = 2;
			monsterSpeed = 5;
		}
		
	// Create platforms
		double y = 0;
		for (int i = 0; i<nrOfPlatforms; ++i) {
			Random randX = new Random();
			double x = randX.nextInt(700);

			GameObject platform = new Platform("platform.png", 60, 40, x, y);
			y += MyCanvas.height / nrOfPlatforms;
			
			model.addObjects(platform);
		}
		
	// Create lava platforms 
		y = (-1) * MyCanvas.height;
		for (int i = 0; i<nrOfLavaPlatforms; ++i) {
			Random randX = new Random();
			double x = randX.nextInt(700);

			GameObject lavaPlatform = new Platform("lava_platform.png", 70, 17, x, y, true);
			y += MyCanvas.height / nrOfLavaPlatforms;
			
			model.addObjects(lavaPlatform);
		}
		
	// Create monsters
		GameObject monster = new Monster("monster.png", 70, 80, 0, -600, monsterSpeed);
		model.addObjects(monster);
		
	// Create power ups
		Random randX1 = new Random();
		Random randX2 = new Random();
		double x1 = randX1.nextInt(700);
		double x2 = randX2.nextInt(700);
		
		boots = new Boots("boots.png", 30, 50, x1, -500);
		life = new Life("life.png", 60, 40, x2, -300);
		model.addObjects(boots);
		model.addObjects(life);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Boots getBoots() {
		return boots;
	}
	
	public Life getLife() {
		return life;
	}
}
