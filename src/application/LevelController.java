package application;

import java.awt.Dimension;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.image.ImageView;

import swing.Game;

public class LevelController implements Initializable{
	
	@FXML
	private Button level;
	
	@FXML
	private Button level1;
	
	@FXML
	private ImageView image1;
	
	@FXML
	private ImageView image2;
	
	private Game game;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
		
		
	
	
	public void handleLevel(ActionEvent event) {
		createGame();
		game.URL = "/level2.png";
		game.start();
		
	}
	
	public void handleLevel2(ActionEvent event) {
		createGame();
		game.URL = "/level.png";
		game.start();
	}
	
	public void createGame() {

		game = new Game();
		game.setPreferredSize(new Dimension(800, 575));
		game.setMaximumSize(new Dimension(800, 600));
		game.URL = "/level2.png";

		JFrame frame = new JFrame("Dead shot");
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	
	
	}
	
	
	
	
}

