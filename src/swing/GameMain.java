package swing;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GameMain {
	
	public static void main(String[] args) {
		Game game = new Game();
		game.setPreferredSize(new Dimension(800, 600));
		game.setMaximumSize(new Dimension(800, 600));
		game.setMaximumSize(new Dimension(800, 600));
		
		JFrame frame = new JFrame("Dead shot");
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
	
	

}
