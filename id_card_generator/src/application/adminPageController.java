package application;

import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class adminPageController{

    @FXML
    private Button viewdetails;

    @FXML
    private Button setDetails;

    @FXML
    private AnchorPane adminAnchor;
    
    private AnchorPane studentDetails;

    @FXML
    void Details(ActionEvent event) throws IOException {
    	createPage("Sample.fxml");
    }

    @FXML
    void viewDetails(ActionEvent event) throws Exception {
		 createPage("editdetails.fxml");
    }
    
    private void SetNode(Node node) {
		adminAnchor.getChildren().clear();
		adminAnchor.getChildren().add((Node) node);
		
		FadeTransition ft = new FadeTransition(Duration.millis(1500));
		ft.setNode(node);
		ft.setFromValue(0.1);
		ft.setToValue(1);
		ft.setCycleCount(1);
		ft.setAutoReverse(false);
		ft.play();
	}
    
	private void createPage(String file) throws IOException {
		studentDetails = FXMLLoader.load(getClass().getResource(file));
		SetNode(studentDetails);
		
	}
	
}