package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

import com.mysql.jdbc.Statement;

/**
 * 
 * This is a simple test class that makes sure that we are able to connect to
 * the database
 * 
 * @author richardm
 *
 * 
 * 
 */

public class CreateTable {
	private static Connection c = null;
	private static java.sql.Statement stmt = null;
	private static final String CREATE_TABLE_SQL="CREATE TABLE TomTrumps2 ("
			+ "GameID SERIAL,"
			+ "NumberOfDraws INT NOT NULL,"
			+ "Winner INT NOT NULL,"
			+ "NumberOfRounds INT NOT NULL,"
			+ "PRIMARY KEY(GameID))";

	/**
	 * 
	 * Main Method - gets the database username and password and then runs a
	 * connection test
	 * 
	 * @param args
	 * 
	 */

	@SuppressWarnings("null")
	public static void main(String args[]) {




		try {

			Class.forName("org.postgresql.Driver");

			c = DriverManager

					.getConnection("jdbc:postgresql://52.24.215.108:5432/TomTrumps",

							"TomTrumps", "TomTrumps");
			stmt = c.createStatement();
			
			stmt.executeUpdate(CREATE_TABLE_SQL);
			System.out.println("table created");

			

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

		System.out.println("Database is online and available");

	}

}
}