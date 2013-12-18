package com.hangman.service;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.hangman.exception.GameException;
import com.hangman.utils.Letter;

public class HangmanServiceTest {

  private final HangmanService hangmanService = HangmanServiceImpl.getInstance();

  private static final String PLAYER_NAME = "ebrigand";

  private static final String SECRET_WORD_OK = "house";

  private static final String SECRET_WORD_KO = "house$$";

  private static final Letter LETTER_IN_SECRET_WORD = Letter.O;

  private static final int LETTER_POSITION_IN_SECRET_WORD = 1;

  private static final Letter LETTER_NOT_IN_SECRET_WORD = Letter.Z;

  private static final Letter[] ALL_LETTERS_IN_SECRET_WORD = { Letter.H, Letter.O, Letter.U, Letter.S, Letter.E };

  private static final Letter[] LETTERS_NOT_IN_SECRET_WORD_LOST_GAME = { Letter.A, Letter.B, Letter.C, Letter.D, Letter.F, Letter.G, Letter.I };

  /**
   * Initialier the
   */
  @Before
  public void initialize() {
    hangmanService.cleanAllGames();
  }

  @Test
  public void testGameCreationInitialization() throws GameException {
    hangmanService.newGame(PLAYER_NAME, SECRET_WORD_OK);
    Assert.assertTrue(hangmanService.isHangmanExists(PLAYER_NAME));
    Assert.assertTrue(hangmanService.getHangman(PLAYER_NAME).getSecretWord() != null);
    Assert.assertEquals(StringUtils.upperCase(SECRET_WORD_OK), hangmanService.getHangman(PLAYER_NAME).getSecretWord());
  }

  @Test(expected = GameException.class)
  public void testGameCreationInitializationWithBadWord() throws GameException {
    hangmanService.newGame(PLAYER_NAME, SECRET_WORD_KO);
  }

  @Test
  public void testGameMoveWithGoodLetter() throws GameException {
    hangmanService.newGame(PLAYER_NAME, SECRET_WORD_OK);
    hangmanService.play(PLAYER_NAME, LETTER_IN_SECRET_WORD);
    Assert.assertEquals(LETTER_IN_SECRET_WORD, hangmanService.getHangman(PLAYER_NAME).getCurrentWordLetters().get(LETTER_POSITION_IN_SECRET_WORD));
  }

  @Test
  public void testGameMoveWithBadLetter() throws GameException {
    hangmanService.newGame(PLAYER_NAME, SECRET_WORD_OK);
    int remainingMoveBeforePlay = hangmanService.getHangman(PLAYER_NAME).getRemainingMoves();
    hangmanService.play(PLAYER_NAME, LETTER_NOT_IN_SECRET_WORD);
    Assert.assertEquals(remainingMoveBeforePlay - 1, hangmanService.getHangman(PLAYER_NAME).getRemainingMoves());
    Assert.assertTrue(hangmanService.getHangman(PLAYER_NAME).getBadLetters().contains(LETTER_NOT_IN_SECRET_WORD));
  }

  @Test(expected = GameException.class)
  public void testPlayLetterTwice() throws GameException {
    hangmanService.newGame(PLAYER_NAME, SECRET_WORD_OK);
    hangmanService.play(PLAYER_NAME, LETTER_IN_SECRET_WORD);
    hangmanService.play(PLAYER_NAME, LETTER_IN_SECRET_WORD);
  }

  @Test
  public void testWinTheGame() throws GameException {
    hangmanService.newGame(PLAYER_NAME, SECRET_WORD_OK);
    for (Letter letter : ALL_LETTERS_IN_SECRET_WORD) {
      hangmanService.play(PLAYER_NAME, letter);
    }
    Assert.assertTrue(hangmanService.isPlayerWonTheGame(PLAYER_NAME));
  }

  @Test
  public void testLostTheGame() throws GameException {
    hangmanService.newGame(PLAYER_NAME, SECRET_WORD_OK);
    for (Letter letter : LETTERS_NOT_IN_SECRET_WORD_LOST_GAME) {
      hangmanService.play(PLAYER_NAME, letter);
    }
    Assert.assertTrue(hangmanService.isPlayerLostTheGame(PLAYER_NAME));
  }

  @Test(expected = GameException.class)
  public void testAlreadyLostTheGame() throws GameException {
    hangmanService.newGame(PLAYER_NAME, SECRET_WORD_OK);
    for (Letter letter : LETTERS_NOT_IN_SECRET_WORD_LOST_GAME) {
      hangmanService.play(PLAYER_NAME, letter);
    }
    hangmanService.play(PLAYER_NAME, LETTER_NOT_IN_SECRET_WORD);
  }
}
