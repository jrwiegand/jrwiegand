package com.joshwiegand.portal.asteroids;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

/**
 * This class will detail the constants and behavior of the asteroids
 * in this game.
 */
public class Asteroid extends SpaceObject {

    //The max speed of an asteroid
    static final float MAX_SPEED = 10.0f;

    //How many hits each asteroid can take
    int hitsRemaining;

    private float rotationSpeed;

    /**
     * A constructor for the asteroids.
     * It will take some starting coordinates
     * and will always initialize with a velocity
     * equal to the max.
     */
    Asteroid(TextureRegion texture, float x, float y, float size, float vX, float vY) {
        super(texture, x, y, size, vX, vY);
        rotationSpeed = MathUtils.random(30) - 15;
    }

    public void update(float t, GameModel m) {
        this.rotate(rotationSpeed * t);
        super.update(t, m);
    }

    public float getMaximumSpeed() {
        return MAX_SPEED;
    }

    /**
     * Handles a collision between this asteroid and any obstacles
     * it can collide with, including the player's ship, missles,
     * and enemy ships.
     */
    boolean hitTaken() {
        hitsRemaining--;
        return hitsRemaining <= 0;

    }
}
