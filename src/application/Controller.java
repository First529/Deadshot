package application;

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
import javafx.stage.Stage;

public class Controller implements Initializable {

	@FXML
	private Button play;
	@FXML
	private Button help;
	@FXML
	private Button quit;

	private Stage stage;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	public void handlePlay() {
		stage = (Stage) play.getScene().getWindow();
		stage.close();
		try {
			Parent root = (Parent) FXMLLoader.load(getClass().getResource("LevelUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch (Exception ex) {
			System.out.println("Couldn't open game.");
		}
	}

	@FXML
	public void handleHelp(ActionEvent event) {
		stage = (Stage) help.getScene().getWindow();
		stage.close();
		try {
			Parent root = (Parent) FXMLLoader.load(getClass().getResource("HelpUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			System.out.println("Couldn't open help menu");
		}
	}

	@FXML
	public void handlerQuit() {
		System.exit(0);
	}
}
