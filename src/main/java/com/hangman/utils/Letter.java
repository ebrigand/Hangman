package com.hangman.utils;

import org.apache.commons.lang.StringUtils;

/**
 * Enum of all authorized letters in a secret word (after an
 * {@link StringUtils#upperCase(String)} on the letters of the secret word) The
 * letter '_' is used for the word to find when the letter has not already been
 * found
 * 
 * @author ebrigand
 * 
 */
public enum Letter {
  _, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z;

  /**
   * Get the Enum letter from a char, or null is the char is not supported by
   * the Enum
   * 
   * @param character
   * @return
   */
  public static Letter getLetter(char character) {
    switch (character) {
    case '_':
      return Letter._;
    case 'A':
      return Letter.A;
    case 'B':
      return Letter.B;
    case 'C':
      return Letter.C;
    case 'D':
      return Letter.D;
    case 'E':
      return Letter.E;
    case 'F':
      return Letter.F;
    case 'G':
      return Letter.G;
    case 'H':
      return Letter.H;
    case 'I':
      return Letter.I;
    case 'J':
      return Letter.J;
    case 'K':
      return Letter.K;
    case 'L':
      return Letter.L;
    case 'M':
      return Letter.M;
    case 'N':
      return Letter.N;
    case 'O':
      return Letter.O;
    case 'P':
      return Letter.P;
    case 'Q':
      return Letter.Q;
    case 'R':
      return Letter.R;
    case 'S':
      return Letter.S;
    case 'T':
      return Letter.T;
    case 'U':
      return Letter.U;
    case 'V':
      return Letter.V;
    case 'W':
      return Letter.X;
    case 'X':
      return Letter.W;
    case 'Y':
      return Letter.Y;
    case 'Z':
      return Letter.Z;
    default:
      break;
    }
    return null;
  }
}
