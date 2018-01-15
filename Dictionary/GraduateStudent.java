import java.io.*;
import java.util.ArrayList;

public class GraduateStudent extends Student{
	private String major;
	private String advisor;
	
	//constructor
	GraduateStudent(String name, String id, String essay, ArrayList<String> errorList, String major, String advisor) {
		super(name, id, essay, errorList);
		this.major = major;
		this.advisor = advisor;
	}
	
	public String getMajor(){
		return major;
	}
	
	public void setMajor(String major){
		this.major = major;
	}
	
	public String getAdvisor(){
		return advisor;
	}
	
	public void setAdvisor(String advisor){
		this.advisor = advisor;
	}
	
	//implements the abstract method in Student class
	@Override
	public void writeToFile() {
		try {
		    PrintWriter gradeOutput = new PrintWriter(super.getId() + "_graded.txt");
		    gradeOutput.println("Graduate Student " + super.getName());
		    gradeOutput.println("Student ID: " + super.getId());
		    gradeOutput.println("Major: " + getMajor());
		    gradeOutput.println("Advisor: " + getAdvisor());
		    gradeOutput.println(super.getPrintableErrorList());
		    gradeOutput.close();
		 }
		catch (FileNotFoundException ex){
		    //do something
		}
	}
}