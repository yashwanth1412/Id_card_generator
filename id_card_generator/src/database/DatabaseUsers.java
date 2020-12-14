package database;
import java.sql.*;

import application.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseUsers{
	private String url, username, password, query ;
	private Connection connect;
	public DatabaseUsers() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		this.url = "jdbc:mysql://localhost:3306/id_card_generator";
		this.username = "yashwanth";
		this.password = "HarithaHema";
		this.connect = DriverManager.getConnection(url, username, password);
	}
	
	public void insertDetails(String name, String rollno, String department, String mobileno, String bloodgroup, String email, String dob) throws SQLException {
		Statement st = this.connect.createStatement();
		query = "insert into student values ( \""+name+"\", \""+ rollno +"\", \""+ department +"\", \""+ mobileno +"\", \""+ bloodgroup +"\", \""+ email +"\" ,\""+ dob +"\" )";
		st.executeUpdate(query);
		st.close();
	}
	
	public void deleteDetails(String searchBy, String detail) throws SQLException {
		Statement st = this.connect.createStatement();
		if(detail.equalsIgnoreCase("all")) {
			query = "delete from student";
		}
		else {
			query = "delete from student where "+ searchBy + " =\"" + detail + "\"";
		}
		st.executeUpdate(query);
		st.close();
	}
	
	public void updateDetails(String what, String to, String condition) throws SQLException{
		Statement st = this.connect.createStatement();
		query = "update student set " + what + " =\"" + to +"\" where email = \"" + condition + "\"";
		st.executeUpdate(query);
		st.close();
	}
	
	public ObservableList<Student> selectDetails(String searchBy, String detail) throws SQLException{
		 ObservableList<Student> data = FXCollections.observableArrayList();
		if(detail.equalsIgnoreCase("all")) {
			query = "select * from student";
		}
		else {
		    query = "select * from student where "+ searchBy + "= \"" + detail + "\"";
		}
		Statement st = this.connect.createStatement();
		ResultSet r = st.executeQuery(query);
		while(r.next()) {
			data.add(new Student (r.getString(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5), r.getString(6), r.getString(7)));
		}
		st.close();
		return data;
	}
	
	public void closeConnection() throws SQLException {
		this.connect.close();
	}
}