package com.joshwiegand.abandon.entities;

import com.joshwiegand.abandon.Application;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.joshwiegand.abandon.managers.Jukebox;

public class FlyingSaucer extends SpaceObject {

  private ArrayList<Bullet> bullets;

  private int type;
  public static final int LARGE = 0;
  public static final int SMALL = 1;

  private int score;

  private float fireTimer;
  private float fireTime;

  private Player player;

  private float pathTimer;
  private float pathTime1;
  private float pathTime2;

  private int direction;
  public static final int LEFT = 0;
  public static final int RIGHT = 1;

  private boolean remove;

  public FlyingSaucer(int type, int direction, Player player, ArrayList<Bullet> bullets) {
    this.type = type;
    this.direction = direction;
    this.player = player;
    this.bullets = bullets;

    this.speed = 70;
    if (direction == LEFT) {
      this.dx = -this.speed;
      this.x = Application.WIDTH;
    } else if (direction == RIGHT) {
      this.dx = this.speed;
      this.x = 0;
    }
    this.y = MathUtils.random(Application.HEIGHT);

    this.shapex = new float[6];
    this.shapey = new float[6];
    setShape();

    if (type == LARGE) {
      this.score = 200;
      Jukebox.loop("largesaucer");
    } else if (type == SMALL) {
      this.score = 1000;
      Jukebox.loop("smallsaucer");
    }

    this.fireTimer = 0;
    this.fireTime = 1;

    this.pathTimer = 0;
    this.pathTime1 = 2;
    this.pathTime2 = this.pathTime1 + 2;
  }

  private void setShape() {
    if (this.type == LARGE) {
      this.shapex[0] = this.x - 10;
      this.shapey[0] = this.y;

      this.shapex[1] = this.x - 3;
      this.shapey[1] = this.y - 5;

      this.shapex[2] = x + 3;
      shapey[2] = y - 5;

      this.shapex[3] = x + 10;
      shapey[3] = y;

      this.shapex[4] = x + 3;
      shapey[4] = y + 5;

      this.shapex[5] = x - 3;
      shapey[5] = y + 5;
    } else if (type == SMALL) {
      this.shapex[0] = x - 6;
      shapey[0] = y;

      this.shapex[1] = x - 2;
      shapey[1] = y - 3;

      this.shapex[2] = x + 2;
      shapey[2] = y - 3;

      this.shapex[3] = x + 6;
      shapey[3] = y;

      this.shapex[4] = x + 2;
      shapey[4] = y + 3;

      this.shapex[5] = x - 2;
      shapey[5] = y + 3;
    }
  }

  public int getScore() {
    return score;
  }

  public boolean shouldRemove() {
    return remove;
  }

  public void update(float dt) {

    // fire
    if (player.isNotHit()) {
      fireTimer += dt;
      if (fireTimer > fireTime) {
        fireTimer = 0;
        if (type == LARGE) {
          radians = MathUtils.random(2 * 3.1415f);
        } else if (type == SMALL) {
          radians = MathUtils.atan2(
              player.gety() - y,
              player.getx() - x
          );
        }
        bullets.add(new Bullet(x, y, radians));
        Jukebox.play("saucershoot");
      }
    }

    // move along path
    pathTimer += dt;

    // move forward
    if (pathTimer < pathTime1) {
      dy = 0;
    }

    // move downward
    if (pathTimer > pathTime1 && pathTimer < pathTime2) {
      dy = -speed;
    }

    // move to end of screen
    if (pathTimer > pathTime1 + pathTime2) {
      dy = 0;
    }

    x += dx * dt;
    y += dy * dt;

    // screen wrap
    if (y < 0) {
      y = Application.HEIGHT;
    }

    // set shape
    setShape();

    // check if remove
    if ((direction == RIGHT && x > Application.WIDTH) ||
        (direction == LEFT && x < 0)) {
      remove = true;
    }
  }

  public void draw(ShapeRenderer sr) {
    sr.setColor(1, 1, 1, 1);
    sr.begin(ShapeType.Line);

    for (int i = 0, j = this.shapex.length - 1;
        i < this.shapex.length;
        j = i++) {

      sr.line(this.shapex[i], shapey[i], this.shapex[j], shapey[j]);
    }

    sr.line(this.shapex[0], shapey[0], this.shapex[3], shapey[3]);

    sr.end();
  }
}