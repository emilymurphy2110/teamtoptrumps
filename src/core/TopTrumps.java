package core;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import commandline.TopTrumpsCLIApplication;
import model.Card;
import model.Characteristic;
import model.Deck;
import model.Player;
import online.TopTrumpsOnlineApplication;

public class TopTrumps {
	
	private static Player[] players;
	public static int noOfCards;
	private static boolean[] eliminated;
	private static int roundCounter = 0;
	private static int roundWinner = -1;
	private static Deck communalPile;
	private static int startingPlayer;


	/** This is the main class for the TopTrumps Applications */
	public static void main(String[] args) {

		System.out.println("--------------------");
		System.out.println("--- Top Trumps   ---");
		System.out.println("--------------------");

		// command line switches
		boolean onlineMode = false;
		boolean commandLineMode = false;
		boolean printTestLog = false;

		// check the command line for what switches are active
		for (String arg : args) {

			if (arg.equalsIgnoreCase("-t"))
				printTestLog = true;
			if (arg.equalsIgnoreCase("-c"))
				commandLineMode = true;
			if (arg.equalsIgnoreCase("-o"))
				onlineMode = true;

		}

		// We cannot run online and command line mode simultaniously
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

	public static void setUpGame(int noOfPlayers) {
		// creates an array of players
		players = new Player[noOfPlayers];
		eliminated = new boolean[noOfPlayers];
		for(int i=0; i<eliminated.length; i++) {
			eliminated[i] = false;
		}
		// loads the cards
		Deck cards = loadCards();
		noOfCards = cards.getCards().size();
		// shuffles the cards
		cards.shuffle();
		// System.out.println(cards.getCards().size());
		// splits the cards between the number of players
		Deck[] hands = cards.split(noOfPlayers);
		for (int i = 0; i < noOfPlayers; i++) {
			players[i] = new Player(i, "", hands[i]);
			 //System.out.println(players[i]);
		}
		// randomise starting player
		Random random = new Random();
		int startingPlayer = random.nextInt(noOfPlayers);
		round(startingPlayer, new Deck());
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
			//System.out.println(newCards);
		}
		return newCards;
	}
	
	public static void round(int playerChooseAttribute, Deck communalPile) {
		// step 1 
		setRoundCounter(getRoundCounter() + 1);
		System.out.println("\n\nround: " + getRoundCounter());
		System.out.println("you have " + (players[0].getDeck().getCards().size() - 1) + " cards in your hand");
		// step 2 - selects top card from each player
		Deck topCards = new Deck();
		for(int i = 0; i<players.length; i++) {
			if(!eliminated[i]) {
				topCards.addCard(players[i].getDeck().removeCard(players[i].getDeck().getCards().size() - 1));
			}else {
				topCards.addCard(null);
			}
		}
		System.out.println(topCards.getCards().get(0));
		// step 3 - if human: present card on screen and ask for attribute
		// if AI: automatically choose highest attribute
		int chosenAttribute = -1;
		if(playerChooseAttribute == 0) {
			//System.out.println(topCards.getCards().get(0));
			chosenAttribute = TopTrumpsCLIApplication.numberInput("Choose a Characteristic", 1, 5) -1;
		}else {
//			System.out.println(topCards);
//			System.out.println(topCards.getCards().size());
//			System.out.println(Arrays.toString(eliminated));
			Characteristic[] characteristicsPlayerCard = topCards.getCards().get(playerChooseAttribute).getCharacteristics();
			for(int i = 0; i< characteristicsPlayerCard.length; i++) {
				if(characteristicsPlayerCard[i].getValue() > chosenAttribute) {
				chosenAttribute = i;
				}
			}
		}
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
				System.out.println("Player " + getRoundWinner() + " won! and their card was:");	
				}
			System.out.println(topCards.getCards().get(getRoundWinner()));
		}
		// step 6 - if draw: transfer all cards to the communal pile
		// winner: transfer all cards from round to back of winners cards 
		// also if winner and there is cards in the com pile, add them to back and empty com pile
		if(draw) {
			Deck.transferHand(topCards, communalPile);
			System.out.println("there is " + communalPile.getCards().size() + " cards in the communal pile");
		}else {
			Deck.transferHand(topCards, players[getRoundWinner()].getDeck());
			Deck.transferHand(communalPile, players[getRoundWinner()].getDeck());
			players[getRoundWinner()].roundWon();
		}
		// step 7 - if winner the game will end and show stats of that game but not coded yet and offer main menu to player 
		// will also check if any player has 0 cards and eliminate them
		// then offer human player to proceed to next round (not coded yet also) - commented below what should happen
		boolean gameEnded = false;
		int gameWinner = -1;
		for(int i = 0; i < players.length; i++) {
			if(players[i].getDeck().getCards().size() == noOfCards-communalPile.getCards().size()) {
				gameEnded = true;
				gameWinner = i;
			}else if(players[i].getDeck().getCards().size() == 0) {
				eliminated[i] = true;
			}
		}
		if(gameEnded) {
			System.out.println(players[gameWinner].getName());
			for(int i = 0; i<players.length; i++) {
				System.out.println(players[i].getName() + " and they won " + players[i].getNoRoundsWon() + " rounds");
			}
			int choice = TopTrumpsCLIApplication.menu("Go back to main menu");
			if(choice==0) {
				System.exit(0);
			}else if(choice==1) {
				return;
			}
		}else {
			if(draw) {
				int nextPlayer = 0;
				for(int i=1;i<players.length;i++) {
					if(!eliminated[(playerChooseAttribute+i)%players.length]) {
						nextPlayer = (playerChooseAttribute+i)%players.length;
					}
				}
				round(nextPlayer, communalPile);
			}else {
			round(getRoundWinner(), communalPile);
			}
		}
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