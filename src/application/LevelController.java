package application;

import java.awt.Dimension;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import swing.Game;
import swing.GameCharacter;

public class LevelController implements Initializable {

	@FXML
	private Button level;

	@FXML
	private Button level1;

	@FXML
	private Button backButton;

	@FXML
	private ImageView imageView;

	@FXML
	private ImageView imageView2;

	@FXML
	private ComboBox<GameCharacter> box1;

	@FXML
	private ComboBox<GameCharacter> box2;

	@FXML
	private TextField field1;

	@FXML
	private TextField field2;

	public static String player1Character = null;

	public static String player2Character = null;

	private Game game;

	private Stage stage;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		if (box1 != null && box2 != null) {
			box1.getItems().addAll(GameCharacter.values());
			box1.getSelectionModel().select(0);
			box2.getItems().addAll(GameCharacter.values());
			box2.getSelectionModel().select(0);
		}
	}

	public void handleSelect(ActionEvent event) {
		GameCharacter character = box1.getValue();

		if (GameCharacter.Assassin.equals(character)) {
			field1.setText("assassin");
			box2.getItems().remove(GameCharacter.Assassin);
			player1Character = field1.getText();
			if (!box2.getItems().contains(GameCharacter.Hunter))
				box2.getItems().add(GameCharacter.Hunter);
			if (!box2.getItems().contains(GameCharacter.Wizard))
				box2.getItems().add(GameCharacter.Wizard);
		}

		if (GameCharacter.Wizard.equals(character)) {
			field1.setText("wizard");
			box2.getItems().remove(GameCharacter.Wizard);
			player1Character = field1.getText();
			if (!box2.getItems().contains(GameCharacter.Hunter))
				box2.getItems().add(GameCharacter.Hunter);
			if (!box2.getItems().contains(GameCharacter.Assassin))
				box2.getItems().add(GameCharacter.Assassin);
		}

		if (GameCharacter.Hunter.equals(character)) {
			field1.setText("hunter");
			box2.getItems().remove(GameCharacter.Hunter);
			player1Character = field1.getText();
			if (!box2.getItems().contains(GameCharacter.Assassin))
				box2.getItems().add(GameCharacter.Assassin);
			if (!box2.getItems().contains(GameCharacter.Wizard))
				box2.getItems().add(GameCharacter.Wizard);
		}
	}

	public void handleSelect2(ActionEvent event) {
		GameCharacter character = box2.getValue();

		if (GameCharacter.Assassin.equals(character)) {
			field2.setText("assassin");
			box1.getItems().remove(GameCharacter.Assassin);
			player2Character = field2.getText();
			if (!box1.getItems().contains(GameCharacter.Hunter))
				box1.getItems().add(GameCharacter.Hunter);
			if (!box1.getItems().contains(GameCharacter.Wizard))
				box1.getItems().add(GameCharacter.Wizard);
		}

		if (GameCharacter.Wizard.equals(character)) {
			field2.setText("wizard");
			box1.getItems().remove(GameCharacter.Wizard);
			player2Character = field2.getText();
			if (!box1.getItems().contains(GameCharacter.Hunter))
				box1.getItems().add(GameCharacter.Hunter);
			if (!box1.getItems().contains(GameCharacter.Assassin))
				box1.getItems().add(GameCharacter.Assassin);
		}

		if (GameCharacter.Hunter.equals(character)) {
			field2.setText("hunter");
			box1.getItems().remove(GameCharacter.Hunter);
			player2Character = field2.getText();
			if (!box1.getItems().contains(GameCharacter.Assassin))
				box1.getItems().add(GameCharacter.Assassin);
			if (!box1.getItems().contains(GameCharacter.Wizard))
				box1.getItems().add(GameCharacter.Wizard);
		}
	}

	public void handleLevel(ActionEvent event) {
		createGame();
//		game.URL = "/images/level.png";
		game.start();

	}

	public void handleLevel2(ActionEvent event) {
		createGame();
//		game.URL = "/images/level2.png";
		game.start();
	}

	public void handleBack(ActionEvent event) {
		stage = (Stage) backButton.getScene().getWindow();
		stage.close();
		try {
			Parent root = (Parent) FXMLLoader.load(getClass().getResource("MenuUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createGame() {

		game = new Game();
		game.setPreferredSize(new Dimension(800, 575));
		game.setMaximumSize(new Dimension(800, 600));

		JFrame frame = new JFrame("Dead shot");
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

}
