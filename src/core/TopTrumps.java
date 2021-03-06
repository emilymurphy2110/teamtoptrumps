package core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import commandline.TopTrumpsCLIApplication;
import database.DatabaseLogic;
import model.Card;
import model.Characteristic;
import model.Deck;
import model.GameData;
import model.Player;
import online.TopTrumpsOnlineApplication;

public class TopTrumps {
	
	private static Player[] players;
	public static int noOfCards;
	private static boolean[] eliminated;
	private static int roundCounter = 0;
	private static int roundWinner = -1;
	private static Deck communalPile = new Deck();
	private static int startingPlayer;
	private static PrintWriter logWriter;
	private static GameData game = new GameData();
	// command line switches
	// moved these out the main so they can be seen within the other methods
	static boolean onlineMode = false;
	static boolean commandLineMode = false;
	static boolean printTestLog = false;
	public static Deck topCards = new Deck();
	public static int playerChooseAttribute = 0;

	

	/** This is the main class for the TopTrumps Applications */
	public static void main(String[] args) {

		System.out.println("--------------------");
		System.out.println("--- Top Trumps   ---");
		System.out.println("--------------------");


		DatabaseLogic.initiateDatabase();

		// check the command line for what switches are active
		for (String arg : args) {

			if (arg.equalsIgnoreCase("-t"))
				printTestLog = true;
			if (arg.equalsIgnoreCase("-c"))
				commandLineMode = true;
			if (arg.equalsIgnoreCase("-o"))
				onlineMode = true;

		}

		// We cannot run online and command line mode simultaneously
		if (onlineMode && commandLineMode) {
			System.out.println("ERROR: Both online and command line mode selected, select one or the other!");
			System.exit(0);
		}

		// Start the appropriate application
		if (onlineMode) {
			// Start the online application
			String[] commandArgs = { "server", "TopTrumps.json" };
			TopTrumpsOnlineApplication.main(commandArgs);
		} else if (commandLineMode) {
			// Start the command line application
			String[] commandArgs = { String.valueOf(printTestLog) };
			TopTrumpsCLIApplication.main(commandArgs);
		}

	}

	// game logic for both command line and online mode

	public static void setUpGame(int noOfPlayers, String playerName) {
		// initiating the logWriter to save to toptrumps.log
		// this will override the previous file
		try {
			logWriter = new PrintWriter("toptrumps.log", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		roundCounter = 0;
		// creates an array of players
		players = new Player[noOfPlayers];
		eliminated = new boolean[noOfPlayers];
		for(int i=0; i<eliminated.length; i++) {
			eliminated[i] = false;
		}
		// loads the cards
		Deck cards = loadCards();
		writeLog(cards);
		noOfCards = cards.getCards().size();
		// shuffles the cards
		cards.shuffle();
		writeLog(cards);
		// System.out.println(cards.getCards().size());
		// splits the cards between the number of players
		Deck[] hands = cards.split(noOfPlayers);
		for (int i = 0; i < noOfPlayers; i++) {
			players[i] = new Player(i, hands[i]);
			if(i==0)players[0].setName(playerName);
			writeLog(players[i] + "cards are: \n" + players[i].getDeck().toString());
		}
		
		// randomise starting player
		Random random = new Random();
		startingPlayer = random.nextInt(noOfPlayers);
		if(commandLineMode) {
			roundStage1();
		}
	}

	// method for loading cards
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
		for (int i = 1; i < lines.size(); i++) {
			values = lines.get(i).split(" ");
			Characteristic c1 = new Characteristic(characteristicsNames[1], Integer.valueOf(values[1]));
			Characteristic c2 = new Characteristic(characteristicsNames[2], Integer.valueOf(values[2]));
			Characteristic c3 = new Characteristic(characteristicsNames[3], Integer.valueOf(values[3]));
			Characteristic c4 = new Characteristic(characteristicsNames[4], Integer.valueOf(values[4]));
			Characteristic c5 = new Characteristic(characteristicsNames[5], Integer.valueOf(values[5]));

			Characteristic[] characteristics = new Characteristic[] { c1, c2, c3, c4, c5 };

			Card newCard = new Card(values[0], characteristics);
			newCards.addCard(newCard);
		}
		return newCards;
	}
	
	public static void roundStage1() {
		roundCounter++;
		System.out.println("\n\nround: " + roundCounter);
		System.out.println("you have " + (players[0].getDeck().getCards().size()) + " cards in your hand");

		// step 2 - selects top card from each player
		for(int i = 0; i<players.length; i++) {
			if(!eliminated[i]) {
				topCards.addCard(players[i].getDeck().removeCard(players[i].getDeck().getCards().size() - 1));
			}else {
				topCards.addCard(null);
			}
		}
		writeLog("The cards in play: \n" + topCards.toString());
		if(!eliminated[0]) {
			System.out.println(topCards.getCards().get(0));
		}
		if(commandLineMode) {
			roundStage2();
		}
	}
	
	public static void roundStage2() {
		
		// step 3 - if human: present card on screen and ask for attribute
		// if AI: automatically choose highest attribute
		int chosenAttribute = 0;
		// change to -1 to have an all AI game, change to 0 to have a human player
		if(playerChooseAttribute == -1 && commandLineMode) {
			chosenAttribute = TopTrumpsCLIApplication.numberInput("Choose a Characteristic", 1, 5) -1;
		}else {
			Characteristic[] characteristicsPlayerCard = topCards.getCards().get(playerChooseAttribute).getCharacteristics();
			for(int i = 0; i< characteristicsPlayerCard.length; i++) {
				if(characteristicsPlayerCard[i].getValue() > characteristicsPlayerCard[chosenAttribute].getValue()) {
				chosenAttribute = i;
				}
			}
		}
		String s = "";
		s += "The category selected is: " + topCards.getCards().get(playerChooseAttribute).getCharacteristics()[chosenAttribute].getName();
		for(int i = 0; i<players.length;i++) {
			if(!eliminated[i]) {
				s+= "\n Player " + (i+1) + " value is: " + topCards.getCards().get(i).getCharacteristics()[chosenAttribute].getValue();
			}
		}
		writeLog(s);
		System.out.println("The category selected is: " + topCards.getCards().get(playerChooseAttribute).getCharacteristics()[chosenAttribute].getName());
		// step 4 - decides the winner or if a draw
//		int roundWinner = -1;
		for(int i=0;i<topCards.getCards().size();i++) {
			if(topCards.getCards().get(i)!=null) {
				setRoundWinner(i);
			}
		}
		boolean draw = false;
		for(int i = 0; i < topCards.getCards().size(); i++) {
			if(!eliminated[i]) {
				if(topCards.getCards().get(i).getCharacteristics()[chosenAttribute].getValue() > 
				topCards.getCards().get(getRoundWinner()).getCharacteristics()[chosenAttribute].getValue()) {
					setRoundWinner(i);
				}
		}
		}
		
		for(int i = 0; i < topCards.getCards().size(); i++) {
			if(!eliminated[i]) {
				if(topCards.getCards().get(i).getCharacteristics()[chosenAttribute].getValue() == 
					topCards.getCards().get(getRoundWinner()).getCharacteristics()[chosenAttribute].getValue() && i != getRoundWinner()) {
					draw = true;
			}
			}
		}
		// step 5 - show all the cards and the winner
		if(draw) {
			System.out.println("It's a draw!");
		}else {
			if(getRoundWinner() == 0) {
				System.out.println("You won! and your card was:");	
			}else {

// 				System.out.println("Player " + getRoundWinner() + " won! and their card was:");	
// 				}
// 			System.out.println(topCards.getCards().get(getRoundWinner()));

				System.out.println(players[roundWinner].getName() + " won! and their card was:");	
				}
			System.out.println(topCards.getCards().get(roundWinner));
			//System.out.println(players[roundWinner] + " will pick the next characteristic.");

		}
		System.out.println(players[roundWinner].getName() + " will pick the next characteristic");

		// step 6 - if draw: transfer all cards to the communal pile
		// winner: transfer all cards from round to back of winners cards 
		// also if winner and there is cards in the com pile, add them to back and empty com pile
		if(draw) {
			Deck.transferHand(topCards, communalPile);
			writeLog("cards added to the communal pile: \n" + communalPile.toString());
			System.out.println("there is " + communalPile.getCards().size() + " cards in the communal pile");
			game.incrementDraws();
		}else {

// 			Deck.transferHand(topCards, players[getRoundWinner()].getDeck());
// 			Deck.transferHand(communalPile, players[getRoundWinner()].getDeck());
// 			players[getRoundWinner()].roundWon();

			Deck.transferHand(topCards, players[roundWinner].getDeck());
			Deck.transferHand(communalPile, players[roundWinner].getDeck());
			if(!communalPile.getCards().isEmpty()) {
				writeLog("cards removed from the communal pile: \n" + communalPile.toString());
			}
			players[roundWinner].roundWon();

		}
		for (int i = 0; i < players.length; i++) {
			if(i==0) {
				writeLog("the human player cards are: \n" + players[i].getDeck().toString());
			}else {
				writeLog("the computer " + i + "players cards are: \n" + players[i].getDeck().toString());
			}
		}
		// step 7 - if winner the game will end and show stats of that game but not coded yet and offer main menu to player 
		// will also check if any player has 0 cards and eliminate them
		// then offer human player to proceed to next round (not coded yet also) - commented below what should happen
		boolean gameEnded = false;
		int gameWinner = -1;
		game.incrementRoundCounter();
		for(int i = 0; i < players.length; i++) {
			if(players[i].getDeck().getCards().size() == noOfCards-communalPile.getCards().size()) {
				gameEnded = true;
				gameWinner = i;
			}else if(players[i].getDeck().getCards().size() == 0) {
				if(!eliminated[i]) {
					eliminated[i] = true;
					System.out.println(players[i].getName() + " has been eliminated");
				}
			}
//			if(eliminated[i]) {
//				System.out.println(players[i].getName() + " has been eliminated");
//			}
		}
		if(gameEnded) {
			System.out.println("\nThe winner is: " + players[gameWinner].getName());
			writeLog("The winner is: " + players[gameWinner].getName());
			game.setWinner(gameWinner);
			DatabaseLogic.insertRecord(game);
			logWriter.close();
			for(int i = 0; i<players.length; i++) {
				System.out.println(players[i].getName() + " and they won " + players[i].getNoRoundsWon() + " rounds");
			}
			int choice = TopTrumpsCLIApplication.menu("Go back to main menu");
			if(choice==0) {
				DatabaseLogic.disconnectDatabase();
				System.exit(0);
			}else if(choice==1) {
				TopTrumpsCLIApplication.mainMenu();
			}
		}else {
			if(draw) {
				
				for(int i=1;i<players.length;i++) {
					if(!eliminated[(playerChooseAttribute+i)%players.length]) {
						playerChooseAttribute = (playerChooseAttribute+i)%players.length;
					}
				}
			}else {
				playerChooseAttribute = roundWinner;
			}
			if(commandLineMode) {
				roundStage1();
			}
		}
	}
	
	// if '-t' has been called, writeLog() will print to the log file
		public static void writeLog(Object log) {
			if(printTestLog) {
				logWriter.println(log);
				logWriter.println("-----------------------");	
			}
		}
		
		// prints statistics for commandline
		public static void printStats() {
			//DatabaseLogic.getDatabaseStats();
			//System.out.println(Arrays.toString(DatabaseLogic.getDatabaseStats()));
			int[] stats = DatabaseLogic.getDatabaseStats();
			System.out.println("Number of Games: " + stats[0]);
			System.out.println("Total computer wins: " + stats[1]);
			System.out.println("Total human wins: " + stats[2]);
			System.out.println("Average number of draws: " + stats[3]);
			System.out.println("Most rounds played in a single game: " + stats[4]);
		}

 	public static int getRoundCounter() {
 		return roundCounter;
 	}

 	public static void setRoundCounter(int roundCounter) {
 		TopTrumps.roundCounter = roundCounter;
 	}

 	public static int getRoundWinner() {
 		return roundWinner;
 	}


	public static void setRoundWinner(int roundWinner) {
		TopTrumps.roundWinner = roundWinner;
	}

 	public static Deck getCommunalPile() {
 		return communalPile;
 	}

 	public static void setCommunalPile(Deck communalPile) {
 		TopTrumps.communalPile = communalPile;
 	}

 	public static int getStartingPlayer() {
 		return startingPlayer;
 	}

 	public static void setStartingPlayer(int startingPlayer) {
 		TopTrumps.startingPlayer = startingPlayer;
 	}

}