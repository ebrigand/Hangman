package com.hangman.viewbean;

import com.hangman.utils.Letter;

/**
 * View bean used in the JSP
 * 
 * @author ebrigand
 * 
 */
public class PlayedLetter {

  public Letter letter;

  public Boolean isAlreadyPlayed;

  public PlayedLetter(Letter letter, Boolean isAlreadyPlayed) {
    this.letter = letter;
    this.isAlreadyPlayed = isAlreadyPlayed;
  }

  public Letter getLetter() {
    return letter;
  }

  public void setLetter(Letter letter) {
    this.letter = letter;
  }

  public Boolean getIsAlreadyPlayed() {
    return isAlreadyPlayed;
  }

  public void setIsAlreadyPlayed(Boolean isAlreadyPlayed) {
    this.isAlreadyPlayed = isAlreadyPlayed;
  }

}
