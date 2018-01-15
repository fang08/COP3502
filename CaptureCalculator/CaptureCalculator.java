/*Project 1

Fang Yao
UFL ID: 37961919
Section: 11H6
Brief description: The program tells users the probability of capturing a monster in the game Brokemon Go.*/

//import Libraries
import java.lang.Math;
import java.util.Scanner;

public class CaptureCalculator {
	public static void main(String args[]) {
		System.out.println("Hello and welcome to the Monster Capture Possibility Calculator.");
		
		Scanner console = new Scanner(System.in);//Create a Scanner named console
		
		// Save user inputs as variables
		System.out.println("Please enter the latitude of the monster (1-10): ");
		int yMonster = console.nextInt();//monster's location latitude
		
		System.out.println("Please enter the longitude of the monster (1-10): ");
		int xMonster = console.nextInt();//monster's location longitude
		
		System.out.println("Please enter the time of the monster appear (1-1440): ");
		int appearTime = console.nextInt();//monster appear time (24hour*60mins=1440)
		
		System.out.println("Please enter the possible time of the monster will exist (10-59): ");
		int existTime = console.nextInt();//monster possible exist time
		
		System.out.println("Please enter the player's ID (8 digits): ");
		int playerID = console.nextInt();//player's ID (8 digits & not begin with 0)
		int lastIDnumber = playerID % 100;//last two numbers of ID to determine lucky or normal list
		
		// Define lucky or normal list
		String listName = "";
		if (lastIDnumber>=0 && lastIDnumber<=49){
			listName = "lucky";
		}
		else {
			listName = "normal";
		}
		
		System.out.println("Please enter the time of the player noticing monster (1-1440 and greater than the time the monster appears): ");
		int noticeTime = console.nextInt();//player notice time, same day as monster appears
		
		System.out.println("Please enter the latitude of the player (1-10): ");
		int yPlayer = console.nextInt();//player's location latitude
		
		System.out.println("Please enter the longitude of the player (1-10): ");
		int xPlayer = console.nextInt();//player's location longitude
		
		System.out.println("Please enter the player's walking speed (10-200): ");
		int walkSpeed = console.nextInt();//player's walk speed
		
		// All the math calculation
		double distance = Math.sqrt(Math.pow(xPlayer-xMonster, 2) + Math.pow(yPlayer-yMonster, 2)) * 1000;
		//distance between monster and player
		double getTime = noticeTime + Math.round((distance/walkSpeed)*10) / 10.0;
		//the time player gets to monster's location
		int disappearTime = appearTime + existTime;
		// monster's disappear time
		double proportion = ((getTime - disappearTime) / existTime) * 100;
		// proportion of monster's disappear probability
		distance= Math.round(distance * 10) / 10.0;
		
		// Determine the probability of capture
		String probCapture = "";
		if (getTime > disappearTime){
			if ((listName == "lucky" && proportion > 0 && proportion <= 10) || (listName == "normal" && proportion > 0 && proportion <= 5)) {
				probCapture = "highly likely";
			}
			else if ((listName == "lucky" && proportion > 10 && proportion <= 30) || (listName == "normal" && proportion > 5 && proportion <= 20)) {
				probCapture = "likely";
			}
			else if ((listName == "lucky" && proportion > 30 && proportion <= 40) || (listName == "normal" && proportion > 20 && proportion <= 35)) {
				probCapture = "unsure";
			}
			else if ((listName == "lucky" && proportion > 40 && proportion <= 50) || (listName == "normal" && proportion > 35 && proportion <= 40)) {
				probCapture = "unlikely";
			}
			else {
				probCapture = "highly unlikely";
			}
		}
		else {
			probCapture = "definitely";
		}
		
		// Print out the results
		System.out.println("Player " + playerID + " who is on the " + listName + " list,");
		System.out.println("noticed the monster at time " + noticeTime + ",");
		System.out.println("is " + distance + " m away from the monster,");
		System.out.println("and will arrive at time " + getTime + ".");
		System.out.println("The monster's disappear time is about " + disappearTime + ".");
		System.out.println("So the player will capture this monster with " + probCapture + " possibility.");
		
		console.close();		
	}
}
