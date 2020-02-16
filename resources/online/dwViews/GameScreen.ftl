<html>

	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>

    	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
		<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">
    	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
    	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
    	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

	</head>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
  		<!-- Navagation Bar-->
		<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
			<a href="http://localhost:7777/toptrumps/" class="navbar-brand">TopTrumps</a>
			<button class="navbar-toggler" data-toggle="collapse" data-target="#navbarMenu">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarMenu">				<ul class="navbar-nav">
					<li class="nav-item">
						<a href="http://localhost:7777/toptrumps/game/" class="nav-link">New Game</a>
					</li>
					<li class="nav-item">
						<a href="http://localhost:7777/toptrumps/stats/" class="nav-link">Statistics</a>
					</li>
				</ul>
			</div>
		</nav>

		<!-- Main Content -->
		<div class="container">
			<div class="row">
				<div class="alert alert-primary" id="actionInfo" role="alert">Round 1: Players have drawn their cards</div>
			</div>
			<div class="row">
				<div class="col">
					<div class="container mh-75">
						<div class="card" id="playerAction" style="width: 12rem;">
							<div class="card-body">
								<div>
									<h5 class="card-title" id="activePlayer" style="font-size: 12px">The Active Player is...</h5>
								</div>
								<div>
									<p id="selected" style="font-size: 12px">They selected... </p>
								</div>
							</div>
						<div class="dropdown">
 							<button class="btn btn-secondary dropdown-toggle" type="button" id="playerSelection" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="width: 12rem; font-size:10px">Next: Category Selection
 							</button>
 							<div class="dropdown-menu" aria-labelledby="playerSelection" style="font-size:10px">
   								<button class="dropdown-item" id="speed" type="button" style="font-size:10px">Select: Speed</button>
   								<button class="dropdown-item" id="cargo" type="button" style="font-size:10px">Select: Cargo</button>
    							<button class="dropdown-item" id="size" type="button" style="font-size:10px">Select: Size</button>
    							<button class="dropdown-item" id="range" type="button" style="font-size:10px">Select: Range</button>
    							<button class="dropdown-item" id="firepower" type="button" style="font-size:10px">Select: Firepower</button>
 							</div>
 						</div>
 						</div>
						<div>
							<button type="button" id="aiSelection" style="width: 12rem; font-size:10px" class="btn btn-success">Next: Category Selection</button>
						</div>
						<div>
							<button type="button" id="newRound" style="width: 12rem; font-size:10px" onclick="newRound() "class="btn btn-success">New Round</button>
						</div>
						<div>
							<button type="button" id="showWinner" style="width: 12rem; font-size:10px" onclick="showWinner()" class="btn btn-success">Show Winner</button>
						</div>
					</div>
				</div>
				<div class="col">
					<div class="container mh-75">
						<div class="card mh-75" id="human" style="width: 12rem;">
						  <img src="..." class="card-img-top" alt="...">
					 	  <div class="card-body">
					   	    <h5 class="card-title" style="font-size:14px">Card title</h5>
					  	  </div>
					 	  <ul class="list-group list-group-flush">
					    	<li class="list-group-item" style="font-size:10px">Speed: </li>
					    	<li class="list-group-item" style="font-size:10px">Cargo: </li>
					   		<li class="list-group-item" style="font-size:10px">Size: </li>
					   		<li class="list-group-item"  style="font-size:10px">Range: </li>
					    	<li class="list-group-item" style="font-size:10px">Firepower: </li>
					  	  </ul>
						</div>
					</div>
				</div>
				<div class="col">
					<div class="container mh-75">
						<div class="card mh-75" id="ai1" style="width: 12rem;">
					  	  <img src="..." class="card-img-top" alt="...">
					  	  <div class="card-body">
					   		<h5 class="card-title" style="font-size:14px">Card title</h5>
					 	  </div>
					 	  <ul class="list-group list-group-flush">
					   		<li class="list-group-item" style="font-size:10px">Speed: </li>
					    	<li class="list-group-item" style="font-size:10px">Cargo: </li>
					   		<li class="list-group-item" style="font-size:10px">Size: </li>
					    	<li class="list-group-item" style="font-size:10px">Range: </li>
					    	<li class="list-group-item" style="font-size:10px">Firepower: </li>
					  	  </ul>
						</div>
					</div>
				</div>
				<div class="col">
					<div class="card" id="ai2" style="width: 12rem;">
					  <img src="..." class="card-img-top" alt="...">
					  <div class="card-body">
					    <h5 class="card-title" style="font-size:14px">Card title</h5>
					  </div>
					  <ul class="list-group list-group-flush">
					    <li class="list-group-item" style="font-size:10px">Speed: </li>
					    <li class="list-group-item" style="font-size:10px">Cargo: </li>
					    <li class="list-group-item" style="font-size:10px">Size: </li>
					    <li class="list-group-item" style="font-size:10px">Range: </li>
					    <li class="list-group-item" style="font-size:10px">Firepower: </li>
					  </ul>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col">
				</div>
				<div class="col">
				</div>
				<div class="col">
					<div class="card" id="ai3" style="width: 12rem;">
					  <img src="..." class="card-img-top" alt="...">
					  <div class="card-body">
					    <h5 class="card-title" style="font-size:14px">Card title</h5>
					  </div>
					  <ul class="list-group list-group-flush">
					    <li class="list-group-item" style="font-size:10px">Speed: </li>
					    <li class="list-group-item" style="font-size:10px">Cargo: </li>
					    <li class="list-group-item" style="font-size:10px">Size: </li>
					    <li class="list-group-item" style="font-size:10px">Range: </li>
					    <li class="list-group-item" style="font-size:10px">Firepower: </li>
					  </ul>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="card" id="ai4" style="width: 12rem;">
					  <img src="..." class="card-img-top" alt="...">
					  <div class="card-body">
					    <h5 class="card-title" style="font-size:14px">Card title</h5>
					  </div>
					  <ul class="list-group list-group-flush">
					    <li class="list-group-item" style="font-size:10px">Speed: </li>
					    <li class="list-group-item" style="font-size:10px">Cargo: </li>
					    <li class="list-group-item" style="font-size:10px">Size: </li>
					    <li class="list-group-item" style="font-size:10px">Range: </li>
					    <li class="list-group-item" style="font-size:10px">Firepower: </li>
					  </ul>
					</div>
				</div>
			</div>
		</div>

		<script type="text/javascript">
			$(document).ready(function(){
				$("#selected").toggle();
				$("#showWinner").toggle();
				$("#newRound").toggle();
				$("#ai1").toggle();
				$("#ai2").toggle();
				$("#ai3").toggle();
				$("#ai4").toggle();
			});
		</script>

		<script type="text/javascript">
			$("#aiSelection").click(function(){
				$("#selected").toggle()
				$("#ai1").toggle();
				$("#ai2").toggle();
				$("#ai3").toggle();
				$("#ai4").toggle();
				$("#aiSelection").toggle();
				$("#showWinner").toggle();
			});
		</script>

		<script type="text/javascript">
			$("#speed").click(function(){
				$("#selected").toggle()
				$("#ai1").toggle();
				$("#ai2").toggle();
				$("#ai3").toggle();
				$("#ai4").toggle();
				$("#playerSelection").toggle();
				$("#showWinner").toggle();
			});
		</script>

		<script type="text/javascript">
			$("#cargo").click(function(){
				$("#selected").toggle()
				$("#ai1").toggle();
				$("#ai2").toggle();
				$("#ai3").toggle();
				$("#ai4").toggle();
				$("#playerSelection").toggle();
				$("#showWinner").toggle();
			});
		</script>

		<script type="text/javascript">
			$("#size").click(function(){
				$("#selected").toggle()
				$("#ai1").toggle();
				$("#ai2").toggle();
				$("#ai3").toggle();
				$("#ai4").toggle();
				$("#playerSelection").toggle();
				$("#showWinner").toggle();
			});
		</script>

		<script type="text/javascript">
			$("#range").click(function(){
				$("#selected").toggle()
				$("#ai1").toggle();
				$("#ai2").toggle();
				$("#ai3").toggle();
				$("#ai4").toggle();
				$("#playerSelection").toggle();
				$("#showWinner").toggle();
			});
		</script>

		<script type="text/javascript">
			$("#firepower").click(function(){
				$("#selected").toggle()
				$("#ai1").toggle();
				$("#ai2").toggle();
				$("#ai3").toggle();
				$("#ai4").toggle();
				$("#playerSelection").toggle();
				$("#showWinner").toggle();
			});
		</script>

		<script>
			$("#showWinner").click(function(){
				$("#activePlayer").toggle();
				$("#selected").toggle()
				$("#human").toggle();;
				$("#ai1").toggle();
				$("#ai2").toggle();
				$("#ai3").toggle();
				$("#ai4").toggle();
				$("#aiSelection").toggle();
				$("#showWinner").toggle();
				$("#newRound").toggle();
			});
		</script>

		<script>
			$("#newRound").click(function(){
				$("#activePlayer").toggle();
				$("#selected").toggle()
				$("#human").toggle();;
				$("#aiSelection").toggle();
				$("#showWinner").toggle();
			});
		</script>




		<script type="text/javascript">
		<!-- Here are examples of how to call REST API Methods -->


			// This gives the player chosen attribute: Speed
			function speed(){

			}

			// This gives the player chosen attribute: Cargo
			function cargo(){

			}

			// This gives the player chosen attribute: Size
			function size(){

			}

			// This gives the player chosen attribute: Range
			function range(){

			}

			// This gives the player chosen attribute: Firepower
			function firepower(){

			}

			// This is the button for when the ai is making a category selection
			function aiSelection(){
				$(#ai1).toggle();
				$(#ai2).toggle();
				$(#ai3).toggle();
				$(#ai4).toggle();
			}

			// Displays winner, shows new round button and hides everything else
			function showWinner(){
				$(#showWinner).hide();
				$(#human).hide();
				$(#ai1).hide();
				$(#ai2).hide();
				$(#ai3).hide();
				$(#ai4).hide();
				$(#playerAction).hide();
				$(#newRound).show();

				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/showWinner");
				if (!xhr) {
  					alert("CORS not supported");
				}
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					document.getElementbyID("actionInfo").innerHTML = responseText; // makes actionInfo display response
				};
				xhr.send();
			}



		</script>

		<script>

			// Method that is called on page load
			function initalize() {
				newGame();
				roundStage1();
				getPlayerCard();
				getRoundNumber();
				console.log("hello");

			}

			function newGame() {

				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/newGame"); // Request type and URL+parameters

				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}
				xhr.onload = function(e) {
					roundStage1();
				}

				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}

			function startingPlayer(){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/startingPlayer"); // Request type and URL+parameters
				if (!xhr) {
  					alert("CORS not supported");
				}
				xhr.onload = function(e) {
 					var startingPlayer = xhr.response; // the text of the response
					if(startingPlayer == 0){
						$(document).ready(function(){

							$("#aiSelection").toggle();
						});
					}else{
						$(document).ready(function(){
							$("#playerSelection").toggle();
						});
					}
				}
				xhr.send();
			}

			function getPlayerCard(elementId) {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getPlayerCard");
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					var value = JSON.parse(responseText);
					var obj = document.getElementById('human').getElementsByClassName('card-title')[0];
					// var c1 = document.getElementById(
<<<<<<< HEAD
<<<<<<< HEAD
					obj[0] = document.getElementById('c1');
=======
>>>>>>> Emily
=======
>>>>>>> Emily
					obj.innerHTML=value.name;
					c1.innerText = value.name;
					getRoundNumber();
				};
				xhr.send();
			}

			function getRoundNumber() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getRoundNumber");
				xhr.onload = function(e) {
 				var responseText = xhr.response; // the text of the response
 				var value = JSON.parse(responseText);
				var obj = document.getElementById('actionInfo');
				obj.innerHTML="Round: " + value;
				};
				xhr.send();
			}
			function roundStage1() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/roundStage1");
				xhr.onload = function(e) {
					getPlayerCard();
				}
				xhr.send();
			}


			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------

			// This is a reusable method for creating a CORS request. Do not edit this.
			function createCORSRequest(method, url) {
  				var xhr = new XMLHttpRequest();
  				if ("withCredentials" in xhr) {

    				// Check if the XMLHttpRequest object has a "withCredentials" property.
    				// "withCredentials" only exists on XMLHTTPRequest2 objects.
    				xhr.open(method, url, true);

  				} else if (typeof XDomainRequest != "undefined") {

    				// Otherwise, check if XDomainRequest.
    				// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
    				xhr = new XDomainRequest();
    				xhr.open(method, url);

 				 } else {

    				// Otherwise, CORS is not supported by the browser.
    				xhr = null;

  				 }
  				 return xhr;
			}
			</script>

		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

	</body>

</html>
