
import java.util.Random;

public class GameLevel {
	
	private Player player;
	private Boots boots;
	private Life life;

	public GameLevel(Model model, boolean difficultLevel) { //diffLevel=true, svår bana, level 2
		int nrOfPlatforms;
		int nrOfLavaPlatforms;
		int monsterSpeed;
		
		if (!difficultLevel) {
			nrOfPlatforms = 15;
			nrOfLavaPlatforms = 1;
			monsterSpeed = 3;
		} else {
			nrOfPlatforms = 10;
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
		y = 0;
		for (int i = 0; i<nrOfLavaPlatforms; ++i) {
			Random randX = new Random();
			double x = randX.nextInt(700);

			GameObject lavaPlatform = new Platform("lava_platform.png", 70, 17, x, y, true);
			y += MyCanvas.height / nrOfLavaPlatforms;
			
			model.addObjects(lavaPlatform);
		}
		
	// Create monsters
		GameObject monster = new Monster("monster.png", 70, 80, 0, -500, monsterSpeed);
		model.addObjects(monster);
		
	// Create player
		player = new Player("elephant.png", 60, 80, 0, 700, -8);
		model.addObjects(player);
		
	// Create powerups
		boots = new Boots("boots.png", 30, 50, 40, -3000);
		life = new Life("lifes.png", 60, 50, 150, -100);
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
