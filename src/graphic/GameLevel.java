package graphic;

import game_objects.Boots;
import game_objects.GameObject;
import game_objects.Life;
import game_objects.Monster;
import game_objects.Platform;
import java.util.Random;
import logic.Model;
import logic.MyCanvas;

public class GameLevel {
	
	private Boots boots;
	private Life life;

	public GameLevel(Model model, boolean difficultLevel) { //diffLevel=true, means hard level, level 2
		int nrOfPlatforms = 20;
		int nrOfLavaPlatforms = 2;
		int monsterSpeed = 2;
		int nrOfMonsters = 1;
		
		if (difficultLevel) {
			nrOfPlatforms = 20;
			nrOfLavaPlatforms = 10;
			monsterSpeed = 5;
			nrOfMonsters = 3;
		}
		
		
	// Create platforms
		double y = 0;
		for (int i = 0; i<nrOfPlatforms-13; ++i) {
			Random randX = new Random();
			double x = randX.nextInt(700);
			GameObject platform = new Platform("platform.png", 60, 40, x, y);
			y += MyCanvas.height / nrOfPlatforms;
			model.addObjects(platform);
		}
		for (int i = 0; i<13; ++i) {
			GameObject platform = new Platform("platform.png", 60, 40, MyCanvas.width/2 - 30, y);
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
		y = -600;
		for (int i = 0; i<nrOfMonsters; ++i) {
			GameObject monster = new Monster("monster.png", 70, 80, 0, -600, monsterSpeed);
			y += MyCanvas.height / nrOfMonsters;
			model.addObjects(monster);
		}
		
		
	// Create power ups
		Random randX1 = new Random();
		Random randX2 = new Random();
		double x1 = randX1.nextInt(700);
		double x2 = randX2.nextInt(700);
		
		boots = new Boots("boots.png", 30, 50, x1, -300);
		life = new Life("life.png", 60, 40, x2, -2000);
		model.addObjects(boots);
		model.addObjects(life);
	}
	
	public Boots getBoots() {
		return boots;
	}
	
	public Life getLife() {
		return life;
	}
}
