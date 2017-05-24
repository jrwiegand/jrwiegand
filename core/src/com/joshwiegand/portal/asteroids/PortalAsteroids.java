package com.joshwiegand.portal.asteroids;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PortalAsteroids extends ApplicationAdapter {
	private SpriteBatch batch;
	private GameModel model;

	static TextureRegion playerTexture;
	static TextureRegion smallAsteroidTexture;
	static TextureRegion largeAsteroidTexture;
	static TextureRegion missileTexture;
	private static TextureRegion nebulaTexture;
	static TextureRegion explosionTexture;

	/**
	 * explosion sound
	 **/
	private static Sound playerExplosion;
	/**
	 * shot sound
	 **/
	private static Sound shot;

	private BitmapFont font;

	@Override
	public void create() {
		playerTexture = new TextureRegion(new Texture("spaceship.png"));
		smallAsteroidTexture = new TextureRegion(new Texture("asteroid.png"));
		largeAsteroidTexture = new TextureRegion(new Texture("largeAsteroid.png"));
		missileTexture = new TextureRegion(new Texture("missile.gif"));
		nebulaTexture = new TextureRegion(new Texture("nebula1.png"));
		explosionTexture = new TextureRegion(new Texture("explosion.png"));
		playerExplosion = Gdx.app.getAudio().newSound(Gdx.app.getFiles().getFileHandle("playerExplosion.wav", Files.FileType.Internal));
		shot = Gdx.app.getAudio().newSound(Gdx.app.getFiles().getFileHandle("shot.mp3", Files.FileType.Internal));
		font = new BitmapFont();

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
		TextureRegion background = new TextureRegion(nebulaTexture);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		float xPos = -(2048 - Gdx.graphics.getWidth()) + (1024 - Gdx.graphics.getWidth()) * model.getPlayer().getX() / Gdx.graphics.getWidth();
		float yPos = -(2048 - Gdx.graphics.getHeight()) + (1024 - Gdx.graphics.getHeight()) * model.getPlayer().getY() / Gdx.graphics.getHeight();

		batch.begin();
		batch.disableBlending();
		batch.draw(background, 0, 0);
		batch.enableBlending();

		for (Sprite s : model.getSprites())
			s.draw(batch);

		String displayPanel = "Score: " + model.getScore() + "   Lives: " + model.getLives();
		font.draw(batch, displayPanel, 0, Gdx.graphics.getHeight());
		batch.end();
	}

	static void explosion() {
		playerExplosion.play();
	}

	static void shot() {
		shot.play();
	}

	@Override
	public void resize(int arg0, int arg1) {

	}

	@Override
	public void resume() {

	}
}

