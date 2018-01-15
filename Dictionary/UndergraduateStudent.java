import java.io.*;
import java.util.ArrayList;

public class UndergraduateStudent extends Student{
	private String major;
	
	//constructor
	UndergraduateStudent(String name, String id, String essay, ArrayList<String>errorList, String major) {
		super(name, id, essay, errorList);
		this.major = major;
	}
	
	public String getMajor(){
		return major;
	}
	
	public void setMajor(String major){
		this.major = major;
	}
	
	//implements the abstract method in Student class
	@Override
	public void writeToFile() {
		try {
		    PrintWriter gradeOutput = new PrintWriter(super.getId() + "_graded.txt");
		    gradeOutput.println("Undergraduate Student " + super.getName());
		    gradeOutput.println("Student ID: " + super.getId());
		    gradeOutput.println("Major: " + getMajor());
		    gradeOutput.println(super.getPrintableErrorList());
		    gradeOutput.close();
		 }
		catch (FileNotFoundException ex){
		    //do something
		}
	}
}