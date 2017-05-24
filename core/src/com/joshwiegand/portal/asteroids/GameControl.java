package com.joshwiegand.portal.asteroids;

import com.badlogic.gdx.InputProcessor;

public class GameControl implements InputProcessor {
    static final int UP = 0;
    static final int DOWN = 1;
    static final int LEFT = 2;
    static final int RIGHT = 3;
    static final int SPACE = 4;
    static final int B = 5;
    boolean[] buttons = new boolean[64];
    private boolean[] oldButtons = new boolean[64];

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

    public void tick() {
        System.arraycopy(buttons, 0, oldButtons, 0, buttons.length);

   	 /*if(Gdx.app.getType() == ApplicationType.Android) {
	   	 boolean left = false;
	   	 boolean right = false;
	   	 boolean z = false;
	   	 boolean s = false;

	   	 for(int i = 0; i < 2; i++) {
	   		 int x = (int)((Gdx.input.getX(i) / (float)Gdx.graphics.getWidth()) * 320);
	   		 if(!Gdx.input.isTouched(i)) continue;
	   		 if(x < 32) {
	   			 set(Keys.DPAD_LEFT, true);
	   			 left |= true;
	   		 }
	   		 if(x > 32 && x < 90) {
	   			 set(Keys.DPAD_RIGHT, true);
	   			 right |= true;
	   		 }
	   		 if(x > 320-64 && x < 320-32) {
	   			 set(Keys.Z, true);
	   			 z |= true;
	   		 }
	   		 if(x > 320-32 && x < 320) {
	   			 set(Keys.X, true);
	   			 s |= true;
	   		 }
	   	 }

	   	 if(left==false) set(Keys.DPAD_LEFT, false);
	   	 if(right==false) set(Keys.DPAD_RIGHT, false);
	   	 if(z==false) set(Keys.Z, false);
	   	 if(s==false) set(Keys.X, false);
   	 }      	*/
    }


    public void releaseAllKeys() {
        for (int i = 0; i < buttons.length; i++) {
            if (i == UP || i == DOWN) continue;
            buttons[i] = false;
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
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
		/*x = (int)(x / (float)Gdx.graphics.getWidth() * 320);
		if(x > 160 - 32 && x < 160) {
			set(Keys.DPAD_UP, !buttons[UP]);
			if(buttons[UP])
				buttons[DOWN] = false;
		}
		if(x > 160 && x < 160 + 32) {
			set(Keys.DPAD_DOWN, !buttons[DOWN]);
			if(buttons[DOWN])
				buttons[UP] = false;
		}
		System.out.println("buttons: " + buttons[UP] + ", " + buttons[DOWN]);*/
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
