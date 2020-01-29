package model;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

	private ArrayList<Card> cards = new ArrayList<Card>();

	@Override
	public String toString() {
		return "Within the Deck, the cards are " + cards;
	}

	public static ArrayList<Card> shuffleDeck(Deck deck) {
		ArrayList<Card> shuffled = new ArrayList<>(deck.getCards());
		Collections.shuffle(shuffled);
		return shuffled;
	}

	// methods to shuffle the deck
	public void shuffle() {
		ArrayList<Card> shuffled = new ArrayList<>(getCards());
		Collections.shuffle(shuffled);
		this.cards = shuffled;
	}

	// method for unshuffled deck
	public List<Card> unshuffledDeck() {
		return cards;
	}

	// method for splitting cards between number of players
	public Deck[] split(int numHands) {
		// initialised the number of cards and the size of the cards array
		int noOfCards = cards.size();
		// players hand is the number of cards divided by the number of players
		int handSize = noOfCards / numHands;
		// System.out.println(handSize);
		Deck[] hands = new Deck[numHands];
		// this double for loop adds the cards to the players hands
		for (int i = 0; i < hands.length; i++) {
			hands[i] = new Deck();
			for (int l = i * handSize; l < (i + 1) * handSize; l++) {
				hands[i].addCard(cards.get(l));
				// System.out.println("transferring card at " + l + " to hand " + i);
			}
		}
		return hands;
	}

	public ArrayList getCards() {
		return cards;
	}

	public void print() {
		System.out.println("Within the Deck, the cards are " + cards);
	}

	public void setCards(ArrayList cards) {
		this.cards = cards;
	}

	public void addCard(Card card) {
		this.cards.add(card);
	}

	public Card removeCard(int i) {
		Card temp = this.cards.get(i);
		this.cards.remove(i);
		return temp;
	}

}
