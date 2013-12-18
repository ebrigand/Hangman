Hangman
=========================================================================================================================================================
Needs:

The challenge is to build a simple version of a 'hangman' game as a web-app (look & feel is not important).

The app should use jquery and ajax queries for interaction.

The app should keep the current game state persistent across server and browser re-starts.

The app should be built with 'ant' and produce a war file that can be deployed in a tomcat.

The app should have a 'management' page that shows a summary of the state of all games that are currently being played.

=========================================================================================================================================================
Project Details:

I developed this project between 10 hours and 12 hours

Two servlets are available :
- hangman" the web page to play
- "manageHangman" the web page to print all the current games for all players
 
- Servlet "hangman"
 
 If the web app is installed on a local web server, the URL is formed like this:
 http://localhost:8080/hangman?playerName=ebrigand where playerName is a parameter to distinguish the different accounts
 
The page is initialized with the current state of the game, or a new game if no game exists for the playerName.
A parameter, 'restart' is used when the game is finished (when the player won or lost the game).

The player can click on different letters on the web page, a click generate an Ajax request, 
the player name and the letter is sent to the service and is used to play the next move, then, the state 
of the model (he class Hangman) is updated and converted into a JSON object and is written in the response.
 
- Servlet "manageHangman"

 If the web app is installed on a local web server, the URL is formed like this:
The web page is  http://localhost:8080/manageHangman

The page displays in a HTML table all the existing games of players.

=========================================================================================================================================================
Technologies used:

- Servlet 3
- JQuery front-end (with JSON AJAX requests)
- Twitter Bootstrap
