package com.joshwiegand.abandon.managers;

import java.io.Serializable;

public class Data implements Serializable {

  private static final long serialVersionUID = 1;

  private final int MAX_SCORES = 10;
  private long[] highScores;
  private String[] names;

  private long tentativeScore;

  Data() {
    this.highScores = new long[this.MAX_SCORES];
    this.names = new String[this.MAX_SCORES];
  }

  // sets up an empty high scores table
  public void init() {
    for (int i = 0; i < this.MAX_SCORES; i++) {
      this.highScores[i] = 0;
      this.names[i] = "---";
    }
  }

  public long[] getHighScores() {
    return this.highScores;
  }

  public String[] getNames() {
    return this.names;
  }

  public long getTentativeScore() {
    return this.tentativeScore;
  }

  public void setTenativeScore(long i) {
    this.tentativeScore = i;
  }

  public boolean isHighScore(long score) {
    return score > this.highScores[this.MAX_SCORES - 1];
  }

  public void addHighScore(long newScore, String name) {
    if (isHighScore(newScore)) {
      this.highScores[this.MAX_SCORES - 1] = newScore;
      this.names[this.MAX_SCORES - 1] = name;
      sortHighScores();
    }
  }

  private void sortHighScores() {
    for (int i = 0; i < this.MAX_SCORES; i++) {
      long score = this.highScores[i];
      String name = this.names[i];
      int j;
      for (j = i - 1;
          j >= 0 && this.highScores[j] < score;
          j--) {
        this.highScores[j + 1] = this.highScores[j];
        this.names[j + 1] = this.names[j];
      }
      this.highScores[j + 1] = score;
      this.names[j + 1] = name;
    }
  }
}