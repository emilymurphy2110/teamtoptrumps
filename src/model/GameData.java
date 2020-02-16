package model;

public class GameData {
	
	private int draws, rounds, winner;
	
	
	// increments the number of draws 
	public void incrementDraws() {
		this.draws++;
	}
	
	// increments the number of rounds
	public void incrementRoundCounter() {
		this.rounds++;
	}
	
	@Override
	public String toString() {
		return "draws: " + draws + ", rounds: " + rounds + ", winner: " + winner;
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

}
