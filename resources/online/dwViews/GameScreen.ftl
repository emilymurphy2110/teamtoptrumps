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

	</head>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
  		<!-- Navagation Bar-->
		<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
			<a href="http://localhost:8080/toptrumps/" class="navbar-brand">TopTrumps</a>
			<button class="navbar-toggler" data-toggle="collapse" data-target="#navbarMenu">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarMenu">				<ul class="navbar-nav">
					<li class="nav-item">
						<a href="http://localhost:8080/toptrumps/game/" class="nav-link">New Game</a>
					</li>
					<li class="nav-item">							
						<a href="http://localhost:8080/toptrumps/stats/" class="nav-link">Statistics</a>
					</li>
				</ul>
			</div>
		</nav>

		<!-- Main Content -->
		<div class="container">
			<div class="row">
				<div class="alert alert-primary" id="actionIfo" role="alert">
					Text about round and what's happening
				</div>
			</div>
			<div class="row">
				<div class="col">
					<div class="card" style="width: 18rem;">
						<div class="card-body">
							<h5 class="card-title" id="activePlayer">The Active Player is...</h5>
							<p id="selected">They selected... </p>
						<div class="dropdown">
 							<button class="btn btn-secondary dropdown-toggle" type="button" id="charSelection" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Next: Category Selection
 							</button>
 							<div class="dropdown-menu" aria-labelledby="charSelection">
   								<button class="dropdown-item" id="speed" type="button">Select: Speed</button>
   								<button class="dropdown-item" id="cargo" type="button">Select: Cargo</button>
    							<button class="dropdown-item" id="size" type="button">Select: Size</button>
    							<button class="dropdown-item" id="range" type="button">Select: Range</button>
    							<button class="dropdown-item" id="firepower" type="button">Select: Firepower</button>
 							</div>
						</div>
						<button type="button" id="newRound" class="btn btn-success">New Round</button>
					</div>
				</div>
				<div class="col">
					<div class="card" id="human" style="width: 18rem;">
					  <img src="..." class="card-img-top" alt="...">
					  <div class="card-body">
					    <h5 class="card-title">Card title</h5>
					  </div>
					  <ul class="list-group list-group-flush">
					    <li class="list-group-item">Speed: </li>
					    <li class="list-group-item">Cargo: </li>
					    <li class="list-group-item">Size: </li>
					    <li class="list-group-item">Range: </li>
					    <li class="list-group-item">Firepower: </li>
					  </ul>
					</div>
				</div>
				<div class="col">
					<div class="card" id="ai1" style="width: 18rem;">
					  <img src="..." class="card-img-top" alt="...">
					  <div class="card-body">
					    <h5 class="card-title">Card title</h5>
					  </div>
					  <ul class="list-group list-group-flush">
					    <li class="list-group-item">Speed: </li>
					    <li class="list-group-item">Cargo: </li>
					    <li class="list-group-item">Size: </li>
					    <li class="list-group-item">Range: </li>
					    <li class="list-group-item">Firepower: </li>
					  </ul>
					</div>
				</div>
				<div class="col">
					<div class="card" id="ai2" style="width: 18rem;">
					  <img src="..." class="card-img-top" alt="...">
					  <div class="card-body">
					    <h5 class="card-title">Card title</h5>
					  </div>
					  <ul class="list-group list-group-flush">
					    <li class="list-group-item">Speed: </li>
					    <li class="list-group-item">Cargo: </li>
					    <li class="list-group-item">Size: </li>
					    <li class="list-group-item">Range: </li>
					    <li class="list-group-item">Firepower: </li>
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
					<div class="card" id="ai3" style="width: 18rem;">
					  <img src="..." class="card-img-top" alt="...">
					  <div class="card-body">
					    <h5 class="card-title">Card title</h5>
					  </div>
					  <ul class="list-group list-group-flush">
					    <li class="list-group-item">Speed: </li>
					    <li class="list-group-item">Cargo: </li>
					    <li class="list-group-item">Size: </li>
					    <li class="list-group-item">Range: </li>
					    <li class="list-group-item">Firepower: </li>
					  </ul>
					</div>
				</div>
				<div class="col">
					<div class="card" id="ai4" style="width: 18rem;">
					  <img src="..." class="card-img-top" alt="...">
					  <div class="card-body">
					    <h5 class="card-title">Card title</h5>
					  </div>
					  <ul class="list-group list-group-flush">
					    <li class="list-group-item">Speed: </li>
					    <li class="list-group-item">Cargo: </li>
					    <li class="list-group-item">Size: </li>
					    <li class="list-group-item">Range: </li>
					    <li class="list-group-item">Firepower: </li>
					  </ul>
					</div>
				</div>
			</div>
		</div>


	</body>

		</div>
		
		<!-- End of HTML -->
		
		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
			
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				
				function newGame() {
			
					// First create a CORS request, this is the message we are going to send (a get request in this case)
					var xhr = createCORSRequest('GET', "http://localhost:8080/toptrumps/newgame"); // Request type and URL+parameters
				
					// Message is not sent yet, but we can check that the browser supports CORS
					if (!xhr) {
  						alert("CORS not supported");
					}
				
					// We have done everything we need to prepare the CORS request, so send it
					xhr.send();		
				}

				function loadCards() {
			
					// First create a CORS request, this is the message we are going to send (a get request in this case)
					var xhr = createCORSRequest('GET', "http://localhost:8080/toptrumps/newgame"); // Request type and URL+parameters
				
					// Message is not sent yet, but we can check that the browser supports CORS
					if (!xhr) {
  						alert("CORS not supported");
					}
				
					// We have done everything we need to prepare the CORS request, so send it
					xhr.send();		
				}
				
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
		
		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">
		
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloJSONList() {
			
			

		</script>
		
		</body>
</html>