package com.hangman.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hangman.model.Hangman;
import com.hangman.service.HangmanService;
import com.hangman.service.HangmanServiceImpl;
import com.hangman.viewbean.HangmanView;

/**
 * doGet read all the existing games of players and store the data in a list of
 * {@link HangmanView}, the data is displayed for information in a table of the
 * JSP
 * 
 * @author ebrigand
 * 
 */
public class ManageHangmanServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HangmanService hangmanService = HangmanServiceImpl.getInstance();
    // Get all the existing games by player name
    Map<String, Hangman> gameByPlayer = hangmanService.getGameByPlayer();

    // Build the list of HangmanView
    List<HangmanView> hangmanViews = buildHangmanViews(hangmanService, gameByPlayer);

    request.setAttribute("hangmanViews", hangmanViews);

    // Set the JSP page name
    request.getRequestDispatcher("/manageHangman.jsp").forward(request, response);
  }

  /**
   * Build a list of {@link HangmanView} for all existing games
   * 
   * @param hangmanService
   * @param gameByPlayer
   * @return
   */
  private List<HangmanView> buildHangmanViews(HangmanService hangmanService, Map<String, Hangman> gameByPlayer) {
    List<HangmanView> hangmanViews = new ArrayList<HangmanView>();
    // build the
    for (Entry<String, Hangman> entry : gameByPlayer.entrySet()) {
      boolean isPlayerWonTheGame = hangmanService.isPlayerWonTheGame(entry.getKey());
      boolean isPlayerLostTheGame = hangmanService.isPlayerWonTheGame(entry.getKey());
      hangmanViews.add(new HangmanView(entry.getKey(), entry.getValue(), isPlayerWonTheGame, isPlayerLostTheGame, isPlayerWonTheGame || isPlayerLostTheGame));
    }
    return hangmanViews;
  }
}