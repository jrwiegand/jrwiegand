package com.joshwiegand.abandon.managers;

import com.joshwiegand.abandon.states.GameOverState;
import com.joshwiegand.abandon.states.State;
import com.joshwiegand.abandon.states.HighScoreState;
import com.joshwiegand.abandon.states.MenuState;
import com.joshwiegand.abandon.states.PlayState;

public class StateManager {

  // current game state
  private State gameState;

  public static final int MENU = 'M' + 'E' + 'N' + 'U';
  public static final int PLAY = 'P' + 'L' + 'A' + 'Y';
  public static final int HIGH_SCORE = 'H' + 'I' + 'G' + 'H' + '_' + 'S' + 'C' + 'O' + 'R' + 'E';
  public static final int GAME_OVER ='G' + 'A' + 'M' + 'E' + '_' + 'O' + 'V' + 'E' + 'R';

  public StateManager() {
    setState(MENU);
  }

  public void setState(int state) {
    if (gameState != null) {
      gameState.dispose();
    }
    if (state == MENU) {
      gameState = new MenuState(this);
    }
    if (state == PLAY) {
      gameState = new PlayState(this);
    }
    if (state == HIGH_SCORE) {
      gameState = new HighScoreState(this);
    }
    if (state == GAME_OVER) {
      gameState = new GameOverState(this);
    }
  }

  public void update(float dt) {
    gameState.update(dt);
  }

  public void draw() {
    gameState.draw();
  }
}