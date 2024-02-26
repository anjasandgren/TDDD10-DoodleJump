package buffer_pics_test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import javafx.scene.image.Image;

public class SpriteManager {

	private static HashMap<String, Image> sprites = new HashMap<>();
	
	public static Image getImage(String name) {
		if (!sprites.containsKey(name)) {
			try {
				sprites.put(name, new Image(new FileInputStream(name)));
			} catch (FileNotFoundException e) {
				System.out.println(name + " does not excist in chosen folder.");
			}
		}
		return sprites.get(name);
	}

	public static void add(String name) {
		try {
			sprites.put(name, new Image(new FileInputStream(name)));
		} catch (FileNotFoundException e) {
			System.out.println(name + " does not excist in chosen folder.");
		}
	}

}