package model;

public class Player {
	
	private int id;
	private String name;
	private Deck deck;
	private Card[] playerHand; // cards held by the player
	private int noRoundsWon;
	private boolean inGame;
	
	public Player(int id, Deck deck) {
		super();
		this.id = id;
		if(id ==0) {
			this.name = "Player";
		}else {
			this.name = "Computer " + id;
		}
		this.deck = deck;
	}
	
	@Override
	public String toString() {
		return "Player ID: " + id + ", players name: " + name + ", Players hand: " + deck;
	}
	
	// increments the number of rounds won
	public void roundWon() {
		noRoundsWon++;
	}
	
	// returns number of cards in the players hands
	public int getPlayersHand() {
		return this.playerHand.length;
	}
	
	// returns whether player is still in the game or not
	public boolean stillInGame() {
		return inGame;
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

	public int getNoRoundsWon() {
		return noRoundsWon;
	}

	public void setNoRoundsWon(int noRoundsWon) {
		this.noRoundsWon = noRoundsWon;
	}

	public Card[] getPlayerHand() {
		return playerHand;
	}

	public void setPlayerHand(Card[] playerHand) {
		this.playerHand = playerHand;
	}

	public boolean isInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}
	
	
	
	

}
