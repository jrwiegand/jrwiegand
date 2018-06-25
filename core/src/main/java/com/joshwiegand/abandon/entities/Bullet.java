package com.joshwiegand.abandon.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;

public class Bullet extends SpaceObject {

  private float lifeTime;
  private float lifeTimer;

  private boolean remove;

  Bullet(float x, float y, float radians) {
    this.x = x;
    this.y = y;
    this.radians = radians;

    float speed = 350;
    this.dx = MathUtils.cos(radians) * speed;
    this.dy = MathUtils.sin(radians) * speed;

    this.width = this.height = 2;

    this.lifeTimer = 0;
    this.lifeTime = 1;
  }

  public boolean shouldRemove() {
    return this.remove;
  }

  public void update(float dt) {
    this.x += this.dx * dt;
    this.y += this.dy * dt;

    wrap();

    this.lifeTimer += dt;
    if (this.lifeTimer > this.lifeTime) {
      this.remove = true;
    }
  }

  public void draw(ShapeRenderer sr) {
    sr.setColor(1, 1, 1, 1);
    sr.begin(ShapeType.Line);
    sr.circle(this.x - this.width / 2, this.y - this.height / 2, this.width / 2);
    sr.end();
  }
}