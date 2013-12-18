package com.hangman.service;

import java.util.Map;

import com.hangman.exception.GameException;
import com.hangman.model.Hangman;
import com.hangman.utils.Letter;

/**
 * Interface describing the actions of a user on the game
 * 
 * @author ebrigand
 * 
 */
public interface HangmanService {

  /**
   * Clean all the games of all players
   * 
   * @return
   */
  void cleanAllGames();

  /**
   * Return an unmodifiable map containing all the game by player
   * 
   * @return
   */
  Map<String, Hangman> getGameByPlayer();

  /**
   * Remove the game for a player, remove the current game in the map for a
   * player
   * 
   * @param playerName
   * @throws GameException
   *           if the player doen't exits
   */
  void removeGame(String playerName) throws GameException;

  /**
   * Return true if a game exists for a player, false othewise
   * 
   * @param playerName
   * @return
   * @throws GameException
   *           if the player doen't exits
   */
  boolean isHangmanExists(String playerName) throws GameException;

  /**
   * Return the current game of a player
   * 
   * @param playerName
   * @return
   * @throws GameException
   *           if the player doen't exits
   */
  Hangman getHangman(String playerName) throws GameException;

  /**
   * Create a new game for a player with a secret word
   * 
   * @param playerName
   * @param secretWord
   * @return
   * @throws GameException
   *           if some letter of the word are not handle
   */
  Hangman newGame(String playerName, String secretWord) throws GameException;

  /**
   * Action when a player try a letter, test if the letter belong to the secret
   * word
   * 
   * @param playerName
   * @param letter
   * @throws GameException
   *           if the game is finished or if the letter has already been played
   */
  void play(String playerName, Letter letter) throws GameException;

  /**
   * Return true if the letter has been already played, otherwise false
   * 
   * @param playerName
   * @param letter
   * @return
   */
  boolean isLetterAlreadyPlayed(String playerName, Letter letter);

  /**
   * Return true if the player won the game, otherwise false
   * 
   * @param playerName
   * @return
   */
  boolean isPlayerWonTheGame(String playerName);

  /**
   * Return true if the player lost the game, otherwise false
   * 
   * @param playerName
   * @return
   */
  boolean isPlayerLostTheGame(String playerName);
}
