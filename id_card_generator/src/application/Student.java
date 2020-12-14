package application;
import database.DatabaseUsers;
import javafx.collections.ObservableList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student {
	private String name;
	private String dob;
	private String department;
	private String mobileNo;
	private String emailId;
	private String bloodGrp;
	private String rollNo;	
	public Student(String name, String rollNo, String dept, String mobileNo,  String bloodGrp, String email, String dob) {
		this.name = name;
		this.dob = dob;
		this.rollNo = rollNo;
		this.department = dept;
		this.mobileNo = mobileNo;
		this.emailId = email.toLowerCase();
		this.bloodGrp = bloodGrp.toLowerCase();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getBloodGrp() {
		return bloodGrp;
	}

	public void setBloodGrp(String bloodGrp) {
		this.bloodGrp = bloodGrp;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public boolean validateDetails() {
		return validateName() && validateDept() && validateEmail() && validateMobileNo() ;
	}
	
	private boolean validateName() {
		Pattern pattern_name;
		Matcher matcher;
		String nameregex = "^[a-z]+";
		pattern_name = Pattern.compile(nameregex);
		matcher = pattern_name.matcher(this.name.toLowerCase());
		if(matcher.matches()) {
			return true;
		}
		return false;
	}
	
	private boolean validateDept() {
		if(this.department.equalsIgnoreCase("cse")) {
			return true;
		}
		return false;
	}
	
	private boolean validateEmail() {
		Pattern pattern_name;
		Matcher matcher;
		String nameregex = ".+@iiitr.ac.in$" ;
		pattern_name = Pattern.compile(nameregex);
		matcher = pattern_name.matcher(this.emailId);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}
	
	private boolean validateMobileNo() {
		Pattern pattern_name;
		Matcher matcher;
		String nameregex = "[0-9]{10}";
		pattern_name = Pattern.compile(nameregex);
		matcher = pattern_name.matcher(this.mobileNo);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}
	
	public boolean checkInDatabase() throws Exception {
		DatabaseUsers data = new DatabaseUsers();
		ObservableList<Student> detailsofRoll =  data.selectDetails("rollno", this.rollNo);
		ObservableList<Student> detailsofEmail = data.selectDetails("email", this.emailId);
		data.closeConnection();
		if(detailsofRoll.isEmpty() && detailsofEmail.isEmpty()) {
			return true;
		} 
		return false;
	}
	
	public void insertToDatabase() throws Exception {
		DatabaseUsers data = new DatabaseUsers();
		data.insertDetails(this.name, this.rollNo, this.department, this.mobileNo, this.bloodGrp, this.emailId, this.dob);
		data.closeConnection();
	}
	
}
