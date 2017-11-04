package com.joshwiegand.pa.objects;

import com.joshwiegand.pa.util.Assets;

public class SmallAsteroid extends Asteroid {

    public static final float SIZE = 20;

    private static final int HITS = 1;

    public SmallAsteroid(float x, float y, float vX, float vY) {
        super(Assets.SMALL_ASTEROID, x, y, SIZE, vX, vY, HITS);
    }
}
