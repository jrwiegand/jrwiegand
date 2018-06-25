package com.joshwiegand.abandon.states;

import com.badlogic.gdx.Input.Keys;
import com.joshwiegand.abandon.Application;
import com.joshwiegand.abandon.managers.StateManager;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.joshwiegand.abandon.entities.Asteroid;
import com.joshwiegand.abandon.entities.Bullet;
import com.joshwiegand.abandon.entities.FlyingSaucer;
import com.joshwiegand.abandon.entities.Particle;
import com.joshwiegand.abandon.entities.Player;
import com.joshwiegand.abandon.managers.Jukebox;
import com.joshwiegand.abandon.managers.Save;

public class PlayState extends State {

  private SpriteBatch sb;
  private ShapeRenderer sr;

  private BitmapFont font;
  private Player hudPlayer;

  private Player player;
  private ArrayList<Bullet> bullets;
  private ArrayList<Asteroid> asteroids;
  private ArrayList<Bullet> enemyBullets;

  private FlyingSaucer flyingSaucer;
  private float fsTimer;
  private float fsTime;

  private ArrayList<Particle> particles;

  private int level;
  private int totalAsteroids;
  private int numAsteroidsLeft;

  private float maxDelay;
  private float minDelay;
  private float currentDelay;
  private float bgTimer;
  private boolean playLowPulse;

  public PlayState(StateManager stateManager) {
    super(stateManager);
  }

  public void init() {
    this.sb = new SpriteBatch();
    this.sr = new ShapeRenderer();

    FreeTypeFontGenerator gen =
        new FreeTypeFontGenerator(
            Gdx.files.internal("fonts/Hyperspace Bold.ttf")
        );

    this.font = gen.generateFont(new FreeTypeFontGenerator.FreeTypeFontParameter());

    this.bullets = new ArrayList<>();

    this.player = new Player(this.bullets);

    this.asteroids = new ArrayList<>();

    this.particles = new ArrayList<>();

    this.level = 1;
    spawnAsteroids();

    this.hudPlayer = new Player(null);

    this.fsTimer = 0;
    this.fsTime = 15;
    this.enemyBullets = new ArrayList<>();

    // set up bg music
    this.maxDelay = 1;
    this.minDelay = 0.25f;
    this.currentDelay = this.maxDelay;
    this.bgTimer = this.maxDelay;
    this.playLowPulse = true;
  }

  private void createParticles(float x, float y) {
    for (int i = 0; i < 6; i++) {
      this.particles.add(new Particle(x, y));
    }
  }

  private void splitAsteroids(Asteroid a) {
    createParticles(a.getx(), a.gety());
    this.numAsteroidsLeft--;
    float delayDelta = this.maxDelay - this.minDelay;
    this.currentDelay = this.minDelay + (delayDelta * this.numAsteroidsLeft / this.totalAsteroids);
    if (a.getType() == Asteroid.LARGE) {
      this.asteroids.add(new Asteroid(a.getx(), a.gety(), Asteroid.MEDIUM));
      this.asteroids.add(new Asteroid(a.getx(), a.gety(), Asteroid.MEDIUM));
    }
    if (a.getType() == Asteroid.MEDIUM) {
      this.asteroids.add(new Asteroid(a.getx(), a.gety(), Asteroid.SMALL));
      this.asteroids.add(new Asteroid(a.getx(), a.gety(), Asteroid.SMALL));
    }
  }

  private void spawnAsteroids() {
    this.asteroids.clear();

    int numToSpawn = 4 + this.level - 1;
    this.totalAsteroids = numToSpawn * 7;
    this.numAsteroidsLeft = this.totalAsteroids;
    this.currentDelay = this.maxDelay;

    for (int i = 0; i < numToSpawn; i++) {
      float x = MathUtils.random(Application.WIDTH);
      float y = MathUtils.random(Application.HEIGHT);

      float dx = x - this.player.getx();
      float dy = y - this.player.gety();
      float dist = (float) Math.sqrt(dx * dx + dy * dy);

      while (dist < 100) {
        x = MathUtils.random(Application.WIDTH);
        y = MathUtils.random(Application.HEIGHT);
        dx = x - this.player.getx();
        dy = y - this.player.gety();
        dist = (float) Math.sqrt(dx * dx + dy * dy);
      }
      this.asteroids.add(new Asteroid(x, y, Asteroid.LARGE));
    }
  }

  public void update(float delta) {
    // get user input
    handleInput();

    // next level
    if (this.asteroids.size() == 0) {
      this.level++;
      spawnAsteroids();
    }

    // update player
    this.player.update(delta);
    if (this.player.isDead()) {
      if (this.player.getLives() == 0) {
        Jukebox.stopAll();
        Save.data.setTenativeScore(this.player.getScore());
        this.gsm.setState(StateManager.GAME_OVER);
        return;
      }
      this.player.reset();
      this.player.loseLife();
      this.flyingSaucer = null;
      Jukebox.stop("smallsaucer");
      Jukebox.stop("largesaucer");
      return;
    }

    // update player bullets
    for (int i = 0; i < this.bullets.size(); i++) {
      this.bullets.get(i).update(delta);
      if (this.bullets.get(i).shouldRemove()) {
        this.bullets.remove(i);
        i--;
      }
    }

    // update flying saucer
    if (this.flyingSaucer == null) {
      this.fsTimer += delta;
      if (this.fsTimer >= this.fsTime) {
        this.fsTimer = 0;
        int type = MathUtils.random() < 0.5 ? FlyingSaucer.SMALL : FlyingSaucer.LARGE;
        int direction = MathUtils.random() < 0.5 ? FlyingSaucer.RIGHT : FlyingSaucer.LEFT;
        this.flyingSaucer = new FlyingSaucer(type, direction, this.player, this.enemyBullets);
      }
    }
    // if there is a flying saucer already
    else {
      this.flyingSaucer.update(delta);
      if (this.flyingSaucer.shouldRemove()) {
        this.flyingSaucer = null;
        Jukebox.stop("smallsaucer");
        Jukebox.stop("largesaucer");
      }
    }

    // update fs bullets
    for (int i = 0; i < this.enemyBullets.size(); i++) {
      this.enemyBullets.get(i).update(delta);
      if (this.enemyBullets.get(i).shouldRemove()) {
        this.enemyBullets.remove(i);
        i--;
      }
    }

    // update asteroids
    for (int i = 0; i < this.asteroids.size(); i++) {
      this.asteroids.get(i).update(delta);
    }

    // update particles
    for (int i = 0; i < this.particles.size(); i++) {
      this.particles.get(i).update(delta);
      if (this.particles.get(i).shouldRemove()) {
        this.particles.remove(i);
        i--;
      }
    }

    // check collision
    checkCollisions();

    // play bg music
    this.bgTimer += delta;
    if (this.player.isNotHit() && this.bgTimer >= this.currentDelay) {
      if (this.playLowPulse) {
        Jukebox.play("pulselow");
      } else {
        Jukebox.play("pulsehigh");
      }
      this.playLowPulse = !this.playLowPulse;
      this.bgTimer = 0;
    }
  }

  private void checkCollisions() {

    // player-asteroid collision
    if (this.player.isNotHit()) {
      for (int i = 0; i < this.asteroids.size(); i++) {
        Asteroid a = this.asteroids.get(i);
        if (a.intersects(this.player)) {
          this.player.hit();
          this.asteroids.remove(i);
          splitAsteroids(a);
          Jukebox.play("explode");
          break;
        }
      }
    }

    // bullet-asteroid collision
    for (int i = 0; i < this.bullets.size(); i++) {
      Bullet b = this.bullets.get(i);
      for (int j = 0; j < this.asteroids.size(); j++) {
        Asteroid a = this.asteroids.get(j);
        if (a.contains(b.getx(), b.gety())) {
          this.bullets.remove(i);
          i--;
          this.asteroids.remove(j);
          splitAsteroids(a);
          this.player.incrementScore(a.getScore());
          Jukebox.play("explode");
          break;
        }
      }
    }

    // player-flying saucer collision
    if (this.flyingSaucer != null) {
      if (this.player.intersects(this.flyingSaucer)) {
        this.player.hit();
        createParticles(this.player.getx(), this.player.gety());
        createParticles(this.flyingSaucer.getx(), this.flyingSaucer.gety());
        this.flyingSaucer = null;
        Jukebox.stop("smallsaucer");
        Jukebox.stop("largesaucer");
        Jukebox.play("explode");
      }
    }

    // bullet-flying saucer collision
    if (this.flyingSaucer != null) {
      for (int i = 0; i < this.bullets.size(); i++) {
        Bullet b = this.bullets.get(i);
        if (this.flyingSaucer.contains(b.getx(), b.gety())) {
          this.bullets.remove(i);
          createParticles(this.flyingSaucer.getx(), this.flyingSaucer.gety());
          this.player.incrementScore(this.flyingSaucer.getScore());
          this.flyingSaucer = null;
          Jukebox.stop("smallsaucer");
          Jukebox.stop("largesaucer");
          Jukebox.play("explode");
          break;
        }
      }
    }

    // player-enemy bullets collision
    if (this.player.isNotHit()) {
      for (int i = 0; i < this.enemyBullets.size(); i++) {
        Bullet b = this.enemyBullets.get(i);
        if (this.player.contains(b.getx(), b.gety())) {
          this.player.hit();
          this.enemyBullets.remove(i);
          Jukebox.play("explode");
          break;
        }
      }
    }

    // flying saucer-asteroid collision
    if (this.flyingSaucer != null) {
      for (int i = 0; i < this.asteroids.size(); i++) {
        Asteroid a = this.asteroids.get(i);
        if (a.intersects(this.flyingSaucer)) {
          this.asteroids.remove(i);
          splitAsteroids(a);
          createParticles(a.getx(), a.gety());
          createParticles(this.flyingSaucer.getx(), this.flyingSaucer.gety());
          this.flyingSaucer = null;
          Jukebox.stop("smallsaucer");
          Jukebox.stop("largesaucer");
          Jukebox.play("explode");
          break;
        }
      }
    }

    // asteroid-enemy bullet collision
    for (int i = 0; i < this.enemyBullets.size(); i++) {
      Bullet b = this.enemyBullets.get(i);
      for (int j = 0; j < this.asteroids.size(); j++) {
        Asteroid a = this.asteroids.get(j);
        if (a.contains(b.getx(), b.gety())) {
          this.asteroids.remove(j);
//          j--;
          splitAsteroids(a);
          this.enemyBullets.remove(i);
          i--;
          createParticles(a.getx(), a.gety());
          Jukebox.play("explode");
          break;
        }
      }
    }
  }

  public void draw() {
    this.sb.setProjectionMatrix(Application.camera.combined);
    this.sr.setProjectionMatrix(Application.camera.combined);

    // draw player
    this.player.draw(this.sr);

    // draw bullets
    for (Bullet bullet : this.bullets) {
      bullet.draw(this.sr);
    }

    // draw flying saucer
    if (this.flyingSaucer != null) {
      this.flyingSaucer.draw(this.sr);
    }

    // draw fs bullets
    for (Bullet enemyBullet : this.enemyBullets) {
      enemyBullet.draw(this.sr);
    }

    // draw this.asteroids
    for (Asteroid asteroid : this.asteroids) {
      asteroid.draw(this.sr);
    }

    // draw particles
    for (Particle particle : this.particles) {
      particle.draw(this.sr);
    }

    // draw score
    this.sb.setColor(1, 1, 1, 1);
    this.sb.begin();
    this.font.draw(this.sb, Long.toString(this.player.getScore()), 40, 390);
    this.sb.end();

    // draw lives
    for (int i = 0; i < this.player.getLives(); i++) {
      this.hudPlayer.setPosition(40 + i * 10, 360);
      this.hudPlayer.draw(this.sr);
    }
  }

  public void handleInput() {
    if (this.player.isNotHit()) {
      // continuous and fluid ship movement
      this.player.setLeft(Gdx.input.isKeyPressed(Keys.LEFT));
      this.player.setRight(Gdx.input.isKeyPressed(Keys.RIGHT));
      this.player.setUp(Gdx.input.isKeyPressed(Keys.UP));

      // Single shot at a time
      if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
        this.player.shoot();
      }
    }
  }

  public void dispose() {
    this.sb.dispose();
    this.sr.dispose();
    this.font.dispose();
  }
}