package logic;

public class Player {
	private int lifes;
	private boolean immortal; 
	
	public Player() {
		lifes = 0;
		immortal = false;
	}
	
	public int getLifes() {
		return lifes;
	}
	
	public boolean isImmortal() {
		return immortal;
	}
}
