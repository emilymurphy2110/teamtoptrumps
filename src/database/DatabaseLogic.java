package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import model.Game;
import model.Player;



public class DatabaseLogic {
	
	private static Connection c = null;
	private static Statement stmt = null;

	/**
	 * 
	 * Main Method - gets the database username and password and then runs a
	 * connection test
	 * 
	 * @param args
	 * 
	 */

	public static void main(String args[]) {
//		final String username = "TomTrumps";
//		final String password = "TomTrumps";
//		final String database = "jdbc:postgresql://52.24.215.108:5432/TomTrumps";

		try {

			Class.forName("org.postgresql.Driver");

			c = DriverManager.getConnection("jdbc:postgresql://52.24.215.108:5432/TomTrumps", "TomTrumps", "TomTrumps");

		} catch (Exception e) {
			e.printStackTrace();
			
			System.err.println(e.getClass().getName() + ": " + e.getMessage());

			System.exit(0);
			
		} finally {
			try {
				// close connection
				if (stmt != null) {
					stmt.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Database is online and available");
	}
	//connect to database method
	
	//disconnect from database
	
	
// write game stats
//	public void writeGameStats(Game gameStats) {
//		Player[] players = gameStats.getPlayers();
//		int winner = gameStats.getWinner();
//		
//		int gameID = getOverallGames() + 1;
//		int noOfDraws = gameStats.getDraws();
//		int noOfRounds = gameStats.getRounds();
//		int gameWinner = 1;
//		
//	}
	
	// need to use in model class to set values to the variables after the game, which are then used to insert data to the database
	public void insertRecord(int gameID, int noOfDraws, String winner, int noOfRounds) {
		try {
			stmt = c.createStatement();
			// insert game stats after game played into the database
			stmt.executeUpdate("INSERT INTO GameStats " 
					+ "VALUES (" + gameID + ", " + noOfDraws + ", " + winner + ", " + noOfRounds + ")");
			
			stmt.close();
			c.close();
			c=null;
		} catch (SQLException e) {
			System.out.println("Insert failed");
			e.printStackTrace();
		}
		
		String insertRecord = "INSERT INTO GameStats (GameID, NumberOfDraws, Winner, NumberOfRounds) "
				+ "VALUES ()";
	}
	
	
	
	public int getOverallGames() {
		String getOverallGames = "SELECT GameID FROM GameStats ORDER BY GameID DESC LIMIT 1";
		int overallGames = 0;
		
		try {
			stmt = c.createStatement();
			ResultSet stats = stmt.executeQuery(getOverallGames);
			while (stats.next()) {
				overallGames = stats.getInt("GameID");
			}
		}
		catch (Exception e) {
			System.exit(0);
		}
		return overallGames;
		}
	
	public int getCompWins() {
		Statement stmt = null;
		String getCompWins = "SELECT COUNT (Winner) FROM GameStats WHERE NOT (Winner = 0)";
		int compWins = 0;
		
		try {
			stmt = c.createStatement();
			ResultSet stats = stmt.executeQuery(getCompWins);
			while (stats.next()) {
				compWins = stats.getInt("GameID");
			}
		}
		catch (Exception e) {
			System.exit(0);
		}
		return compWins;
	}
	
	public int getPlayerWins() {
		Statement stmt = null;
		String getPlayerWins = "SELECT COUNT (Winner) FROM GameStats WHERE (Winner = 0)";
		int playerWins = 0;
		
		try {
			stmt = c.createStatement();
			ResultSet stats = stmt.executeQuery(getPlayerWins);
			while (stats.next()) {
				playerWins = stats.getInt("GameID");
			}
		}
		catch (Exception e) {
			System.exit(0);
		}
		return playerWins;
	}
	
	public int getAverageDraws() {
		Statement stmt = null;
		String getAverageDraws = "SELECT CAST (AVG(NumberOfDraws) AS DECIMAL(20,2)) FROM GameStats";
		int averageDraws = 0;
		
		try {
			stmt = c.createStatement();
			ResultSet stats = stmt.executeQuery(getAverageDraws);
			while (stats.next()) {
				averageDraws = stats.getInt("GameID");
			}
		}
		catch (Exception e) {
			System.exit(0);
		}
		return averageDraws;
	}

	public int getMostRoundsPlayed() {
		Statement stmt = null;
		String getMostRoundsPlayed = "SELECT MAX (NumberOfRounds) FROM GameStats";
		int mostRoundsPlayed = 0;
		
		try {
			stmt = c.createStatement();
			ResultSet stats = stmt.executeQuery(getMostRoundsPlayed);
			while (stats.next()) {
				mostRoundsPlayed = stats.getInt("GameID");
			}
		}
		catch (Exception e) {
			System.exit(0);
		}
		return mostRoundsPlayed;
	}
	
	// insert new record
	
	// returns the statistics from the database of all games played
	public DatabaseDownload getDatabaseStats() {
		DatabaseDownload download = new DatabaseDownload();
		
		int overallGames = 0;
		int compWins = 0;
		int playerWins = 0;
		int averageDraws = 0;
		int mostRoundsPlayed = 0;
		
		try { 
			overallGames = getOverallGames();
			compWins = getCompWins();
			playerWins = getPlayerWins();
			averageDraws = getAverageDraws();
			mostRoundsPlayed = getMostRoundsPlayed();
		}catch(Exception e) {
			System.out.println("Cannot get statistics from database.");	
		}
		download.setOverallGames(overallGames);
		download.setCompWins(compWins);
		download.setPlayerWins(playerWins);
		download.setAverageDraws(averageDraws);
		download.setMostRoundsPlayed(mostRoundsPlayed);
		
		return download;
		
	}
}
