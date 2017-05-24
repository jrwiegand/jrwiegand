package com.joshwiegand.portal.asteroids;

class SmallAsteroid extends Asteroid {

    static final float SIZE = 20;

    SmallAsteroid(float x, float y, float vX, float vY) {
        super(PortalAsteroids.smallAsteroidTexture, x, y, SIZE, vX, vY);
        hitsRemaining = 1;
    }
}
