package graphic;

import game_objects.Boots;
import game_objects.GameObject;
import game_objects.Life;
import game_objects.Monster;
import game_objects.Platform;
import java.util.Random;
import logic.Model;
import logic.MyCanvas;

/**
 * This class is responsible for creating a level in the game.
 * There are two levels to choose from with different difficulty.
 * @author anjsa296
 */

public class GameLevel {
	
	private Boots boots;
	private Life life;

	public GameLevel(Model model, boolean difficultLevel) {
		int nrOfPlatforms = 20;
		int nrOfLavaPlatforms = 2;
		int monsterSpeed = 2;
		int nrOfMonsters = 1;
		
		if (difficultLevel) {
			nrOfPlatforms = 15;
			nrOfLavaPlatforms = 8;
			monsterSpeed = 6;
			nrOfMonsters = 3;
		}
		
	// Create platforms
		GameObject platform = new Platform("platform.png", 800, 40, 0, MyCanvas.height - 150, 0, 1, false, true);
		model.addObjects(platform);

		double y = -300;
		for (int i = 0; i<nrOfPlatforms; ++i) {
			Random randX = new Random();
			double x = randX.nextInt(700);
			platform = new Platform("platform.png", 60, 40, x, y, 0, 2);
			y += MyCanvas.height / nrOfPlatforms;
			model.addObjects(platform);
		}
		
	// Create lava platforms 
		y = (-1) * MyCanvas.height;
		for (int i = 0; i<nrOfLavaPlatforms; ++i) {
			Random randX = new Random();
			double x = randX.nextInt(700);

			GameObject lavaPlatform = new Platform("lava_platform.png", 70, 17, x, y, 0, 2, true);
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
