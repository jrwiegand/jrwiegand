package com.joshwiegand.abandon.entities;

import com.joshwiegand.abandon.Application;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Float;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.joshwiegand.abandon.managers.Jukebox;

public class Player extends SpaceObject {

  private static final int MAX_BULLETS = 4;
  private ArrayList<Bullet> bullets;

  private float[] flamex;
  private float[] flamey;

  private boolean left;
  private boolean right;
  private boolean up;

  private float maxSpeed;
  private float acceleration;
  private float deceleration;
  private float acceleratingTimer;

  private boolean hit;
  private boolean dead;

  private float hitTimer;
  private float hitTime;
  private Line2D.Float[] hitLines;
  private Point2D.Float[] hitLinesVector;

  private long score;
  private int extraLives;
  private long requiredScore;

  public Player(ArrayList<Bullet> bullets) {
    this.bullets = bullets;

    this.x = Application.WIDTH / 2;
    this.y = Application.HEIGHT / 2;

    this.maxSpeed = 300;
    this.acceleration = 200;
    this.deceleration = 10;

    this.shapex = new float[4];
    this.shapey = new float[4];
    this.flamex = new float[3];
    this.flamey = new float[3];

    this.radians = 3.1415f / 2;
    this.rotationSpeed = 3;

    this.hit = false;
    this.hitTimer = 0;
    this.hitTime = 2;

    this.score = 0;
    this.extraLives = 3;
    this.requiredScore = 10000;
  }

  private void setShape() {
    this.shapex[0] = this.x + MathUtils.cos(this.radians) * 8;
    this.shapey[0] = this.y + MathUtils.sin(this.radians) * 8;

    this.shapex[1] = this.x + MathUtils.cos(this.radians - 4 * 3.1415f / 5) * 8;
    this.shapey[1] = this.y + MathUtils.sin(this.radians - 4 * 3.1415f / 5) * 8;

    this.shapex[2] = this.x + MathUtils.cos(this.radians + 3.1415f) * 5;
    this.shapey[2] = this.y + MathUtils.sin(this.radians + 3.1415f) * 5;

    this.shapex[3] = this.x + MathUtils.cos(this.radians + 4 * 3.1415f / 5) * 8;
    this.shapey[3] = this.y + MathUtils.sin(this.radians + 4 * 3.1415f / 5) * 8;
  }

  private void setFlame() {
    this.flamex[0] = this.x + MathUtils.cos(this.radians - 5 * 3.1415f / 6) * 5;
    this.flamey[0] = this.y + MathUtils.sin(this.radians - 5 * 3.1415f / 6) * 5;

    this.flamex[1] = this.x + MathUtils.cos(this.radians - 3.1415f) *
        (6 + this.acceleratingTimer * 50);
    this.flamey[1] = this.y + MathUtils.sin(this.radians - 3.1415f) *
        (6 + this.acceleratingTimer * 50);

    this.flamex[2] = this.x + MathUtils.cos(this.radians + 5 * 3.1415f / 6) * 5;
    this.flamey[2] = this.y + MathUtils.sin(this.radians + 5 * 3.1415f / 6) * 5;
  }

  public void setLeft(boolean b) {
    this.left = b;
  }

  public void setRight(boolean b) {
    this.right = b;
  }

  public void setUp(boolean b) {
    if (b && !this.up && !this.hit) {
      Jukebox.loop("thruster");
    } else if (!b) {
      Jukebox.stop("thruster");
    }
    this.up = b;
  }

  public void setPosition(float x, float y) {
    super.setPosition(x, y);
    setShape();
  }

  public boolean isNotHit() {
    return !this.hit;
  }

  public boolean isDead() {
    return this.dead;
  }

  public void reset() {
    this.x = Application.WIDTH / 2;
    this.y = Application.HEIGHT / 2;
    setShape();
    this.hit = this.dead = false;
  }

  public long getScore() {
    return this.score;
  }

  public int getLives() {
    return this.extraLives;
  }

  public void loseLife() {
    this.extraLives--;
  }

  public void incrementScore(long l) {
    this.score += l;
  }

  public void shoot() {
    if (this.bullets.size() == MAX_BULLETS) {
      return;
    }
    this.bullets.add(new Bullet(this.x, this.y, this.radians));
    Jukebox.play("shoot");
  }

  public void hit() {
    if (this.hit) {
      return;
    }

    this.hit = true;
    this.dx = this.dy = 0;
    this.left = this.right = this.up = false;
    Jukebox.stop("thruster");

    this.hitLines = new Line2D.Float[4];
    for (int i = 0, j = this.hitLines.length - 1; i < this.hitLines.length; j = i++) {
      this.hitLines[i] = new Line2D.Float(
              this.shapex[i],
              this.shapey[i],
              this.shapex[j],
              this.shapey[j]
      );
    }

    this.hitLinesVector = new Point2D.Float[4];
    this.hitLinesVector[0] = new Point2D.Float(
        MathUtils.cos(this.radians + 1.5f),
        MathUtils.sin(this.radians + 1.5f)
    );
    this.hitLinesVector[1] = new Point2D.Float(
        MathUtils.cos(this.radians - 1.5f),
        MathUtils.sin(this.radians - 1.5f)
    );
    this.hitLinesVector[2] = new Point2D.Float(
        MathUtils.cos(this.radians - 2.8f),
        MathUtils.sin(this.radians - 2.8f)
    );
    this.hitLinesVector[3] = new Point2D.Float(
        MathUtils.cos(this.radians + 2.8f),
        MathUtils.sin(this.radians + 2.8f)
    );
  }

  public void update(float dt) {
    // check if hit
    if (this.hit) {
      this.hitTimer += dt;
      if (this.hitTimer > this.hitTime) {
        this.dead = true;
        this.hitTimer = 0;
      }
      for (int i = 0; i < this.hitLines.length; i++) {
        this.hitLines[i].setLine(
                this.hitLines[i].x1 + this.hitLinesVector[i].x * 10 * dt,
                this.hitLines[i].y1 + this.hitLinesVector[i].y * 10 * dt,
                this.hitLines[i].x2 + this.hitLinesVector[i].x * 10 * dt,
                this.hitLines[i].y2 + this.hitLinesVector[i].y * 10 * dt
        );
      }
      return;
    }

    // check extra lives
    if (this.score >= this.requiredScore) {
      this.extraLives++;
      this.requiredScore += 10000;
      Jukebox.play("extralife");
    }

    // turning
    if (this.left) {
      this.radians += this.rotationSpeed * dt;
    } else if (this.right) {
      this.radians -= this.rotationSpeed * dt;
    }

    // accelerating
    if (this.up) {
      this.dx += MathUtils.cos(this.radians) * this.acceleration * dt;
      this.dy += MathUtils.sin(this.radians) * this.acceleration * dt;
      this.acceleratingTimer += dt;
      if (this.acceleratingTimer > 0.1f) {
        this.acceleratingTimer = 0;
      }
    } else {
      this.acceleratingTimer = 0;
    }

    // deceleration
    float vec = (float) Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    if (vec > 0) {
      this.dx -= (this.dx / vec) * this.deceleration * dt;
      this.dy -= (this.dy / vec) * this.deceleration * dt;
    }
    if (vec > this.maxSpeed) {
      this.dx = (this.dx / vec) * this.maxSpeed;
      this.dy = (this.dy / vec) * this.maxSpeed;
    }

    // set position
    this.x += this.dx * dt;
    this.y += this.dy * dt;

    // set shape
    setShape();

    // set flame
    if (this.up) {
      setFlame();
    }

    // screen wrap
    wrap();
  }

  public void draw(ShapeRenderer sr) {
    sr.setColor(1, 1, 1, 1);

    sr.begin(ShapeType.Line);

    // check if hit
    if (this.hit) {
      for (Float hitLine : this.hitLines) {
        sr.line(hitLine.x1, hitLine.y1, hitLine.x2, hitLine.y2);
      }
      sr.end();
      return;
    }

    // draw ship
    for (int i = 0, j = this.shapex.length - 1;
        i < this.shapex.length;
        j = i++) {

      sr.line(this.shapex[i], this.shapey[i], this.shapex[j], this.shapey[j]);
    }

    // draw flames
    if (this.up) {
      for (int i = 0, j = this.flamex.length - 1;
          i < this.flamex.length;
          j = i++) {

        sr.line(this.flamex[i], this.flamey[i], this.flamex[j], this.flamey[j]);

      }
    }
    sr.end();
  }
}