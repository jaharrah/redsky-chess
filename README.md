RedSky Chess Project
====================

Overview
--------

This project is to demonstrate aptitude in developing a small chess web service implementation.  I have implemented a few basic REST web service calls to create, retrieve, and move a chess game, as well as unit tests around the logic.

The code is hosted in a Spring Boot implementation, and is built using Maven.


UPDATE - Followup
---------

To whom it may concern.  I'm assuming anyone reading this right now is attempting to complete Redsky's chess interview project.  For the record, I implemented this while working another job.  I had, what I thought, was a very good phone screen, and was looking forward to knocking this out of the park.

I received the project requirements on a Wednesday evening.  I worked a few hours on it Thursday/Friday evening after work and family duties were completed, andthen belted out fifteen hours plus over the weekend, to submit it Sunday evening.  Note there was no definition from the recruiters or Redsky as to any time requirements.

Doing another final readthrough of the requirements spec before I submitted this Sunday evening, I noted that near the bottom, they also mentioned logic around pieces being in check, that I must have missed in the first runthrough.  Adding that logic wouldn't have been too hard, just would have taken some more time to add, and since I had to start working Monday morning, would be another few days to get it out, and since it didn't seem like that big of a deal, I let it go.

Come a full week later (Friday morning), I finally get a call back from the recruiter, saying how disappointed Redsky was with what I submitted, but I couldn't get a straight answer as to why.  I wrote the recruiters an email the next day, asking for an answer:

"Hello {recruiters},

Any chance of getting a response to this?  I am still a little angry that I spent a whole weekend crafting this solution, to get little to no response/feedback as to what he could possibly have been disappointed in.  

This is a direct quote from the documentation they provided:

'IMPORTANT: It is not expected/required that you will develop every single detail associated with the exercise.  The more robust the end product is the better, however, we are mostly looking for a reasonable effort to meet the guidelines of the exercise, and demonstrated ability to address functionality issues.  You are not expected to produce something requiring expert knowledge of the subject area.  This is not a make or break factor in the overall interview process.'

The first sentence made me assume that they weren't caring about a complete solution, so that doesn't seem to be what the issue was.  I got the assignment late Wednesday, and submitted it late Sunday.  There was nothing in the documentation about expectations on project submission, so I was trying to be as thorough as possible, in the free time that I had available between when I got the assignment, and submission.  Not to mention that I still have my other priorities to deal with, so I can't imagine why the amount of time was a factor.  On a side note, when was my submission actually sent to them?  I never got a response back after I sent the email Sunday evening.

I understand that they no longer want to talk to me, but I think I'm at least due some form of reasoning as to why that makes sense, since I spent over 16 hours putting that entire project together.  If they wanted it in a shorter amount of time, I would have not bothered with complete unit tests, a clean documentation page, etc, but again, there was no indication as to expected submission time.  It's very disappointing that I was being very flexible for them, and then I get treated to that kind of response.  This is the exact reason why no one wants to do interview projects.

Maybe it's a case of whoever was actually looking at it saw all my unit tests, and didn't want me to come in and rock the boat?  In any case, I believe that I'm due a more detailed response as to why they didn't even want to talk to me again about it.

'This is not a make or break factor in the overall interview process.', indeed.

Thanks, and looking forward to a logical response,
Jon"

Of course, I have yet to hear anything back about it.  Rather disappointing that I spent a large amount of time implementing what I think is a fairly robust solution, setup Spring Boot, controller/service/models/validators, full unit test coverage, etc., with no real reason as to what they weren't happy about.

So, in summary, feel free to use whatever, and add logic around moving pieces when the King is in check.  And I guess don't take too long to submit it either.  Who knows.


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
