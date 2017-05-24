package com.joshwiegand.portal.asteroids;

class LargeAsteroid extends Asteroid {

    static final float SIZE = 64;

    LargeAsteroid(float x, float y, float vX, float vY) {
        super(PortalAsteroids.largeAsteroidTexture, x, y, SIZE, vX, vY);
        hitsRemaining = 3;
    }
}
