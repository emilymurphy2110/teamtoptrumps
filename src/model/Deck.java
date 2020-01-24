package model;

import java.util.ArrayList;

public class Deck {
	
	private ArrayList<Card> cards = new ArrayList<Card>();

	public Deck(ArrayList cards) {
		super();
		this.cards = cards;
	}

	@Override
	public String toString() {
		return "Within the Deck, the cards are " + cards;
	}

	public ArrayList getCards() {
		return cards;
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