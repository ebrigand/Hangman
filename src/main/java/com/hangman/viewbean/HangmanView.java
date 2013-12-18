package com.hangman.viewbean;

import com.hangman.model.Hangman;

/**
 * View bean used in the JSP
 * 
 * @author ebrigand
 * 
 */
public class HangmanView {

  public String playerName;

  public Hangman hangman;

  public Boolean isPlayerWonTheGame;

  public Boolean isPlayerLostTheGame;

  private Boolean isFinished;

  public HangmanView(String playerName, Hangman hangman, Boolean isPlayerWonTheGame, Boolean isPlayerLostTheGame, Boolean isFinished) {
    this.playerName = playerName;
    this.hangman = hangman;
    this.isPlayerWonTheGame = isPlayerWonTheGame;
    this.isPlayerLostTheGame = isPlayerLostTheGame;
    this.isFinished = isFinished;
  }

  public Hangman getHangman() {
    return hangman;
  }

  public void setHangman(Hangman hangman) {
    this.hangman = hangman;
  }

  public Boolean getIsPlayerWonTheGame() {
    return isPlayerWonTheGame;
  }

  public void setIsPlayerWonTheGame(Boolean isPlayerWonTheGame) {
    this.isPlayerWonTheGame = isPlayerWonTheGame;
  }

  public Boolean getIsPlayerLostTheGame() {
    return isPlayerLostTheGame;
  }

  public void setIsPlayerLostTheGame(Boolean isPlayerLostTheGame) {
    this.isPlayerLostTheGame = isPlayerLostTheGame;
  }

  public Boolean getIsFinished() {
    return isFinished;
  }

  public void setIsFinished(Boolean isFinished) {
    this.isFinished = isFinished;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

}
