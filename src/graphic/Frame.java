package graphic;
//import functionality.BottomPanel;

import javafx.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.awt.Color;

	public class Frame extends JFrame {
		private  JLabel label;
		private int width = 1200;
		private int height = 800;
		final JPanel panel = new JPanel();


   public Frame() {
   
//       label = new Label("Welcome to Doodle Jump!");

       this.setDefaultCloseOperation(EXIT_ON_CLOSE);
       this.setSize(width, height);
       this.setLayout(null);
       this.add(label);
       this.setVisible(true);
       this.setLocationRelativeTo(null);
       
  //     new BottomPanel(label);
       
       JButton exitButton = new JButton("EXIT");
       exitButton.setSize(10, 10);
       exitButton.setBackground(Color.RED);
       
       JButton hsButton = new JButton("HIGHSCORE");
       hsButton.setSize(20, 10);
       hsButton.setBackground(Color.RED);
       
       JButton playButton = new JButton("PLAY");
       playButton.setSize(30, 10);
       playButton.setBackground(Color.RED);
       
       JPanel panel = new JPanel();
       panel.setBounds(0, 200, 1200, 1200);
       panel.setBackground(Color.GREEN);
   
       
       panel.add(exitButton);
       panel.add(hsButton);
       panel.add(playButton);

     //  panel.setSize(200, 400);
       this.add(panel);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setVisible(true);
       this.setVisible(true);
       this.setLocationRelativeTo(null);
	
	}
}
