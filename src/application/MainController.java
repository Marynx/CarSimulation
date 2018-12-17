package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
	
	static final int MAX_SPEED=300;
	
	public boolean isOn=false;
	public boolean isClutchOn=false;
	EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			label.setText("Accepted");
			event.consume();
			pane.requestFocus();
		}
	};
	int a=0;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		toggle.setDisable(true);
		toggle.setStyle("-fx-base: green;");
		toggle.setText("zapal");
//		pane.setOnKeyTyped((KeyEvent e) -> {
//		     if(e.getCode()==KeyCode.DOWN) {
//		    	 System.out.println("asdas");
//		     }
//		});
		pane.setOnKeyTyped(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
              if(isOn&&isClutchOn) {
                switch(event.getCharacter()) {
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
					//button.setDisable(false);
					toggle.setDisable(false);
					isClutchOn=true;
				}else if(isOn&&event.getCode()==KeyCode.UP&&a<MAX_SPEED) {
					speedLabel.setText(String.valueOf(a));
					engineSpeed.setText(String.valueOf((a*8000)/300));
					a++;
				}
				
			}
		});

		pane.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.SPACE) {
					System.out.println("space ");
					//button.setDisable(true);
					toggle.setDisable(true);
					isClutchOn=false;
				}
			}
		});
		
	
		
//		/button.setOnAction(buttonHandler);
		toggle.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (toggle.isSelected()) {
					toggle.setStyle("-fx-base: red;");
					toggle.setText("zgas");
					isOn=true;
					
				} else {
					toggle.setStyle("-fx-base: green;");
					toggle.setText("Zapal");
					isOn=false;
				}
				pane.requestFocus();
			}
		});
		
		speedLabel.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				gauge.setProgress(Double.parseDouble(arg2)/300);	
			}
		
		
		});
		
		engineSpeed.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				
				gauge1.setProgress(Double.parseDouble(arg2)/8000);
			}
		});
	}
	
}
