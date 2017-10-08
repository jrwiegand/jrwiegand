package com.joshwiegand.portal.asteroids.objects;

import com.joshwiegand.portal.asteroids.Application;

/**
 * This class details the constants and behavior of a missile in this game.  A missile can be fired by the player's ship
 * or the enemy ships.
 */
public class Missile extends BaseObject {

    /**
     * The size of a missile sprite
     */
    private static final int SIZE = 5;

    /**
     * The speed of a missile launched from the ship
     */
    public static final float MAX_SPEED = 300;

    /**
     * Creates a missile at the given position with the given velocity.
     */
    public Missile(float x, float y, float velX, float velY) {
        super(Application.missileTexture, x, y, SIZE, velX, velY);
    }
}
