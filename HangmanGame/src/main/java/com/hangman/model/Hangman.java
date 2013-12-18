package com.hangman.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.hangman.exception.GameException;
import com.hangman.service.HangmanService;
import com.hangman.utils.Letter;

/**
 * Model for the hangman game
 * 
 * @author ebrigand
 * 
 */
public class Hangman implements Serializable {

  private static final long serialVersionUID = 2854637468960193039L;

  /**
   * Remaining moves, can be used to draw a hang man
   */
  private int remainingMoves = 7;

  /**
   * Secret word
   */
  private final String secretWord;

  /**
   * Current word, each letter played are saved here
   */
  private final List<Letter> currentWordLetters = new ArrayList<Letter>();

  /**
   * The letters of the secret word are saved in a list, this is helping for the
   * algorithm of {@link HangmanService#play(String, Hangman)}
   */
  private final List<Letter> secretWordLetters = new ArrayList<Letter>();

  /**
   * The bad letters are saved to handle the case where a letter is played twice
   */
  private final Set<Letter> badLetters = new HashSet<Letter>();

  /**
   * Constructor, initialize the secretWord and the currentWordLetters with the
   * special letter '_'. The {@link StringUtils#upperCase(String)} method is
   * used on the letters of the secret word to match the letter of the Enum
   * {@link Letter}
   * 
   * @param secretWord
   * @throws GameException
   *           if a letter of the secretWord is not supported
   */
  public Hangman(String secretWord) throws GameException {
    this.secretWord = StringUtils.upperCase(secretWord);
    for (int i = 0; i < this.secretWord.length(); i++) {
      currentWordLetters.add(Letter._);
    }
    for (int i = 0; i < this.secretWord.length(); i++) {
      Letter letter = Letter.getLetter(this.secretWord.charAt(i));
      // If the letter of the secret word doesn't not exist in the Enum or is
      // equal to '_'
      if (letter == null || letter.equals('_'))
        throw new GameException("The letter " + this.secretWord.charAt(i) + " of secret word is not supported");
      secretWordLetters.add(letter);
    }
  }

  public int getRemainingMoves() {
    return remainingMoves;
  }

  public void setRemainingMoves(int remainingMoves) {
    this.remainingMoves = remainingMoves;
  }

  public String getSecretWord() {
    return secretWord;
  }

  public List<Letter> getSecretWordLetters() {
    return secretWordLetters;
  }

  public List<Letter> getCurrentWordLetters() {
    return currentWordLetters;
  }

  public Set<Letter> getBadLetters() {
    return badLetters;
  }

}
