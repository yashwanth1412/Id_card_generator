package application;

import java.net.URL;
import java.util.ResourceBundle;
import database.DatabaseUsers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
public class editDetailsController implements Initializable{
    @FXML
    private ComboBox<String> searchBy;
    
    @FXML
    private TextField detail;
    
    @FXML
    private TableView<Student> table;
    
    @FXML
    private TableColumn<Student, String> col_1;

    @FXML
    private TableColumn<Student, String> col_2 ;

    @FXML
    private TableColumn<Student, String> col_3;

    @FXML
    private TableColumn<Student, String> col_4 ;

    @FXML
    private TableColumn<Student, String> col_5;

    @FXML
    private TableColumn<Student, String> col_6;

    @FXML
    private TableColumn<Student, String> col_7;
    
    @FXML
    private Label status;
    
    @FXML
    private ComboBox<String> updateList;
    
    @FXML
    private TextField updatedValue;

    @FXML
    void search(ActionEvent event) throws Exception{
    	searchResults();
    }
    
    private void searchResults() throws Exception{
    	DatabaseUsers data = new DatabaseUsers();
    	String detl = this.detail.getText();
    	String srhUsg = this.searchBy.getValue();
    	ObservableList<Student>	oDetails =  data.selectDetails(srhUsg, detl);
    	table.setItems(oDetails);
    	if(oDetails.isEmpty()) {
    		status.setText("No Results Found!!");
    	}
    	else {
    		status.setText("Sucessfully Found!!");
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	    ObservableList<String> searchUsing = (ObservableList<String>) FXCollections.observableArrayList("name", "department", "rollNo", "mobileNo", "email");
	    ObservableList<String> list = (ObservableList<String>) FXCollections.observableArrayList("name", "department", "rollNo", "mobileNo", "email", "dob");
		searchBy.setValue(searchUsing.iterator().next());
		searchBy.setItems(searchUsing);
		updateList.setValue(list.iterator().next());
		updateList.setItems(list);
    	col_1.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
    	col_2.setCellValueFactory(new PropertyValueFactory<Student, String>("rollNo"));
    	col_3.setCellValueFactory(new PropertyValueFactory<Student, String>("department"));
    	col_4.setCellValueFactory(new PropertyValueFactory<Student, String>("mobileNo"));
    	col_6.setCellValueFactory(new PropertyValueFactory<Student, String>("emailId"));
    	col_7.setCellValueFactory(new PropertyValueFactory<Student, String>("dob"));
	}
	
    @FXML
    void deleteUsers(ActionEvent event) throws Exception {
    	Student s = table.getSelectionModel().getSelectedItem();
    	if(s==null) {
    		status.setText("Select a value");
    		return;
    	}
    	DatabaseUsers data = new DatabaseUsers();
    	data.deleteDetails("email", s.getEmailId());
    	searchResults();
    	status.setText("Successfully Deleted!!");
    }
    
    @FXML
    void updateUser(ActionEvent event) throws Exception {
    	Student s = table.getSelectionModel().getSelectedItem();
    	DatabaseUsers data = new DatabaseUsers();
    	if(s==null) {
    		status.setText("Select a value");
    		return;
    	}
    	if(updatedValue.getText()=="") {
    		status.setText("Enter a value to update !");
    		return ;
    	}
    	switch(updateList.getValue()) {
    	case "name":
    		s.setName(updatedValue.getText());
    		break;
    	case "department":
    		s.setDepartment(updatedValue.getText());
    		break;
    	case "email":
    		s.setEmailId(updatedValue.getText());
    		break;
    	case "mobileNo":
    		s.setMobileNo(updatedValue.getText());
    		break;
    	}
    	if (! s.validateDetails()) {
    		status.setText("Invalid Details");
    		return;
    	}
	if(updateList.getValue()!="email") {
    		data.updateDetails(updateList.getValue(), updatedValue.getText(), "email",  s.getEmailId());
    	}
    	else {
    		data.updateDetails(updateList.getValue(), updatedValue.getText(), "rollNo",  s.getRollNo());
    	}
    	data.updateDetails(updateList.getValue(), updatedValue.getText(), s.getEmailId());
    	searchResults();
    	status.setText("Successfully Updated!!");
    	updatedValue.setText("");
    }
   
}

	
