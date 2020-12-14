package application;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SampleController {

    @FXML
    private Label status;
    
    @FXML
    private TextField name;

    @FXML
    private TextField rollNo;

    @FXML
    private TextField dept;

    @FXML
    private TextField email;

    @FXML
    private TextField bloodGrp;

    @FXML
    private TextField mobileNo;

    @FXML
    private DatePicker dob;

    @FXML
    private Button submit;

    @FXML
    void sub(ActionEvent event)throws Exception {
    	String date = dob.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    	Student s = new Student(this.name.getText(), this.rollNo.getText(), this.dept.getText(), this.mobileNo.getText(), this.bloodGrp.getText(), this.email.getText(), date);
    	boolean check1 = s.validateDetails();
    	boolean check2 = s.checkInDatabase();
    	if (check1 == false) {
    		status.setText("Invalid Details!");
    	}
    	else if(check2 == false) {
    		System.out.println("checking in db");
    		status.setText("Already exists in Database!");
    	}
    	else {
    		s.insertToDatabase();
    		status.setText("Successfully updated!!");
    	}
    }

}
