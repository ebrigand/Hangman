package com.hangman.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hangman.exception.GameException;
import com.hangman.model.Hangman;
import com.hangman.service.HangmanService;
import com.hangman.service.HangmanServiceImpl;
import com.hangman.utils.Letter;
import com.hangman.utils.WordGenerator;
import com.hangman.viewbean.HangmanView;
import com.hangman.viewbean.PlayedLetter;

/**
 * doGet is used to display the page hangman.jsp initialized with the current
 * state of the game, or a new game if no game exists for the player. The player
 * is defined by the 'playerName' set as a request parameter. The parameter
 * 'restart' doPost is used to handle the Ajax requests, a player name and a
 * letter is got from the JSP and is used to play the next move, the new state
 * of the model {@link Hangman} is convert into a JSON object and is written is
 * the response
 * 
 * @author ebrigand
 * 
 */
public class HangmanServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  /*
   * (non-Javadoc)
   * 
   * @see
   * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
   * javax.servlet.http.HttpServletResponse)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      // Check if the playerName as a request parameter exists
      String playerName = request.getParameter("playerName");
      if (StringUtils.isEmpty(playerName)) {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "please set the parameter playerName");
        return;
      }
      // Check if the playerName as a request parameter exists
      HangmanService hangmanService = HangmanServiceImpl.getInstance();
      String restart = request.getParameter("restart");
      if (StringUtils.isNotEmpty(restart) && Boolean.parseBoolean(restart)) {
        hangmanService.removeGame(playerName);
      }
      Hangman hangman = null;
      HangmanView hangmanView = null;

      // Create or retrieve the game for the user, and set the view bean for the
      // JSP
      if (hangmanService.isHangmanExists(playerName)) {
        hangman = hangmanService.getHangman(playerName);
        boolean isPlayerWonTheGame = hangmanService.isPlayerWonTheGame(playerName);
        boolean isPlayerLostTheGame = hangmanService.isPlayerLostTheGame(playerName);
        hangmanView = new HangmanView(playerName, hangman, isPlayerWonTheGame, isPlayerLostTheGame, isPlayerWonTheGame || isPlayerLostTheGame);
      } else {
        hangman = hangmanService.newGame(playerName, WordGenerator.generateWord());
        hangmanView = new HangmanView(playerName, hangman, false, false, false);
      }

      // Set the view in an attribute of the request
      request.setAttribute("hangmanView", hangmanView);

      // Build the view for the letters
      List<PlayedLetter> playedLetters = buildPlayedLetters(hangmanService, playerName);
      request.setAttribute("playedLetters", playedLetters);

      // Set the JSP page name
      request.getRequestDispatcher("/hangman.jsp").forward(request, response);
    } catch (GameException e) {
      response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getError());
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
   * , javax.servlet.http.HttpServletResponse)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      // Get received data from request
      BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
      if (br != null) {
        String result = br.readLine();

        // Data are in this form : playerName=xxx&letter=yyy, split is used to
        // get the xxx and yyy
        String[] splittedResult = result.split("&");
        String playerName = splittedResult[0].split("=")[1];
        Letter letter = Letter.getLetter((splittedResult[1].split("=")[1]).charAt(0));

        HangmanService hangmanService = HangmanServiceImpl.getInstance();

        // The boolean is used to indicate if the game is already finished when
        // the user click on a letter
        boolean isFinished = false;
        // If the game is not finished, the next move for the player is done
        if (!hangmanService.isPlayerWonTheGame(playerName) && !hangmanService.isPlayerLostTheGame(playerName)) {
          hangmanService.play(playerName, letter);
        } else {
          isFinished = true;
        }

        // Create a view bean with the actualized hangman model
        HangmanView hangmanView = new HangmanView(playerName, hangmanService.getHangman(playerName), hangmanService.isPlayerWonTheGame(playerName), hangmanService.isPlayerLostTheGame(playerName),
            isFinished);

        // Initialize the jackson mapper
        ObjectMapper mapper = new ObjectMapper();

        // Set response type to JSON
        response.setContentType("application/json");

        // Send HangmanView as JSON to client
        mapper.writeValue(response.getOutputStream(), hangmanView);
      }

    } catch (GameException e) {
      response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getError());
    }
  }

  /**
   * Build a list of {@link PlayedLetter} for each letter of {@link Letter} and
   * with a boolean associated to the letter and indicating if the letter has
   * already been played (by the player) or not
   * 
   * @param hangmanService
   * @param playerName
   * @return
   */
  private List<PlayedLetter> buildPlayedLetters(HangmanService hangmanService, String playerName) {
    List<PlayedLetter> activatedLetters = new ArrayList<PlayedLetter>();
    for (Letter letter : Letter.values()) {
      // The special letter '_' is not a playable letter
      if (letter != Letter._) {
        activatedLetters.add(new PlayedLetter(letter, hangmanService.isLetterAlreadyPlayed(playerName, letter)));
      }
    }
    return activatedLetters;
  }
}