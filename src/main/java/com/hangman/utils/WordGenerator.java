package com.hangman.utils;

import java.util.Random;

/**
 * Util class to store and get a generated word
 * 
 * @author ebrigand
 * 
 */
public class WordGenerator {

  private static String[] words = { "secret", "greetings", "computer", "lucky", "languages", "holiday", "continuation", "fraternal" };

  /**
   * Get a random word from the static word list
   * 
   * @return
   */
  public static String generateWord() {
    Random randIndex = new Random();
    int index = randIndex.nextInt(words.length);
    return words[index];
  }
}
