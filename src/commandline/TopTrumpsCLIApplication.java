package commandline;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


import model.Card;
import model.Characteristic;
import model.Deck;
import model.Game;
import model.Player;
import online.configuration.TopTrumpsJSONConfiguration;

/**
 * Top Trumps command line application
 */

// checking commit on eclipse
public class TopTrumpsCLIApplication {

	public static Game game;
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
		
		String name = textInput("What is your name?");
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
			
			
			Deck deck = loadCards();
			// this shuffle deck doesnt seem to be doing anything just now - method in Deck class
			Deck.shuffleDeck(deck);
			deck.print();
			
			// shuffleCards(deck, 40);
			
			//Deck.shuffleDeck();
			
			
			
			
			
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
	
	public static void setUpGame(int noOfPlayers) {
		Player[] players = new Player[noOfPlayers];
		Deck cards = new Deck();
		// shuffle method
		// distribute cards
		// make sure these cards are added to the player
	}
	
	public static Deck loadCards() {
		Deck newCards = new Deck();
		List<String> lines = null;

		try {
			lines = Files.readAllLines(Paths.get("StarCitizenDeck.txt"), StandardCharsets.US_ASCII);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] characteristicsNames = lines.get(0).split(" ");
		String[] values;
		for(int i = 1; i < lines.size(); i++) {
			values = lines.get(i).split(" ");
			Characteristic c1 = new Characteristic(characteristicsNames[1],Integer.valueOf(values[1]));
			Characteristic c2 = new Characteristic(characteristicsNames[2],Integer.valueOf(values[2]));
			Characteristic c3 = new Characteristic(characteristicsNames[3],Integer.valueOf(values[3]));
			Characteristic c4 = new Characteristic(characteristicsNames[4],Integer.valueOf(values[4]));
			Characteristic c5 = new Characteristic(characteristicsNames[5],Integer.valueOf(values[5]));
			
			Characteristic[] characteristics = new Characteristic[] {
					c1, c2, c3, c4, c5
			};

			Card newCard = new Card(values[0],characteristics);
			newCards.addCard(newCard);
		}
		return newCards;
	}
	// these shuffle deck not fully functional yet - need to try sort this out
//	public static void shuffleDeck() {
//		int sizeOfDeck = 40;
//		List<Card> shuffled = new ArrayList<Card>();
//		shuffled.addAll((Collection<Card>) loadCards());
//		Random random = new Random();
//		for(int i=shuffled.size() -1; i>=0;i--) {
//			int j = random.nextInt(i+1);
//			
//			// swap cards
//			Card card = shuffled.get(i);
//			shuffled.set(i, shuffled.get(j));
//			shuffled.set(j, card);
//		}
//	}
//	
//	public static void shuffleCards(Deck deck, int number) {
//		Random random = new Random();
//		
//		for(int i = 0; i<number; i++) {
//			int rand = i + random.nextInt(40-i);
//			
//			int temp = deck[rand];
//			deck[rand] = deck[i];
//			deck[i] = temp;
//		}
//		
//	}
	
}
