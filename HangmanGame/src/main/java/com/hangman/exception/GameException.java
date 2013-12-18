package com.hangman.exception;

/**
 * Service exception, hold the error message
 * 
 * @author ebrigand
 * 
 */
public class GameException extends Exception {

  private static final long serialVersionUID = 8290083516180935414L;

  private String error;

  public GameException(String error) {
    super();
    this.error = error;
  }

  public GameException(String error, Exception exception) {
    super(exception);
    this.error = error;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

}
