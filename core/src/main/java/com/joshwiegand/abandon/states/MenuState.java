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
    this.sb = new SpriteBatch();
    this.sr = new ShapeRenderer();

    FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
        Gdx.files.internal("fonts/Hyperspace Bold.ttf")
    );

    this.titleFont = gen.generateFont(new FreeTypeFontGenerator.FreeTypeFontParameter());
    this.titleFont.setColor(Color.WHITE);

    this.font = gen.generateFont(new FreeTypeFontGenerator.FreeTypeFontParameter());

    this.menuItems = new String[]{ "Play", "Highscores", "Quit" };

    this.asteroids = new ArrayList<>();
    for (int i = 0; i < 6; i++) {
      this.asteroids.add(
          new Asteroid(
              MathUtils.random(Application.WIDTH),
              MathUtils.random(Application.HEIGHT),
                  Asteroid.Companion.getLARGE()
          )
      );
    }
    Save.load();
  }

  public void update(float delta) {
    handleInput();

    for (Asteroid asteroid : this.asteroids) {
      asteroid.update(delta);
    }
  }

  public void draw() {

    this.sb.setProjectionMatrix(Application.camera.combined);
    this.sr.setProjectionMatrix(Application.camera.combined);

    // draw asteroids
    for (Asteroid asteroid : this.asteroids) {
      asteroid.draw(this.sr);
    }

    this.sb.begin();

    // draw title
    float width = this.titleFont.getSpaceWidth();
    String title = "Asteroids";
    this.titleFont.draw(this.sb, title, (Application.WIDTH - width) / 2, 300);

    // draw menu
    for (int i = 0; i < this.menuItems.length; i++) {
      width = this.font.getSpaceWidth();
      if (this.currentItem == i) {
        this.font.setColor(Color.RED);
      } else {
        this.font.setColor(Color.WHITE);
      }
      this.font.draw(
          this.sb,
          this.menuItems[i],
          (Application.WIDTH - width) / 2,
          180 - 35 * i
      );
    }
    this.sb.end();
  }

  public void handleInput() {
    if (Gdx.input.isKeyJustPressed(Keys.UP)) {
      if (this.currentItem > 0) {
        this.currentItem--;
      }
    }
    if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
      if (this.currentItem < this.menuItems.length - 1) {
        this.currentItem++;
      }
    }
    if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
      select();
    }
  }

  private void select() {
    // play
    if (this.currentItem == 0) {
      this.gsm.setState(StateManager.Companion.getPLAY());
    }
    // high scores
    else if (this.currentItem == 1) {
      this.gsm.setState(StateManager.Companion.getHIGH_SCORE());
    } else if (this.currentItem == 2) {
      Gdx.app.exit();
    }
  }

  public void dispose() {
    this.sb.dispose();
    this.sr.dispose();
    this.titleFont.dispose();
    this.font.dispose();
  }
}