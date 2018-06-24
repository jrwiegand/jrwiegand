package com.joshwiegand.abandon.states;

import com.joshwiegand.abandon.managers.StateManager;

public abstract class State {

  protected StateManager gsm;

  protected State(StateManager gsm) {
    this.gsm = gsm;
    init();
  }

  public abstract void init();

  public abstract void update(float delta);

  public abstract void draw();

  public abstract void handleInput();

  public abstract void dispose();
}