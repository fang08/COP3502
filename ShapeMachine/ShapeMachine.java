/*Project 2
Fang Yao
UFL ID: 37961919
Section: 11H6
Brief description: The program creates a mathematical shape machine that 
takes a radius/side and will compute both the area and the perimeter/circumference*/

import java.text.SimpleDateFormat;
import java.util.*;
public class ShapeMachine {
	public static void main(String args[]) {
		System.out.println("Shape Machine login\n");
		Scanner input = new Scanner(System.in);//Create a Scanner named input
		
		//generate today's date
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String day = date.substring(0,2);
		String month = date.substring(3,5);
		String year = date.substring(6);
		
		//compare system date and user input date
		int countDate = 0;
		String userDay, userMonth, userYear;
		for (int a = 0; a < 3; a++){
			//user input date
			System.out.print("What is today's day? ");
			userDay = input.next();
			System.out.print("What is today's month? ");
			userMonth = input.next();
			System.out.print("What is today's year? ");
			userYear = input.next();
			
			if (userDay.equals(day) && userMonth.equals(month) && userYear.equals(year)){
				System.out.println("Correct date. Welcome!");
				break;
			}
			else {
				countDate ++;
				if (countDate < 3){
					System.out.println("#ERROR Login attempt #" + countDate + " Invalid input. Please try again.\n");
				}
				else {
					System.out.println("#ERROR 3rd invalid login attempt. Terminating program.");
					System.exit(0);
				}
			}	
		}
		
		//define shape options
		String option1 = "Circles";
		String option2 = "Rectangles";
		String option3 = "Triangles";
		String option4 = "Exit";
		String shape = "";

		//Shape machine main body
		while (true) {
			System.out.println("\n---Welcome to the Shape Machine---");
			System.out.println("Available Options:\n" + "Circles\n" + "Rectangles\n" + "Triangles\n" + "Exit\n");
			shape = input.next();
			
			//select circles
			if (shape.equals(option1)) {
				System.out.print("Circles selected. Please enter the radius: ");
				double radius = input.nextDouble();
				while (radius <= 0) {
					System.out.print("#ERROR Negative input. Please input the radius again: ");
					radius = input.nextDouble();
				}
				double circumference = 2* Math.PI * radius;
				double circleArea = Math.PI * radius * radius;
				int circleDigit1 = (Double.toString(circumference)).length()-1;
				int circleDigit2 = (Double.toString(circleArea)).length()-1;
				System.out.println("The circumference is: " + circumference);
				System.out.println("The area is: " + circleArea);
				System.out.println("Total number of digits in the circumference is: " + circleDigit1);
				System.out.println("Total number of digits in the area is: " + circleDigit2);
				continue;
			}
			
			//select rectangles
			else if (shape.equals(option2)) {
				System.out.print("Rectangles selected. Please enter the 2 sides: ");
				double recSide1 = input.nextDouble();
				double recSide2 = input.nextDouble();
				while (recSide1 <= 0 || recSide2 <= 0) {
					System.out.print("#ERROR Negative input. Please input the 2 sides again: ");
					recSide1 = input.nextDouble();
					recSide2 = input.nextDouble();
				}
				double recArea = recSide1 * recSide2;
				double recPerimeter = 2* (recSide1 + recSide2);
				int recDigit1 = (Double.toString(recArea)).length()-1;
				int recDigit2 = (Double.toString(recPerimeter)).length()-1;
				System.out.println("The area is: " + recArea);
				System.out.println("The perimeter is: " + recPerimeter);
				System.out.println("Total number of digits in the area is: " + recDigit1);
				System.out.println("Total number of digits in the perimeter is: " + recDigit2);
				continue;
			}
			
			//select triangles
			else if (shape.equals(option3)) {
				System.out.print("Triangles selected. Please enter the 3 sides: ");
				double triSide1 = input.nextDouble();
				double triSide2 = input.nextDouble();
				double triSide3 = input.nextDouble();
				//check negative input
				while (triSide1 <= 0 || triSide2 <= 0 || triSide3 <= 0) {
					System.out.print("#ERROR Negative input. Please input the 3 sides again: ");
					triSide1 = input.nextDouble();
					triSide2 = input.nextDouble();
					triSide3 = input.nextDouble();
				}
				
				//check constitution of a valid triangle
				if ((triSide1 + triSide2 > triSide3) && (triSide1 + triSide3 > triSide2) && (triSide2 + triSide3 > triSide1)) {
					double triPerimeter = triSide1 + triSide2 + triSide3;
					double p = triPerimeter/2;
					double triArea = Math.sqrt(p*(p-triSide1)*(p-triSide2)*(p-triSide3));
					int triDigit1 = (Double.toString(triPerimeter)).length()-1;
					int triDigit2 = (Double.toString(triArea)).length()-1;
					
					//check the shape of triangle
					if (triSide1 == triSide2 && triSide1 == triSide3 && triSide2 == triSide3) {
						System.out.println("The triangle is: Equilateral");
					}
					else if ((triSide1 == triSide2 && triSide1 != triSide3) ||
							(triSide1 == triSide3 && triSide2 != triSide3) ||
							(triSide2 == triSide3 && triSide1 != triSide2)){
						System.out.println("The triangle is: Isosceles");
					}
					else {
						System.out.println("The triangle is: Scalene");
					}
					System.out.println("The perimeter is: " + triPerimeter);
					System.out.println("The area is: " + triArea);
					System.out.println("Total number of digits in the perimeter is: " + triDigit1);
					System.out.println("Total number of digits in the area is: " + triDigit2);
					continue;
				}
				else {
					System.out.println("#ERROR Triangle is not valid. Returning to menu.");
					continue;
				}
			}
			
			//select exit
			else if (shape.equals(option4)) {
				System.out.println("Terminating the program. Have a nice day!");
				System.exit(0);
			}
			
			//other invalid inputs
			else {
				System.out.println("#ERROR Invalid option. Please try again.");
				continue;
			}
		}
	}
}
