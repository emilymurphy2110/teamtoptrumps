package commandline;

import java.util.Scanner;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
 	 * @param args
	 */
	public static void main(String[] args) {

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection
		
		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		// Loop until the user wants to exit the game
		
		String name = textInput("What is your name??");
		int noOfAI = -1;
		
		while (!userWantsToQuit) {

			switch(menu("Play game", "Past Game Statistics")) {
			case 0: 
				userWantsToQuit=true; 
				break;
			case 1: 
				System.out.println("Starting game");
				noOfAI = numberInput("Hello " + name + ", How many players do you want to play?", 1, 4);
				break;
			case 2:
				System.out.println("Past Game statistics");
				break;
		}
			
			userWantsToQuit=true; // use this when the user wants to exit the game
			
		}


	}
	
	private static int menu(String... strings) {
		while (true) {
			System.out.println("Please select an option");
			for (int i = 0; i < strings.length; i++) {
				System.out.println(i + 1 + " - " + strings[i]);
			}
			System.out.println("0 - Quit game");
			Scanner sc = new Scanner(System.in);
			try {
				int choice = sc.nextInt();
				if (choice < 0 || choice > strings.length) {
					System.out.println("Invalid menu input");
				} else {
					return choice;
				}
			} catch (Exception e) {
				System.out.println("Invalid menu input");
			}
		}
	}
	
	public static String textInput(String question) {
		System.out.println(question);
		Scanner s = new Scanner(System.in);
		return s.nextLine();
	}
	
	public static int numberInput(String question, int min, int max) {
		while (true) {
			System.out.println(question + " [" + min + "-" + max + "]");
			Scanner s = new Scanner(System.in);
			try {
				int choice = s.nextInt();
				if (choice < min || choice > max) {
					System.out.println("Invalid input");
				} else {
					return choice;
				}
			} catch (Exception e) {
				System.out.println("Error: enter number");
			}
		}
	}

}
