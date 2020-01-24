package model;

public class Player {
	
	private int id;
	private String name;
	private Deck deck;
	public Player(int id, String name, Deck deck) {
		super();
		this.id = id;
		this.name = name;
		this.deck = deck;
	}
	
	@Override
	public String toString() {
		return "The Players ID is " + id + ", and there name is " + name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Deck getDeck() {
		return deck;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
	}

}
