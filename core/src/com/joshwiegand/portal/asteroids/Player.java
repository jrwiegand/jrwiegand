package com.joshwiegand.portal.asteroids;

import com.badlogic.gdx.math.MathUtils;

/**
 * This class will detail the constants
 * of the player's sprite.  Will extend
 * the sprite class.  The player will be able
 * to rotate the sprite with the arrow keys
 * as well as thrust which increases the ship's
 * velocity in the direction it is facing.
 */
public class Player extends SpaceObject {
    //The size of the ship
    static final int SIZE = 64;

    //Change in velocity for each step while thrusting
    static final float THRUST_STEP = 1f;

    //The square of the ship's max speed
    private static final float MAX_SPEED = 200;

    private static final float DRAG = .05f;

    //The angle that the ship is facing
    private float shipAngle;


    /**
     * This method creates a ship.  Since this is the player's
     * ship, it will always start at the same position with the same
     * initial velocity (zero) and the same width and height.
     * The ship will initially face the positive x-axis
     * and the life count will default to 3.
     */

    Player(float x, float y) {
        super(PortalAsteroids.playerTexture, x, y, SIZE);
        shipAngle = 0;
        setRotation(-shipAngle);
    }

    public void update(float t, GameModel m) {
        float decelerate = 1 - (DRAG * t);
        setVelocity(getVelocityX() * decelerate, getVelocityY() * decelerate);

        shipAngle = MathUtils.atan2(getVelocityX(), getVelocityY());

        setRotation(-shipAngle * MathUtils.radiansToDegrees);

        super.update(t, m);
    }

    void stop() {
        setVelocity(0, 0);
    }

    public float getMaximumSpeed() {
        return MAX_SPEED;
    }

    float getShipAngle() {
        return shipAngle;
    }
}
