package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.PauseTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class MainController implements Initializable {
	@FXML
	private AnchorPane pane;
	@FXML
	private ToggleButton toggle;
	@FXML
	private Button button;
	@FXML
	private Label label;
	@FXML
	private ToggleButton toggleButton;
	@FXML
	private ProgressIndicator gauge;
	@FXML
	private Label speedLabel;
	@FXML
	private ProgressIndicator gauge1;
	@FXML
	private Label engineSpeed;

	static final int MAX_SPEED = 299;

	public boolean isOn = false;
	public boolean isClutchOn = false;
	public boolean canSpeedUp = true;
	EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			label.setText("Accepted");
			event.consume();
			pane.requestFocus();
		}
	};
	static int a = 0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// speedLabel.textProperty().bind(new SimpleIntegerProperty(a).asString());
		toggle.setDisable(true);
		toggle.setStyle("-fx-base: green;");
		toggle.setText("zapal");
		// pane.setOnKeyTyped((KeyEvent e) -> {
		// if(e.getCode()==KeyCode.DOWN) {
		// System.out.println("asdas");
		// }
		// });
		
		
		
		pane.addEventFilter(KeyEvent.ANY, keyEvent -> {
			if (!label.getText().equals("N") && !label.getText().equals("n")) {
				canSpeedUp = true;
			} else {
				canSpeedUp = false;
			}
		});

		pane.setOnKeyTyped(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (isOn && isClutchOn) {
					switch (event.getCharacter()) {
					case "1":
						label.setText("1");
						break;
					case "2":
						label.setText("2");
						break;
					case "3":
						label.setText("3");
						break;
					case "4":
						label.setText("4");
						break;
					case "5":
						label.setText("5");
						break;
					case "N":
						label.setText("N");
						break;
					case "6":
						label.setText("6");
						break;
					case "n":
						label.setText("N");
						break;
					default:
						break;
					}
				}
			}
		});

		pane.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.SPACE) {
					System.out.println("space pressed");
					// button.setDisable(false);
					toggle.setDisable(false);
					isClutchOn = true;
				} else if (isOn && event.getCode() == KeyCode.UP && a < MAX_SPEED && canSpeedUp) {
					speedLabel.setText(String.valueOf(a));
					// engineSpeed.setText(String.valueOf((a*8000)/300));
					engine(label.getText(), speedLabel, engineSpeed);
					if (speeding(a)) {
						a++;
					}
				} else if (isOn && event.getCode() == KeyCode.DOWN && a > 0) {
					speedLabel.setText(String.valueOf(a));
					// engineSpeed.setText(String.valueOf((a*8000)/300));
					engine(label.getText(), speedLabel, engineSpeed);
					--a;
				}

			}
		});

		pane.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.SPACE) {
					System.out.println("space ");
					// button.setDisable(true);
					toggle.setDisable(true);
					isClutchOn = false;
				}
			}
		});

		// /button.setOnAction(buttonHandler);
		toggle.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (toggle.isSelected()) {
					toggle.setStyle("-fx-base: red;");
					toggle.setText("zgas");
					engineSpeed.setText("1200");
					isOn = true;

				} else {
					toggle.setStyle("-fx-base: green;");
					toggle.setText("Zapal");
					
					isOn = false;
				}
				pane.requestFocus();
			}
		});

		speedLabel.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				gauge.setProgress(Double.parseDouble(arg2) / 300);
			}

		});

		engineSpeed.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				gauge1.setProgress(Double.parseDouble(arg2) / 8000);
			}
		});

		// Platform.runLater(new Runnable() {
		//
		// @Override
		// public void run() {
		// System.out.println("asdsad");
		// }
		// });
		update();
	}

	public void engine(String bieg, Label label, Label label1) {
		int maxGearSpeed=0;
		if(!bieg.equals("N")) {
		maxGearSpeed = Integer.parseInt(bieg) * 50;
		double speed = Double.parseDouble(label.getText());
		double engineSpeed = (speed / (maxGearSpeed)) * 8000;
		int displayEngineSpeed = (int) engineSpeed;
		if (displayEngineSpeed < 8000) {
			label1.setText(String.valueOf(displayEngineSpeed));
			System.out.println(engineSpeed);
		} else if (displayEngineSpeed > 8000) {
			label1.setText(String.valueOf(7999));
		}
		}else {
			label1.setText("2200");
		}
		

	}

	public boolean speeding(int a) {
		if (a < Integer.parseInt(label.getText()) * 50) {
			return true;
		} else {
			return false;
		}
	}
	
	 private void update() {

	        PauseTransition pause = new PauseTransition(Duration.millis(200)/*seconds(1)*/);
	        pause.setOnFinished(event ->{
	            if(a>0) {
	            	speedLabel.setText(String.valueOf(a));
	            	engine(label.getText(), speedLabel, engineSpeed);
	            	a--;
	            }
	            pause.play();
	        });
	        pause.play();
	    }

}
