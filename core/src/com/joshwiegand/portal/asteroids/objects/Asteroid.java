package com.joshwiegand.portal.asteroids.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.joshwiegand.portal.asteroids.game.GameModel;

/**
 * This class will detail the constants and behavior of the asteroids in this game.
 */
public class Asteroid extends BaseObject {

    //The max speed of an asteroid
    public static final float MAX_SPEED = 10.0f;

    //How many hits each asteroid can take
    private int hitsRemaining;

    private float rotationSpeed;

    /**
     * A constructor for the asteroids. It will take some starting coordinates and will always initialize with a
     * velocity equal to the max.
     */
    Asteroid(TextureRegion texture, float x, float y, float size, float vX, float vY, int hitsRemaining) {
        super(texture, x, y, size, vX, vY);
        this.rotationSpeed = MathUtils.random(30) - 15;
        this.hitsRemaining = hitsRemaining;
    }

    public void update(float t, GameModel m) {
        this.rotate(rotationSpeed * t);
        super.update(t, m);
    }

    public float getMaxVelocity() {
        return MAX_SPEED;
    }

    /**
     * Handles a collision between this asteroid and any obstacles it can collide with, including the player's ship,
     * missles, and enemy ships.
     */
    public boolean hitTaken() {
        hitsRemaining--;
        return hitsRemaining <= 0;
    }
}
