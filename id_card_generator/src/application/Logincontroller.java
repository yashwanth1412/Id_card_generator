package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Logincontroller {

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Button login;
    
    @FXML
    private Label status;
    
    private AnchorPane addDetails;

    
    @FXML
    public void check(ActionEvent event) throws IOException, Exception {
    	if(username.getText()!="" && password.getText()!="" && username.getText().equalsIgnoreCase(password.getText())) {
			addDetails = (AnchorPane)FXMLLoader.load(getClass().getResource("adminPage.fxml"));
			Scene loginScene = new Scene(addDetails);
    	
			loginScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
    	
			window.setScene(loginScene);
			window.show();
    	
    }
    	else {
    		status.setText("Invalid Details!!!");
    	}
    }
}

