package com.joshwiegand.abandon.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.joshwiegand.abandon.Application;
import com.joshwiegand.abandon.managers.Save;
import com.joshwiegand.abandon.managers.StateManager;

public class HighScoreState extends State {

  private SpriteBatch sb;

  private BitmapFont font;

  private long[] highScores;
  private String[] names;

  public HighScoreState(StateManager stateManager) {
    super(stateManager);
  }

  public void init() {
    this.sb = new SpriteBatch();

    FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
      Gdx.files.internal("fonts/Hyperspace Bold.ttf")
    );
    this.font = gen.generateFont(new FreeTypeFontGenerator.FreeTypeFontParameter());

    Save.load();
    this.highScores = Save.data.getHighScores();
    this.names = Save.data.getNames();
  }

  public void update(float delta) {
    handleInput();
  }

  public void draw() {
    this.sb.setProjectionMatrix(Application.camera.combined);

    this.sb.begin();

    String s;
    float w;

    s = "High Scores";
    w = this.font.getSpaceWidth();
    this.font.draw(this.sb, s, (Application.WIDTH - w) / 2, 300);

    for (int i = 0; i < this.highScores.length; i++) {
      s = String.format("%2d. %7s %s", i + 1, this.highScores[i], this.names[i]);
      w = this.font.getSpaceWidth();
      this.font.draw(this.sb, s, (Application.WIDTH - w) / 2, 270 - 20 * i);
    }
    this.sb.end();
  }

  public void handleInput() {
    if (Gdx.input.isKeyJustPressed(Keys.ENTER) || Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
      gsm.setState(StateManager.Companion.getMENU());
    }
  }

  public void dispose() {
    this.sb.dispose();
    this.font.dispose();
  }
}