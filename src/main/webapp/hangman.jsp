<!DOCTYPE HTML>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <meta charset="utf-8">
    <title>Hangman Game</title>
    <script src="js/jquery.1.9.1.min.js"></script>
    
    <!-- bootstrap just to have good looking page -->
    <link href="bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet" />
    
    <!-- we code these -->
    <script src="js/hangmanfunctions.js"></script>
  </head>
  
  <body>
    <div style="margin:10px;">
    	<h1 style="text-align:center">Hangman Game<br></h1> 
    	<h4 >Welcome ${hangmanView.playerName}</h4>
        <div id="notificationId">
          <c:if test="${hangmanView.isPlayerWonTheGame}">
            <p class="text-success">You Won the game</p>
          </c:if>
          <c:if test="${hangmanView.isPlayerLostTheGame}">
            <p class="text-error">You lost the game, the word is: ${hangmanView.hangman.secretWord} </p>
          </c:if>
          <c:if test="${hangmanView.isPlayerWonTheGame or hangmanView.isPlayerLostTheGame}">
            <button class="btn btn-small btn-primary" type="button" onclick="restart('${hangmanView.playerName}')">Restart</button>
            <br /><br />
          </c:if>
        </div>
    	<div>
    		<div>
                <p>
                  <span class="label label-info">Remaining moves: <span id="remainingMovesId">${hangmanView.hangman.remainingMoves}</span></span>
                </p>
            </div>
            <div>
                <p>
                  <span class="label label-info">Word to find: 
                  <b>
                    <span id="currentWordLetterId">
                      <c:forEach var="currentWordLetter" items="${hangmanView.hangman.currentWordLetters}">
                        ${currentWordLetter}
                      </c:forEach>  
                    </span>
                  </b>
                  </span>
                </p>
    		</div>
    		<br/>
    		<p>
              <c:forEach var="playedLetter" items="${playedLetters}">
                <c:if test="${not playedLetter.isAlreadyPlayed}">
                    <button class="btn btn-small btn-primary" type="button" onclick="sendLetterAjax(this, '${hangmanView.playerName}','${playedLetter.letter}')">${playedLetter.letter}</button>
                </c:if>
                <c:if test="${playedLetter.isAlreadyPlayed}">
                    <button class="btn btn-small btn-warning" disabled="disabled" type="button" >${playedLetter.letter}</button>
                </c:if>
              </c:forEach>
    		</p>
    	</div>
    </div>	
  </body> 
</html>
