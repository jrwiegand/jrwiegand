package com.joshwiegand.abandon.states;

import com.badlogic.gdx.Input.Keys;
import com.joshwiegand.abandon.Application;
import com.joshwiegand.abandon.managers.StateManager;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.joshwiegand.abandon.entities.Asteroid;
import com.joshwiegand.abandon.managers.Save;

public class MenuState extends State {

  private SpriteBatch sb;
  private ShapeRenderer sr;

  private BitmapFont titleFont;
  private BitmapFont font;

  private int currentItem;
  private String[] menuItems;

  private ArrayList<Asteroid> asteroids;

  public MenuState(StateManager stateManager) {
    super(stateManager);
  }

  public void init() {
    sb = new SpriteBatch();
    sr = new ShapeRenderer();

    FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
        Gdx.files.internal("fonts/Hyperspace Bold.ttf")
    );

    titleFont = gen.generateFont(new FreeTypeFontGenerator.FreeTypeFontParameter());
    titleFont.setColor(Color.WHITE);

    font = gen.generateFont(new FreeTypeFontGenerator.FreeTypeFontParameter());

    menuItems = new String[]{
        "Play",
        "Highscores",
        "Quit"
    };

    asteroids = new ArrayList<>();
    for (int i = 0; i < 6; i++) {
      asteroids.add(
          new Asteroid(
              MathUtils.random(Application.WIDTH),
              MathUtils.random(Application.HEIGHT),
              Asteroid.LARGE
          )
      );
    }
    Save.load();
  }

  public void update(float delta) {
    handleInput();

    for (Asteroid asteroid : asteroids) {
      asteroid.update(delta);
    }
  }

  public void draw() {

    sb.setProjectionMatrix(Application.camera.combined);
    sr.setProjectionMatrix(Application.camera.combined);

    // draw asteroids
    for (Asteroid asteroid : asteroids) {
      asteroid.draw(sr);
    }

    sb.begin();

    // draw title
    float width = titleFont.getSpaceWidth();
    String title = "Asteroids";
    titleFont.draw(
        sb,
            title,
        (Application.WIDTH - width) / 2,
        300
    );

    // draw menu
    for (int i = 0; i < menuItems.length; i++) {
      width = font.getSpaceWidth();
      if (currentItem == i) {
        font.setColor(Color.RED);
      } else {
        font.setColor(Color.WHITE);
      }
      font.draw(
          sb,
          menuItems[i],
          (Application.WIDTH - width) / 2,
          180 - 35 * i
      );
    }
    sb.end();
  }

  public void handleInput() {
    if (Gdx.input.isKeyJustPressed(Keys.UP)) {
      if (currentItem > 0) {
        currentItem--;
      }
    }
    if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
      if (currentItem < menuItems.length - 1) {
        currentItem++;
      }
    }
    if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
      select();
    }
  }

  private void select() {
    // play
    if (currentItem == 0) {
      gsm.setState(StateManager.PLAY);
    }
    // high scores
    else if (currentItem == 1) {
      gsm.setState(StateManager.HIGH_SCORE);
    } else if (currentItem == 2) {
      Gdx.app.exit();
    }
  }

  public void dispose() {
    sb.dispose();
    sr.dispose();
    titleFont.dispose();
    font.dispose();
  }
}