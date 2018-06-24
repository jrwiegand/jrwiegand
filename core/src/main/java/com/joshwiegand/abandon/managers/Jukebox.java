package com.joshwiegand.abandon.managers;

import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

public class Jukebox {

  private static Map<String, Sound> SOUNDS = new HashMap<String, Sound>();
  private static final List<String> SOUND_NAMES = Arrays.asList("explode",
      "extralife",
      "largesaucer",
      "pulsehigh",
      "pulselow",
      "saucershoot",
      "shoot",
      "smallsaucer",
      "thruster");

  public static void load() {
    for (String soundName : SOUND_NAMES) {
      FileHandle soundFile = Gdx.files.internal("sounds/" + soundName + ".ogg");
      SOUNDS.put(soundName, Gdx.audio.newSound(soundFile));
    }
  }

  public static void play(String name) {
    SOUNDS.get(name).play();
  }

  public static void loop(String name) {
    SOUNDS.get(name).loop();
  }

  public static void stop(String name) {
    SOUNDS.get(name).stop();
  }

  public static void stopAll() {
    for (Sound s : SOUNDS.values()) {
      s.stop();
    }
  }

  public static void unload() {
    for (String key : SOUNDS.keySet()) {
      SOUNDS.get(key).dispose();
    }
  }
}