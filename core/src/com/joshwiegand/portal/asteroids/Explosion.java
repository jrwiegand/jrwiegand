package com.joshwiegand.portal.asteroids;

/**
 * This class will detail the constants and behavior of the asteroids
 * in this game.
 */

public class Explosion extends SpaceObject {

    private static final float SIZE = 128;

    private int explosionTimer;

    Explosion(float x, float y) {
        super(PortalAsteroids.explosionTexture, x, y, SIZE, 0, 0);
        PortalAsteroids.explosion();
        explosionTimer = 100;
    }

    public void update(float t, GameModel m) {
        explosionTimer -= t;
        super.update(t, m);
    }

    int getExplosionTimer() {
        return explosionTimer;
    }
}
