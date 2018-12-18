package application;

import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
//	public void moveCircle(Label label, Scene scene) {
//	    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
//	        if(MainController.a>0) {
//	        	MainController.a--;
//	        }
//	    }));
//	    timeline.setCycleCount(Animation.INDEFINITE);
//	    timeline.play();
//	}
	public static void main(String[] args) {
		launch(args);
	}
}
