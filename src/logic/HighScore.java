package logic;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner; 
import java.util.*; 

import game_objects.GameObject;
import game_objects.Player;

public class HighScore {
	String highScore1 = "0";
	String highScore2 = "0";
	String highScore3 = "0";
	ArrayList<String> scores = new ArrayList<String>();

	public HighScore() throws ClassNotFoundException {
		ObjectInputStream in = null;
		firstReading();
		try {
			in = new ObjectInputStream(new FileInputStream("/home/cajbj386/TDDE10/projekt/highScore1.txt"));
			highScore1 = (String) in.readObject();
			in = new ObjectInputStream(new FileInputStream("/home/cajbj386/TDDE10/projekt/highScore2.txt"));
			highScore2 = (String) in.readObject();
			in = new ObjectInputStream(new FileInputStream("/home/cajbj386/TDDE10/projekt/highScore3.txt"));
			highScore3 = (String) in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getScores() {
		return scores;
	}
	
	public void firstReading() {
		ObjectOutputStream out = null;
	try {
		out = new ObjectOutputStream(new FileOutputStream("/home/cajbj386/TDDE10/projekt/highScore1.txt"));
		out.writeObject(highScore1);
		out = new ObjectOutputStream(new FileOutputStream("/home/cajbj386/TDDE10/projekt/highScore2.txt"));
		out.writeObject(highScore2);
		out = new ObjectOutputStream(new FileOutputStream("/home/cajbj386/TDDE10/projekt/highScore3.txt"));
		out.writeObject(highScore3);
	} catch (IOException e) {
		e.printStackTrace();
	}
} 

	public void updateScores(GameObject player) {
		if (player.getScore() > Integer.parseInt(highScore3)) {
			if (player.getScore() > Integer.parseInt(highScore1)) {
				loadScore(player, highScore1);
				loadScore(highScore1, highScore2);
				loadScore(highScore2, highScore3);
		} else if (player.getScore() > Integer.parseInt(highScore2)) {
			loadScore(player, highScore2);	
			loadScore(highScore2, highScore3);
		} else {
			loadScore(player, highScore3);
		}
	}
}
	
	public void loadScore(GameObject player, String hs ) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(hs));
			out.writeObject(String.valueOf(player.getScore()));
			out.close();
			hs = String.valueOf(player.getScore());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Ladda highScores
	public void loadScore(String newhs, String oldhs) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(oldhs));
			out.writeObject(newhs);
			out.close();
			oldhs = newhs;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

