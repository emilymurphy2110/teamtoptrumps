package model;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	
//	public Deck() {
		
//	}

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
	public ArrayList<Card> unshuffledDeck() {
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
		for (int i = 0; i < noOfCards; i++) {
			if(hands[i%numHands] == null) {
				hands[i%numHands] = new Deck();
			}
			try {
				hands[i%numHands].addCard(cards.get(i));
			}catch (Exception e) {
				System.out.println("Error");
			}
		}
		return hands;
	}

	public static void transferHand(Deck source, Deck target) {
		while(!source.getCards().isEmpty()) {
			if(source.getCards().get(0) == null) {
				source.removeCard(0);
			}else {
				target.addCard(source.removeCard(0));
			}
		}
	}
	
	public ArrayList<Card> getCards() {
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

	// add the cards to the back of players hand
	public void addCardToBack(Card card) {
		ArrayList<Card> newCards = new ArrayList<Card>();
		newCards.add(card);
		for(int i=0; i<cards.size(); i++) {
			newCards.add(cards.get(i));
		}
		cards = newCards;
	}
	
	public Card removeCard(int i) {
		Card temp = this.cards.get(i);
		this.cards.remove(i);
		return temp;
	}
	
	

}
