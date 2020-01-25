package model;

public class Game {
	
	private int id, draws, rounds, winner;
	private Player[] players;
	public Game(int id, int draws, int rounds, int winner, Player[] players) {
		super();
		this.id = id;
		this.draws = draws;
		this.rounds = rounds;
		this.winner = winner;
		this.players = players;
	}
	
	// increments the number of draws 
	public void noOfDraws() {
		this.draws++;
	}
	
	// increments the number of rounds
	public void noOfRounds() {
		this.rounds++;
	}
	
	@Override
	public String toString() {
		return "The game ID is " + id + ", the number of draws is" + draws + ", the number of rounds is " + rounds + ", and the winner is " + winner;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDraws() {
		return draws;
	}
	public void setDraws(int draws) {
		this.draws = draws;
	}
	public int getRounds() {
		return rounds;
	}
	public void setRounds(int rounds) {
		this.rounds = rounds;
	}
	public int getWinner() {
		return winner;
	}
	public void setWinner(int winner) {
		this.winner = winner;
	}
	public Player[] getPlayers() {
		return players;
	}
	public void setPlayers(Player[] players) {
		this.players = players;
	}

}
