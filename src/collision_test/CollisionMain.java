package collision_test;

import javafx.geometry.Rectangle2D;

public class CollisionMain {
	
	public static void main(String[] args) {
		// Skapar tvÃ¥ GameObjects 
		GameObject ship = new GameObject(10, 15, 5, 5);
		GameObject rock = new GameObject(20, 20, 10, 10);
		
		//Hämtar dessas rektanglar utifrån deras position just nu
		Rectangle2D shipRec = ship.getRectangle();
		Rectangle2D rockRec = rock.getRectangle();
		
		//Kollar kollision
		if (shipRec.intersects(rockRec)) {
			System.out.println("KROCK!");
		} else {
			System.out.println("Inte krock!");
		}
		
		//Uppdaterar skeppets position
		ship.setPosition(20, 20);
		
		//HÃ¤mtar nya rektangeln fÃ¶r ship
		shipRec = ship.getRectangle();
		
		//Kollar kollision utifrÃ¥n skeppets nya position
		if (shipRec.intersects(rockRec)) {
			System.out.println("KROCK!");
		} else {
			System.out.println("Inte krock!");
		}
		
	}
}