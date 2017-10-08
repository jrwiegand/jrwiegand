package com.joshwiegand.portal.asteroids;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.joshwiegand.portal.asteroids.game.GameControl;
import com.joshwiegand.portal.asteroids.game.GameModel;
import com.joshwiegand.portal.asteroids.util.Assets;

public class Application extends ApplicationAdapter {
    private SpriteBatch batch;
    private com.joshwiegand.portal.asteroids.game.GameModel model;

    @Override
    public void create() {
        Assets.init();
        batch = new SpriteBatch();
        GameControl controller = new GameControl();
        model = new GameModel(controller);
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void render() {
        model.update();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.disableBlending();
        batch.draw(Assets.BG, 0, 0);
        batch.enableBlending();

        for (Sprite s : model.getSprites())
            s.draw(batch);

        String displayPanel = "Score: " + model.getScore() + "   Lives: " + model.getLives();
        Assets.FONT.draw(batch, displayPanel, 0, Gdx.graphics.getHeight());
        batch.end();
    }

    public static void explosion() {
        Assets.PLAYER_EXPLOSION.play();
    }

    public static void shot() {
        Assets.SHOT.play();
    }

    @Override
    public void resize(int arg0, int arg1) {

    }

    @Override
    public void resume() {

    }
}

