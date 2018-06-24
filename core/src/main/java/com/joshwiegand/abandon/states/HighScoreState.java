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
    sb = new SpriteBatch();

    FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
        Gdx.files.internal("fonts/Hyperspace Bold.ttf")
    );
    font = gen.generateFont(new FreeTypeFontGenerator.FreeTypeFontParameter());

    Save.load();
    highScores = Save.data.getHighScores();
    names = Save.data.getNames();
  }

  public void update(float delta) {
    handleInput();
  }

  public void draw() {
    sb.setProjectionMatrix(Application.camera.combined);

    sb.begin();

    String s;
    float w;

    s = "High Scores";
    w = font.getSpaceWidth();
    font.draw(sb, s, (Application.WIDTH - w) / 2, 300);

    for (int i = 0; i < highScores.length; i++) {
      s = String.format(
          "%2d. %7s %s",
          i + 1,
          highScores[i],
          names[i]
      );
      w = font.getSpaceWidth();
      font.draw(sb, s, (Application.WIDTH - w) / 2, 270 - 20 * i);
    }
    sb.end();
  }

  public void handleInput() {
    if (Gdx.input.isKeyJustPressed(Keys.ENTER) ||
        Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
      gsm.setState(StateManager.MENU);
    }
  }

  public void dispose() {
    sb.dispose();
    font.dispose();
  }
}