package com.joshwiegand.portal.asteroids;

import com.badlogic.gdx.graphics.g2d.*;

public class SpaceObject extends Sprite {
    // The x-component of this sprite's velocity.
    private float vx;

    //The y-component of this sprite's velocity.
    private float vy;

    /**
     * Creates a new sprite at the given position.
     * The sprite will be black and stationary.
     *
     * @param initX the x-coordinate of the new sprite
     * @param initY the y-coordinate of the new sprite
     * @param r     the radius of a circle that bounds the new sprite
     */
    SpaceObject(TextureRegion region, float initX, float initY, float r) {
        this(region, initX, initY, r, 0, 0);
    }

    /**
     * Creates a new sprite at the given position.
     *
     * @param initX  the x-coordinate of the new sprite
     * @param initY  the y-coordinate of the new sprite
     * @param r      the radius of a circle that bounds the new sprite
     * @param initVx the x-component of the velocity of the new sprite
     * @param initVy the y-component of the velocity of the new sprite
     */
    SpaceObject(TextureRegion region, float initX, float initY, float r, float initVx, float initVy) {
        super(region);

        setBounds(initX, initY, r, r);
        vx = initVx;
        vy = initVy;
    }

    /**
     * Returns the maximum speed of this sprite.  This implementation
     * returns positive infinity, indicating no limit.  Subclasses should
     * override this method so that <CODE>update</CODE> will take
     * a maximum speed into account.
     */
    public float getMaximumSpeed() {
        return 0;
    }

    /**
     * Sets the velocity of this sprite.  If the given velocity exceeds the
     * maximum, it will be reduced so that it equals the maximum.  In such
     * a case the direction will remain the same.
     *
     * @param vxNew the new velocity in the horizontal direction
     * @param vyNew the new velocity in the vertical direction
     */

    void setVelocity(float vxNew, float vyNew) {
        vx = vxNew;
        vy = vyNew;

        // limit speed

        float v = (float) Math.sqrt(vx * vx + vy * vy);
        float maxV = getMaximumSpeed();

        if (v > maxV) {
            vx /= v / maxV;
            vy /= v / maxV;
        }
    }

    float getVelocityX() {
        return vx;
    }

    float getVelocityY() {
        return vy;
    }

    /**
     * Updates this sprite's position and velocity.  The velocity will be
     * adjusted so that it does not exceed the maximum speed as specified by
     * the <CODE>getMaximumSpeed</CODE> method.
     *
     * @param t the time since the last update, in seconds
     * @param m the game model world this sprite belongs
     */
    public void update(float t, GameModel m) {
        translate(vx * t, vy * t);
    }
}
