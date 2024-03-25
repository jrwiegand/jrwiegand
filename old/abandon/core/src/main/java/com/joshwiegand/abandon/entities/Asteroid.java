package com.joshwiegand.abandon.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;

public class Asteroid extends SpaceObject {
  private int type;
  public static final int SMALL = 0;
  public static final int MEDIUM = 1;
  public static final int LARGE = 2;

  private int numPoints;
  private float[] dists;

  private int score;

  public Asteroid(float x, float y, int type) {
    this.x = x;
    this.y = y;
    this.type = type;

    if (type == SMALL) {
      this.numPoints = 8;
      this.width = this.height = 12;
      this.speed = MathUtils.random(70, 100);
      this.score = 100;
    } else if (type == MEDIUM) {
      this.numPoints = 10;
      this.width = this.height = 20;
      this.speed = MathUtils.random(50, 60);
      this.score = 50;
    } else if (type == LARGE) {
      this.numPoints = 12;
      this.width = this.height = 40;
      this.speed = MathUtils.random(20, 30);
      this.score = 20;
    }

    this.rotationSpeed = MathUtils.random(-1, 1);

    this.radians = MathUtils.random(2 * 3.1415f);
    this.dx = MathUtils.cos(this.radians) * this.speed;
    this.dy = MathUtils.sin(this.radians) * this.speed;

    this.shapex = new float[this.numPoints];
    this.shapey = new float[this.numPoints];
    dists = new float[this.numPoints];

    int radius = this.width / 2;
    for (int i = 0; i < this.numPoints; i++) {
      dists[i] = MathUtils.random(radius / 2, radius);
    }
    setShape();
  }

  private void setShape() {
    float angle = 0;
    for (int i = 0; i < this.numPoints; i++) {
      this.shapex[i] = this.x + MathUtils.cos(angle + this.radians) * this.dists[i];
      this.shapey[i] = this.y + MathUtils.sin(angle + this.radians) * this.dists[i];
      angle += 2 * 3.1415f / this.numPoints;
    }
  }

  public int getType() {
    return this.type;
  }

  public int getScore() {
    return this.score;
  }

  public void update(float dt) {
    this.x += this.dx * dt;
    this.y += this.dy * dt;

    this.radians += this.rotationSpeed * dt;
    setShape();

    wrap();
  }

  public void draw(ShapeRenderer sr) {
    sr.setColor(1, 1, 1, 1);
    sr.begin(ShapeType.Line);
    for (int i = 0, j = this.shapex.length - 1; i < this.shapex.length; j = i++) {
      sr.line(this.shapex[i], this.shapey[i], this.shapex[j], this.shapey[j]);
    }
    sr.end();
  }
}