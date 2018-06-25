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
    this.sb = new SpriteBatch();
    this.sr = new ShapeRenderer();

    this.newHighScore = Save.data.isHighScore(Save.data.getTentativeScore());
    if (this.newHighScore) {
      this.newName = new char[]{'A', 'A', 'A'};
      this.currentChar = 0;
    }

    FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
        Gdx.files.internal("fonts/Hyperspace Bold.ttf")
    );
    this.gameOverFont = gen.generateFont(new FreeTypeFontGenerator.FreeTypeFontParameter());
    this.font = gen.generateFont(new FreeTypeFontGenerator.FreeTypeFontParameter());
  }

  public void update(float delta) {
    handleInput();
  }

  public void draw() {

    this.sb.setProjectionMatrix(Application.camera.combined);

    this.sb.begin();

    String s;
    float w;

    s = "Game Over";
    w = this.gameOverFont.getSpaceWidth();
    this.gameOverFont.draw(this.sb, s, (Application.WIDTH - w) / 2, 220);

    if (!this.newHighScore) {
      this.sb.end();
      return;
    }

    s = "New High Score: " + Save.data.getTentativeScore();
    w = this.font.getSpaceWidth();
    this.font.draw(this.sb, s, (Application.WIDTH - w) / 2, 180);

    for (int i = 0; i < this.newName.length; i++) {
      this.font.draw(sb, Character.toString(this.newName[i]), 230 + 14 * i, 120);
    }

    this.sb.end();

    this.sr.begin(ShapeType.Line);
    this.sr.line(
        230 + 14 * this.currentChar,
        100,
        244 + 14 * this.currentChar,
        100
    );
    this.sr.end();
  }

  public void handleInput() {
    if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
      if (this.newHighScore) {
        Save.data.addHighScore(
            Save.data.getTentativeScore(),
            new String(this.newName)
        );
        Save.save();
      }
      this.gsm.setState(StateManager.MENU);
    }

    if (Gdx.input.isKeyJustPressed(Keys.UP)) {
      if (this.newName[this.currentChar] == ' ') {
        this.newName[this.currentChar] = 'Z';
      } else {
        this.newName[this.currentChar]--;
        if (this.newName[this.currentChar] < 'A') {
          this.newName[this.currentChar] = ' ';
        }
      }
    }

    if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
      if (this.newName[this.currentChar] == ' ') {
        this.newName[this.currentChar] = 'A';
      } else {
        this.newName[this.currentChar]++;
        if (this.newName[this.currentChar] > 'Z') {
          this.newName[this.currentChar] = ' ';
        }
      }
    }

    if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
      if (this.currentChar < this.newName.length - 1) {
        this.currentChar++;
      }
    }

    if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
      if (this.currentChar > 0) {
        this.currentChar--;
      }
    }
  }

  public void dispose() {
    this.sb.dispose();
    this.sr.dispose();
    this.gameOverFont.dispose();
    this.font.dispose();
  }
}