package com.joshwiegand.pa.util;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
    public static TextureRegion PLAYER;
    public static TextureRegion SMALL_ASTEROID;
    public static TextureRegion LARGE_ASTEROID;
    public static TextureRegion MISSILE;
    public static TextureRegion EXPLOSION;
    public static TextureRegion BG;
    public static Sound PLAYER_EXPLOSION;
    public static Sound SHOT;
    public static BitmapFont FONT;

    public static void init() {
        initTextures();
        initSounds();
        FONT = new BitmapFont();
    }

    private static void initTextures() {
        PLAYER = new TextureRegion(new Texture("ship.png"));
        SMALL_ASTEROID = new TextureRegion(new Texture("asteroid_small.png"));
        LARGE_ASTEROID = new TextureRegion(new Texture("asteroid_large.png"));
        MISSILE = new TextureRegion(new Texture("missile.gif"));
        EXPLOSION = new TextureRegion(new Texture("explosion.png"));
        BG = new TextureRegion(new Texture("bg.png"));
    }

    private static void initSounds() {
        Audio audio = Gdx.app.getAudio();
        Files files = Gdx.app.getFiles();
        Files.FileType internal = Files.FileType.Internal;

        PLAYER_EXPLOSION = audio.newSound(files.getFileHandle("explosion.wav", internal));
        SHOT = audio.newSound(files.getFileHandle("shot.mp3", internal));
    }
}
