package core;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import commandline.TopTrumpsCLIApplication;
import model.Card;
import model.Characteristic;
import model.Deck;
import model.Player;
import online.TopTrumpsOnlineApplication;

public class TopTrumps {

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
		Player[] players = new Player[noOfPlayers];
		// loads the cards
		Deck cards = loadCards();
		// shuffles the cards
		cards.shuffle();
		// System.out.println(cards.getCards().size());
		// splits the cards between the number of players
		Deck[] hands = cards.split(noOfPlayers);
		for (int i = 0; i < noOfPlayers; i++) {
			players[i] = new Player(i, "", hands[i]);
			// System.out.println(players[i]);
		}
		// randomise starting player
		Random random = new Random();
		int startingPlayer = random.nextInt(noOfPlayers);
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

}
