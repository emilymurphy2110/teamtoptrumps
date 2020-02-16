package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import model.GameData;
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

	public static void initiateDatabase() {
		final String username = "TomTrumps";
		final String password = "TomTrumps";
		final String database = "jdbc:postgresql://52.24.215.108:5432/TomTrumps";

		try {

			Class.forName("org.postgresql.Driver");

			c = DriverManager.getConnection(database, username, password);

		} catch (Exception e) {
			e.printStackTrace();
			
			System.err.println(e.getClass().getName() + ": " + e.getMessage());

			System.exit(0);
			
		} 
		System.out.println("Database is online and available");
	}
	
	public static void disconnectDatabase() {
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
<<<<<<< HEAD
	//connect to database method
	
	//disconnect from database
	
	

	
=======

>>>>>>> 608267605268274ff9e55f45e02327cd030ca39d
	// need to use in model class to set values to the variables after the game, which are then used to insert data to the database
	public static void insertRecord(GameData game) {
		try {
			stmt = c.createStatement();
			// insert game stats after game played into the database
			stmt.executeUpdate("INSERT INTO TomTrumps2 (NumberOfDraws, Winner, NumberOfRounds)" 
					+ "VALUES (" + (game.getDraws() + ", " + game.getWinner() + ", " + game.getRounds() + ")"));
			
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Insert failed");
			e.printStackTrace();
		}
	}
	
	
	
	public static int getOverallGames() {
		String getOverallGames = "SELECT GameID FROM TomTrumps2 ORDER BY GameID DESC LIMIT 1";
		int overallGames = 0;
		
		try {
			stmt = c.createStatement();
			ResultSet stats = stmt.executeQuery(getOverallGames);
			while (stats.next()) {
				overallGames = stats.getInt("GameID");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return overallGames;
		}
	
	public static int getCompWins() {
		Statement stmt = null;
		String getCompWins = "SELECT COUNT (Winner) as ComputerWins FROM TomTrumps2 WHERE NOT (Winner = 0)";
		int compWins = 0;
		
		try {
			stmt = c.createStatement();
			ResultSet stats = stmt.executeQuery(getCompWins);
			while (stats.next()) {
				compWins = stats.getInt("ComputerWins");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return compWins;
	}
	
	public static int getPlayerWins() {
		Statement stmt = null;
		String getPlayerWins = "SELECT COUNT (Winner) as PlayerWins FROM TomTrumps2 WHERE (Winner = 0)";
		int playerWins = 0;
		
		try {
			stmt = c.createStatement();
			ResultSet stats = stmt.executeQuery(getPlayerWins);
			while (stats.next()) {
				playerWins = stats.getInt("PlayerWins");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return playerWins;
	}
	
	public static int getAverageDraws() {
		Statement stmt = null;
		String getAverageDraws = "SELECT AVG(NumberOfDraws) AS Draws  FROM TomTrumps2";
		int averageDraws = 0;
		
		try {
			stmt = c.createStatement();
			ResultSet stats = stmt.executeQuery(getAverageDraws);
			while (stats.next()) {
				averageDraws = stats.getInt("Draws");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return averageDraws;
	}

	public static int getMostRoundsPlayed() {
		Statement stmt = null;
		String getMostRoundsPlayed = "SELECT MAX (NumberOfRounds) as Rounds FROM TomTrumps2";
		int mostRoundsPlayed = 0;
		
		try {
			stmt = c.createStatement();
			ResultSet stats = stmt.executeQuery(getMostRoundsPlayed);
			while (stats.next()) {
				mostRoundsPlayed = stats.getInt("Rounds");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return mostRoundsPlayed;
	}
	
	
	// returns the statistics from the database of all games played
	public static int [] getDatabaseStats() {
		int [] downloadDatabase = new int [5];
		
		try { 
			downloadDatabase[0] = (getOverallGames());
			downloadDatabase[1] = (getCompWins());
			downloadDatabase[2] = (getPlayerWins());
			downloadDatabase[3] = (getAverageDraws());
			downloadDatabase[4] = (getMostRoundsPlayed());
		}catch(Exception e) {
			System.out.println("Cannot get statistics from database.");	
		}
		
		
		return downloadDatabase;
		
	}
}
