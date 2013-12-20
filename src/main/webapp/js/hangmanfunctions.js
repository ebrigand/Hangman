//Reload the page with a param to indicate that game need to be restarted
function restart(playerName) {
	document.location = '/hangman?playerName=' + playerName + '&restart=true';
}

//Send a letter and a playerName for a game move if the game is not finished.
//The data received from the request are in a JSON format
function sendLetterAjax(button, playerName, letter) {
	$.ajax({
		url: "hangman",
		type: 'POST',
		data: "playerName=" + playerName + "&letter=" + letter,
		dataType: 'json',
		success: function (hangmanView) {
			//If the game is finished, nothing is done
			if(!hangmanView.isFinished){
				//Update the remaining moves count
				$("#remainingMovesId").text(hangmanView.hangman.remainingMoves);
				var currentWordLetters = "";
				
				//Update the letters of the word to find
				$.each(hangmanView.hangman.currentWordLetters, function (key, value) {
					currentWordLetters += (value + " ");
				});
				$("#currentWordLetterId").text(currentWordLetters);
				
				//Disable the button and change the CSS for the color
				$(button).prop('disabled', true);
				$(button).prop('class', "btn btn-small btn-warning");
				
				//Notification if the player won the game or lost the game, in one of both case, a restart button is displayed
				var notification = "";
				if(hangmanView.isPlayerWonTheGame){
					notification = '<p class="text-success">You Won the game</p>';
				} else if(hangmanView.isPlayerLostTheGame){
					notification = '<p class="text-error">You lost the game, the word is: ' + hangmanView.hangman.secretWord  + '</p>';
				}
				if(hangmanView.isPlayerWonTheGame || hangmanView.isPlayerLostTheGame){
					notification += "<button class=\"btn btn-small btn-primary\" type=\"button\" onclick=\"restart(\'" + playerName + "\')\">Restart</button><br /><br />";
					$("#notificationId").html(notification);
				}
			}
        },
		error:function(data, status, error) {
			alert("status: " + status + " error:" + error);
		}
	});
}