package application;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class Usercontroller  {

    @FXML
    private Button admin;

    @FXML
    private Button student;

    @FXML
    void openForm(ActionEvent event) throws IOException {
    	
        	AnchorPane studentPage = (AnchorPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
        	Scene studentScene = new Scene(studentPage);
        	
        	studentScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        	
        	window.setScene(studentScene);
        	window.show();
        }

    @FXML
    void openLogin(ActionEvent event) throws IOException {
    	AnchorPane loginPage = (AnchorPane)FXMLLoader.load(getClass().getResource("adminLogin.fxml"));
    	Scene loginScene = new Scene(loginPage);
    	
    	loginScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
    	
    	window.setScene(loginScene);
    	window.show();   		
    }

}