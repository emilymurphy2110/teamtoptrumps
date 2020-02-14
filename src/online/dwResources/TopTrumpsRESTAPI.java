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
	
	// new API for new game which calls the setUpGame method
	@GET
	@Path("/newgame")
	public void newGame() throws IOException {
		TopTrumps.setUpGame(4);
		
	}
	 //new API for new game which calls the loadCards method
	@GET
	@Path("/newgame")
	public void loadCards() throws IOException {
		TopTrumps.loadCards();
	}
	
	 //new API for first round
	@GET
	@Path("/newgame")
	public void firstRound() throws IOException {
		int startingPlayer = TopTrumps.getStartingPlayer();
		Deck communalPile = TopTrumps.getCommunalPile();
		TopTrumps.round(startingPlayer, communalPile);
	}
	
	@GET
	@Path("/newgame")
	public int getStartingPlayer() throws IOException {
		int startingPlayer = TopTrumps.getStartingPlayer();
		return startingPlayer;
	}
	
	// API to return the winner of a round
	@GET
	@Path("/newgame")
	public String showWinner() throws IOException {
		int roundNumber = TopTrumps.getRoundCounter();
		int roundWinner = TopTrumps.getRoundWinner();
		if(roundWinner==0) {
			return "Round "+ roundNumber + ": You won this round";
		}
		else if(roundWinner==1) {
			return "Round "+ roundNumber + ": AI1 won this round";
		}
		else if(roundWinner==2) {
			return "Round "+ roundNumber + ": AI2 won this round";
		}
		else if(roundWinner==3) {
			return "Round "+ roundNumber + ": AI3 won this round";
		}
		else{
			return "Round "+ roundNumber + ": AI4 won this round";
		}
	}	
	
	//new API for new game which calls the round method
	@GET
	@Path("/newgame")
	public void newRound() throws IOException {
		int roundWinner = TopTrumps.getRoundWinner();
		Deck communalPile = TopTrumps.getCommunalPile();
		TopTrumps.round(roundWinner, communalPile);
	}

}
