import java.io.*;
import java.util.*;

public class Grader {
	private boolean available;
	private Student student;
	private Dictionary dict;
	
	//constructor
	public Grader(Dictionary dict){
		this.dict = dict;
		student = null;
		available = true;
	}
	
	//grade the given student essay based on passed in student file name
	public boolean gradeStudent(String fileName){
		File file = new File(fileName + ".txt");
		try{
			Scanner inputFile = new Scanner(file);
			String studentStatus = "";
			if (inputFile.hasNextLine())
				studentStatus = inputFile.nextLine();
			String studentID = "";
			String studentName = "";
			String schoolName = "";
			String studentMajor = "";
			String studentAdvisor = "";
			String studentEssay = "";
			ArrayList<String> errorList = new ArrayList<>();
		
			if (studentStatus.equalsIgnoreCase("HighSchool Student")){
				if (inputFile.hasNextLine()) {
					studentName = inputFile.nextLine();
					studentID = inputFile.nextLine();
					schoolName = inputFile.nextLine();
				}
				while (inputFile.hasNextLine()) {
					String line = inputFile.nextLine();
					line = line.replaceAll("[,.;:!?(){}\\[\\]<>%]", "");
					studentEssay = studentEssay + line;
					String[] word = line.split(" ");
					for (int i = 0; i < word.length; i++){
						if (!dict.isWord(word[i])){
							errorList.add(word[i]);//if repeated error???
						}
					}
				}
				student = new HighSchoolStudent(studentName, studentID, studentEssay, errorList, schoolName);
				available = true;
				inputFile.close();
				return true;
			}
			
			else if (studentStatus.equalsIgnoreCase("Undergraduate Student")){
				if (inputFile.hasNextLine()) {
					studentName = inputFile.nextLine();
					studentID = inputFile.nextLine();
					studentMajor = inputFile.nextLine();
				}
				while (inputFile.hasNextLine()) {
					String line = inputFile.nextLine();
					line = line.replaceAll("[,.;:!?(){}\\[\\]<>%]", "");
					studentEssay = studentEssay + line;
					String[] word = line.split(" ");
					for (int i = 0; i < word.length; i++){
						if (!dict.isWord(word[i])){
							errorList.add(word[i]);//if repeated error???
						}
					}
				}
				student = new UndergraduateStudent(studentName, studentID, studentEssay, errorList, studentMajor);
				available = true;
				inputFile.close();
				return true;
			}
			
			else if (studentStatus.equalsIgnoreCase("Graduate Student")){
				if (inputFile.hasNextLine()) {
				studentName = inputFile.nextLine();
				studentID = inputFile.nextLine();
				studentMajor = inputFile.nextLine();
				studentAdvisor = inputFile.nextLine();
				}
				while (inputFile.hasNextLine()) {
					String line = inputFile.nextLine();
					line = line.replaceAll("[,.;:!?(){}\\[\\]<>%]", "");
					studentEssay = studentEssay + line;
					String[] word = line.split(" ");
					for (int i = 0; i < word.length; i++){
						if (!dict.isWord(word[i])){
							errorList.add(word[i]);//if repeated error???
						}
					}
				}

				student = new GraduateStudent(studentName, studentID, studentEssay, errorList, studentMajor, studentAdvisor);
				available = true;
				inputFile.close();
				return true;
			}
			
			else{
				reset();
				inputFile.close();
				return false;
			}
		}
		catch (FileNotFoundException ex){
			reset();
			return false;
		}
	}
	
	//Check whether the grader is available to grade next student
	public boolean isAvailable(){
		if (student == null && available == true)
			return true;
		else
			return false;
	}
	
	//Get the graded/generated student instance
	public Student getStudent(){
		if (student instanceof HighSchoolStudent)
			return (HighSchoolStudent)student;
		else if (student instanceof UndergraduateStudent)
			return (UndergraduateStudent)student;
		else if (student instanceof GraduateStudent)
			return (GraduateStudent)student;
		else
			return null;
	}
	
	//Reset the local variables, exclude the dict
	public void reset(){
		this.student = null;
		this.available = true;
	}
}