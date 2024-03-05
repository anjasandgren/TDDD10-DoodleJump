package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import game_objects.GameObject;

/**
 * This class handles functions for handling high score and is responsible for updating them.
 * Main function is to read the top 3 scores from and to file. 
 * @author cajbj386
 */

public class HighScore {
	
	private ArrayList<Integer> scores;
	private static final String hsFile = "highScore.txt";

	public HighScore() throws ClassNotFoundException {
		scores = new ArrayList<Integer>();
		DataInputStream in = null;
		try {
			in = new DataInputStream(new FileInputStream(hsFile));
			scores.add(in.readInt());
			scores.add(in.readInt());
			scores.add(in.readInt());
			in.close();
		} catch (Exception e) {
			scores.add(0);
			scores.add(0);
			scores.add(0);
		}
	}
	
	public void updateScores(GameObject player) {
		if (!scores.contains(player.getScore())) {
			scores.add(player.getScore());
			Collections.sort(scores);
			Collections.reverse(scores);
			DataOutputStream out = null;
			try {
				out = new DataOutputStream(new FileOutputStream(hsFile));
				for (int i = 0; i < 3; i++) {
					out.writeInt(scores.get(i));
				}
				out.close();
			} catch (IOException e) {}
		}
	}
	
	public ArrayList<Integer> getScores() {
		return scores;
	}
}