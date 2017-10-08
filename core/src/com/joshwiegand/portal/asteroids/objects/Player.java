package com.joshwiegand.portal.asteroids.objects;

import com.badlogic.gdx.math.MathUtils;
import com.joshwiegand.portal.asteroids.game.GameModel;
import com.joshwiegand.portal.asteroids.util.Assets;

/**
 * This class will detail the constants of the PLAYER's sprite.  Will extend the sprite class.  The PLAYER will be able
 * to rotate the sprite with the arrow keys as well as thrust which increases the ship's velocity in the direction it is
 * facing.
 */
public class Player extends BaseObject {
    //The size of the ship
    public static final int SIZE = 64;

    //Change in velocity for each step while thrusting
    public static final float THRUST_STEP = 1f;

    //The square of the ship's max speed
    private static final float MAX_SPEED = 200;

    private static final float DRAG = .05f;

    //The angle that the ship is facing
    private float shipAngle;


    /**
     * This method creates a ship.  Since this is the PLAYER's ship, it will always start at the same position with the
     * same initial velocity (zero) and the same width and height. The ship will initially face the positive x-axis and
     * the life count will default to 3.
     */

    public Player(float x, float y) {
        super(Assets.PLAYER, x, y, SIZE);
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

    public void stop() {
        setVelocity(0, 0);
    }

    public float getMaxVelocity() {
        return MAX_SPEED;
    }

    public float getShipAngle() {
        return shipAngle;
    }
}
