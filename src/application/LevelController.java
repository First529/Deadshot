package application;

import java.awt.Dimension;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import swing.Game;

public class LevelController implements Initializable{
	
	@FXML
	private Button level;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void handleLevel(ActionEvent event) {
		Game game = new Game();
		game.setPreferredSize(new Dimension(800, 575));
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
