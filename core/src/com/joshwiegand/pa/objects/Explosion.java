package com.joshwiegand.pa.objects;

import com.joshwiegand.pa.game.GameModel;
import com.joshwiegand.pa.Application;
import com.joshwiegand.pa.util.Assets;

/**
 * This class will detail the constants and behavior of the asteroids in this game.
 */

public class Explosion extends BaseObject {

    private static final float SIZE = 128;

    private int explosionTimer;

    public Explosion(float x, float y) {
        super(Assets.EXPLOSION, x, y, SIZE, 0, 0);
        Application.explosion();
        explosionTimer = 100;
    }

    public void update(float t, GameModel m) {
        explosionTimer -= t;
        super.update(t, m);
    }

    public int getExplosionTimer() {
        return explosionTimer;
    }
}
