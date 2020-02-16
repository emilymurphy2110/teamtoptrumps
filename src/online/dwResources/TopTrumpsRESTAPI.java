package online.dwResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import commandline.TopTrumpsCLIApplication;
import core.TopTrumps;
import database.DatabaseLogic;
import model.Card;
import model.Deck;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controlled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	
	/**
	 * Contructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {



//		TopTrumps.setUpGame(4);

	}
	
	// ----------------------------------------------------
	// Add relevant API methods here
	// ----------------------------------------------------
	
	@GET
	@Path("/helloJSONList")
	/**
	 * Here is an example of a simple REST get request that returns a String.
	 * We also illustrate here how we can convert Java objects to JSON strings.
	 * @return - List of words as JSON
	 * @throws IOException
	 */
	public String helloJSONList() throws IOException {
		
		List<String> listOfWords = new ArrayList<String>();
		listOfWords.add("Hello");
		listOfWords.add("World!");
		
		// We can turn arbatory Java objects directly into JSON strings using
		// Jackson seralization, assuming that the Java objects are not too complex.
		String listAsJSONString = oWriter.writeValueAsString(listOfWords);
		
		return listAsJSONString;
	}
	
	@GET
	@Path("/jettyserver")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String helloWord(@QueryParam("Word") String Word) throws IOException {
		return "hello this is a string" + Word;
	}
	// APIs for the GameScreen
	// new API for new game which calls the setUpGame method
	@GET
	@Path("/newGame")
	public void newGame() throws IOException {
		TopTrumps.setUpGame(5, "Player");
	}
	
	@GET
	@Path("/startingPlayer")
	public int getStartingPlayer() throws IOException {
		int startingPlayer = TopTrumps.getStartingPlayer();
		return startingPlayer;
	}
	
	@GET
	@Path("/roundStage1")
	public void roundStage1() throws IOException {
		TopTrumps.roundStage1();
	}
	
	@GET
	@Path("/getPlayerCard")
	public Card getPlayerCard() throws IOException {
		return TopTrumps.topCards.getCards().get(0);
	}
	@GET
	@Path("/getAI1")
	public Card getAI1() throws IOException {
		return TopTrumps.topCards.getCards().get(1);
	}
	@GET
	@Path("/getAI2")
	public Card getAI2() throws IOException {
		return TopTrumps.topCards.getCards().get(2);
	}
	@GET
	@Path("/getAI3")
	public Card getAI3() throws IOException {
		return TopTrumps.topCards.getCards().get(3);
	}
	@GET
	@Path("/getAI4")
	public Card getAI4() throws IOException {
		return TopTrumps.topCards.getCards().get(4);
	}
	@GET
	@Path("/getRoundNumber")
	public int getRoundNumber() throws IOException {
		return TopTrumps.getRoundCounter();
	}

	@GET
	@Path("/toptrumpsgame" )
	public Card toptrumpsgame(@QueryParam("card") int Card) throws IOException {
		return TopTrumps.loadCards().getCards().get(Card);
	}
	
	// all APIs for the stats page
	@GET
	@Path("/numberOfGames" )
	public int overallGames() throws IOException {
		return DatabaseLogic.getOverallGames();
	}
	
	@GET
	@Path("/computerWins" )
	public int compWins() throws IOException {
		return DatabaseLogic.getCompWins();
	}
	
	@GET
	@Path("/humanWins" )
	public int humanWins() throws IOException {
		return DatabaseLogic.getPlayerWins();
	}
	
	@GET
	@Path("/averageDraws" )
	public int averageDraws() throws IOException {
		return DatabaseLogic.getAverageDraws();
	}
	
	@GET
	@Path("/longestGame" )
	public int longestGame() throws IOException {
		return DatabaseLogic.getMostRoundsPlayed();
	}
}