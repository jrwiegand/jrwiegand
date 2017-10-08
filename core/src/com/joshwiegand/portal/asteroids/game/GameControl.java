package com.joshwiegand.portal.asteroids.game;

import com.badlogic.gdx.InputProcessor;

public class GameControl implements InputProcessor {
    static final int UP = 0;
    static final int DOWN = 1;
    static final int LEFT = 2;
    static final int RIGHT = 3;
    static final int SPACE = 4;
    static final int B = 5;
    boolean[] buttons = new boolean[64];

    private void set(int key, boolean down) {
        int button = -1;

        if (key == 19) button = UP;
        if (key == 21) button = LEFT;
        if (key == 20) button = DOWN;
        if (key == 22) button = RIGHT;
        if (key == 62) button = SPACE;
        if (key == 5) button = B;

        if (button >= 0) {
            buttons[button] = down;
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        set(keycode, true);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        set(keycode, false);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
