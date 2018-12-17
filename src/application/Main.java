package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws IOException {

		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/application/MainView.fxml"));
		//parent.setFocusTraversable(true);
		Scene scene = new Scene(parent);
		scene.getRoot().requestFocus();
		stage.setScene(scene);
		stage.setTitle("Login Page");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
