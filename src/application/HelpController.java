package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HelpController {

	@FXML
	private Button backButton;

	private Stage stage;

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
			System.out.println("Couldn't open main menu");
		}
	}

}
