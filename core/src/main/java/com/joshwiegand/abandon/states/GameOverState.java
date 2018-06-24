package com.joshwiegand.abandon.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.joshwiegand.abandon.Application;
import com.joshwiegand.abandon.managers.Save;
import com.joshwiegand.abandon.managers.StateManager;

public class GameOverState extends State {

  private SpriteBatch sb;
  private ShapeRenderer sr;

  private boolean newHighScore;
  private char[] newName;
  private int currentChar;

  private BitmapFont gameOverFont;
  private BitmapFont font;

  public GameOverState(StateManager stateManager) {
    super(stateManager);
  }

  public void init() {
    sb = new SpriteBatch();
    sr = new ShapeRenderer();

    newHighScore = Save.data.isHighScore(Save.data.getTentativeScore());
    if (newHighScore) {
      newName = new char[]{'A', 'A', 'A'};
      currentChar = 0;
    }

    FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
        Gdx.files.internal("fonts/Hyperspace Bold.ttf")
    );
    gameOverFont = gen.generateFont(new FreeTypeFontGenerator.FreeTypeFontParameter());
    font = gen.generateFont(new FreeTypeFontGenerator.FreeTypeFontParameter());
  }

  public void update(float delta) {
    handleInput();
  }

  public void draw() {

    sb.setProjectionMatrix(Application.camera.combined);

    sb.begin();

    String s;
    float w;

    s = "Game Over";
    w = gameOverFont.getSpaceWidth();
    gameOverFont.draw(sb, s, (Application.WIDTH - w) / 2, 220);

    if (!newHighScore) {
      sb.end();
      return;
    }

    s = "New High Score: " + Save.data.getTentativeScore();
    w = font.getSpaceWidth();
    font.draw(sb, s, (Application.WIDTH - w) / 2, 180);

    for (int i = 0; i < newName.length; i++) {
      font.draw(
          sb,
          Character.toString(newName[i]),
          230 + 14 * i,
          120
      );
    }

    sb.end();

    sr.begin(ShapeType.Line);
    sr.line(
        230 + 14 * currentChar,
        100,
        244 + 14 * currentChar,
        100
    );
    sr.end();
  }

  public void handleInput() {
    if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
      if (newHighScore) {
        Save.data.addHighScore(
            Save.data.getTentativeScore(),
            new String(newName)
        );
        Save.save();
      }
      gsm.setState(StateManager.MENU);
    }

    if (Gdx.input.isKeyJustPressed(Keys.UP)) {
      if (newName[currentChar] == ' ') {
        newName[currentChar] = 'Z';
      } else {
        newName[currentChar]--;
        if (newName[currentChar] < 'A') {
          newName[currentChar] = ' ';
        }
      }
    }

    if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
      if (newName[currentChar] == ' ') {
        newName[currentChar] = 'A';
      } else {
        newName[currentChar]++;
        if (newName[currentChar] > 'Z') {
          newName[currentChar] = ' ';
        }
      }
    }

    if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
      if (currentChar < newName.length - 1) {
        currentChar++;
      }
    }

    if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
      if (currentChar > 0) {
        currentChar--;
      }
    }
  }

  public void dispose() {
    sb.dispose();
    sr.dispose();
    gameOverFont.dispose();
    font.dispose();
  }
}