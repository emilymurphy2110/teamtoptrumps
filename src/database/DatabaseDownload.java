// Heather Boyle code - Having git issues, Emily commited.
package database;

public class DatabaseDownload {
	private int overallGames;
	private int compWins;
	private int playerWins;
	private int averageDraws;
	private int mostRoundsPlayed;
	
	// getters and setters
	public int getOverallGames() {
		return overallGames;
	}
	public void setOverallGames(int overallGames) {
		this.overallGames = overallGames;
	}
	public int getCompWins() {
		return compWins;
	}
	public void setCompWins(int compWins) {
		this.compWins = compWins;
	}
	public int getPlayerWins() {
		return playerWins;
	}
	public void setPlayerWins(int playerWins) {
		this.playerWins = playerWins;
	}
	public int getAverageDraws() {
		return averageDraws;
	}
	public void setAverageDraws(int averageDraws) {
		this.averageDraws = averageDraws;
	}
	public int getMostRoundsPlayed() {
		return mostRoundsPlayed;
	}
	public void setMostRoundsPlayed(int mostRoundsPlayed) {
		this.mostRoundsPlayed = mostRoundsPlayed;
	}
	
	@Override
	public String toString() {
		return "DatabaseDownload [overallGames=" + overallGames + ", compWins=" + compWins + ", playerWins="
				+ playerWins + ", averageDraws=" + averageDraws + ", mostRoundsPlayed=" + mostRoundsPlayed + "]";
	}
}
