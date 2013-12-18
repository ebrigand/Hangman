<!DOCTYPE HTML>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <meta charset="utf-8">
    <title>Manage Hangman Game</title>
    <script src="js/jquery.1.9.1.min.js"></script>
    
    <!-- bootstrap just to have good looking page -->
    <link href="bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet" />
  </head>
  
  <body>
    <div style="margin:10px;">
    	<h1 style="text-align:center">Manage Hangman Game<br></h1> 
        <br /><br />
        <table class="table">
          <tr>
            <th>Player</th>
            <th>Won the game?</th>
            <th>Lost the game?</th>
            <th>Remaining moves</th>
            <th>Word to find</th>
            <th>Secret word</th>
          </tr>
          <c:forEach var="hangmanView" items="${hangmanViews}">
          <tr>
            <td>
              <span class="label label-info">${hangmanView.playerName}</span>
            </td>
            <td>
              <span class="label label-info">${hangmanView.isPlayerWonTheGame}</span>
            </td>
            <td>
              <span class="label label-info">${hangmanView.isPlayerLostTheGame}</span>
            </td>
            <td>
              <span class="label label-info">${hangmanView.hangman.remainingMoves}</span>
            </td>
            <td>
              <span class="label label-info">
                <c:forEach var="currentWordLetter" items="${hangmanView.hangman.currentWordLetters}">
                    ${currentWordLetter}
                </c:forEach>  
              </span>
            </td>
            <td>
              <span class="label label-info">${hangmanView.hangman.secretWord}</span>
            </td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </body> 
</html>
