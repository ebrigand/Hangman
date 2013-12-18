package com.hangman.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.hangman.exception.GameException;
import com.hangman.model.Hangman;
import com.hangman.utils.Letter;

/**
 * Implementation of the service for the hangman game The service is a singleton
 * (non lazy loading approach) A map is used to store the game by player, it
 * helps to keep the state of a game for all players
 * 
 * @author ebrigand
 * 
 */
public class HangmanServiceImpl implements HangmanService {

  protected static HangmanService INSTANCE = new HangmanServiceImpl();

  private HangmanServiceImpl() {
  }

  public static HangmanService getInstance() {
    return INSTANCE;
  }

  // private final Map<String, Hangman> gameByPlayer = new
  // ConcurrentHashMap<String, Hangman>();
  private final Map<String, Hangman> gameByPlayer = new HashMap<String, Hangman>();

  /*
   * (non-Javadoc)
   * 
   * @see com.hangman.service.HangmanService#cleanAllGames()
   */
  @Override
  public void cleanAllGames() {
    Iterator<String> it = gameByPlayer.keySet().iterator();
    while (it.hasNext()) {
      String playerName = it.next();
      gameByPlayer.remove(playerName);
    }

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.hangman.service.HangmanService#getGameByPlayer()
   */
  @Override
  public Map<String, Hangman> getGameByPlayer() {
    return Collections.unmodifiableMap(gameByPlayer);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.hangman.service.HangmanService#restart(java.lang.String)
   */
  @Override
  public void removeGame(String playerName) throws GameException {
    gameByPlayer.remove(playerName);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.hangman.service.HangmanService#isHangmanExists(java.lang.String)
   */
  @Override
  public boolean isHangmanExists(String playerName) throws GameException {
    return gameByPlayer.containsKey(playerName);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.hangman.service.HangmanService#getHangman(java.lang.String)
   */
  @Override
  public Hangman getHangman(String playerName) throws GameException {
    if (isHangmanExists(playerName)) {
      return gameByPlayer.get(playerName);
    }
    throw new GameException("Game doesn't exist");
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.hangman.service.HangmanService#newGame(java.lang.String,
   * java.lang.String)
   */
  @Override
  public Hangman newGame(String playerName, String secretWord) throws GameException {
    Hangman hangman = new Hangman(secretWord);
    gameByPlayer.put(playerName, hangman);
    return hangman;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.hangman.service.HangmanService#play(java.lang.String,
   * com.hangman.utils.Letter)
   */
  @Override
  public void play(String playerName, Letter letter) throws GameException {
    Hangman hangman = getHangman(playerName);
    if (isPlayerWonTheGame(playerName)) {
      throw new GameException("Game already finished, the player won already");
    }
    if (isPlayerLostTheGame(playerName)) {
      throw new GameException("Game already finished, the player lost already");
    }
    if (isLetterAlreadyPlayed(playerName, letter)) {
      throw new GameException("Letter already sent");
    }
    if (hangman.getSecretWordLetters().indexOf(letter) != -1) {
      for (int i = 0; i < hangman.getSecretWordLetters().size(); i++) {
        if (hangman.getSecretWordLetters().get(i).equals(letter)) {
          hangman.getCurrentWordLetters().set(i, letter);
        }
      }
    } else {
      hangman.getBadLetters().add(letter);
      hangman.setRemainingMoves(hangman.getRemainingMoves() - 1);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.hangman.service.HangmanService#isLetterAlreadyPlayed(java.lang.String,
   * com.hangman.utils.Letter)
   */
  @Override
  public boolean isLetterAlreadyPlayed(String playerName, Letter letter) {
    Hangman hangman = gameByPlayer.get(playerName);
    if (hangman.getCurrentWordLetters().indexOf(letter) != -1 || hangman.getBadLetters().contains(letter)) {
      return true;
    }
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.hangman.service.HangmanService#isPlayerWonTheGame(java.lang.String)
   */
  @Override
  public boolean isPlayerWonTheGame(String playerName) {
    Hangman hangman = gameByPlayer.get(playerName);
    return !hangman.getCurrentWordLetters().contains(Letter._);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.hangman.service.HangmanService#isPlayerLostTheGame(java.lang.String)
   */
  @Override
  public boolean isPlayerLostTheGame(String playerName) {
    Hangman hangman = gameByPlayer.get(playerName);
    return hangman.getRemainingMoves() == 0;
  }

}
