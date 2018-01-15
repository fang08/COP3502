import java.io.*;
import java.util.ArrayList;

public class HighSchoolStudent extends Student{
	private String nameOfSchool;
	
	//constructor
	HighSchoolStudent(String name, String id, String essay, ArrayList<String> errorList, String nameOfSchool) {
		super(name, id, essay, errorList);
		this.nameOfSchool = nameOfSchool;
	}
	
	public String getSchoolName(){
		return nameOfSchool;
	}
	
	public void setSchoolName(String schoolName){
		nameOfSchool = schoolName;
	}

	//implements the abstract method in Student class
	@Override
	public void writeToFile() {  
	    try {
	    	PrintWriter gradeOutput = new PrintWriter(super.getId() + "_graded.txt");
	    	gradeOutput.println("High School Student " + super.getName());
	    	gradeOutput.println("Student ID: " + super.getId());
	    	gradeOutput.println("School Name: " + getSchoolName());
	    	gradeOutput.println(super.getPrintableErrorList());
	    	gradeOutput.close();
	    }
	    catch (FileNotFoundException ex){
	    	//do something
	    }
	}
}