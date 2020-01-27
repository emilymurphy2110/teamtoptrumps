package model;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	
	private static ArrayList<Card> cards = new ArrayList<Card>();

	

	@Override
	public String toString() {
		return "Within the Deck, the cards are " + cards;
	}
	
	public static void shuffleDeck(Deck deck) {
		ArrayList<Card> shuffled = new ArrayList<>(cards);
		Collections.shuffle(shuffled);
	}
	
	public List<Card> unshuffledDeck() {
		return cards;
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
