RedSky Chess Project
====================

Overview
--------

This project is to demonstrate aptitude in developing a small chess web service implementation.  I have implemented a few basic REST web service calls to create, retrieve, and move a chess game, as well as unit tests around the logic.

The code is hosted in a Spring Boot implementation, and is built using Maven.


Setup
---------

To get the project, you need to install git on your local machine, and run the command (in the directory that you want to have the repository downloaded to):

	git clone https://github.com/jaharrah/redsky-chess

The ouputted JAR from running Maven install is available in the /target directory, chess-1.0.0.jar.  Assuming you have Java installed you can call

	java -jar <directory the jar is located on your machine>/chess-1.0.0.jar

This will start up the Spring Boot server, and you will be able to call the appropriate web service calls (see Usage section below).

If you wish to build the project, you will need to have Maven 3 installed.  Use git to clone from https://github.com/jaharrah/redsky-chess, go into that directory, and then call

	mvn clean install

That will recreate the /target directory with updated output that has been cleaned and rebuild via Maven.

Usage
---------

When you start up the Spring Boot application, a new game will have started by default.  If you want to quit or restart a game, that would be done by calling

	POST http://localhost:8080/chess/quit
	POST http://localhost:8080/chess/reset

From a client that can make web service calls, like Postman.  That will put the board back into the original state.

To make a move on the board, that can be done by making a call like:

	POST http://localhost:8080/chess/move?col1=<a-f>&row1=<1-8>&col2=<a-f>&row2=<1-8>

I have the column indicators as a through f, and row indicators as 1 through 8.  If the coordinate parameters are not populated properly, or the move is not valid, the web service call will return a 400, with a response of why the movement did not happen.  Otherwise, the service will return a 200, with a response of "Done."

To show the current board status, you can make the call:

	GET http://localhost:8080/chess

This will show all of the current pieces still on the game board.  The JSON response will look like this:

{
  "turnColor": "WHITE",
  "pieces": [
    {
      "piece": {
        "type": "Rook",
        "color": "WHITE"
      },
      "position": "a1"
    },
    {
      "piece": {
        "type": "Knight",
        "color": "WHITE"
      },
      "position": "b1"
    },    
    ...
  ]
}  
