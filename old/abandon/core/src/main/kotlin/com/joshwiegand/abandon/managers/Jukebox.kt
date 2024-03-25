package com.joshwiegand.abandon.managers

import java.util.*

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.files.FileHandle

object Jukebox {

    private val SOUNDS = HashMap<String, Sound>()
    private val SOUND_NAMES = Arrays.asList("explode",
            "extralife",
            "largesaucer",
            "pulsehigh",
            "pulselow",
            "saucershoot",
            "shoot",
            "smallsaucer",
            "thruster")

    fun load() {
        for (soundName in SOUND_NAMES) {
            val soundFile = Gdx.files.internal("sounds/$soundName.ogg")
            SOUNDS[soundName] = Gdx.audio.newSound(soundFile)
        }
    }

    fun play(name: String) {
        SOUNDS[name]?.play()
    }

    fun loop(name: String) {
        SOUNDS[name]?.loop()
    }

    fun stop(name: String) {
        SOUNDS[name]?.stop()
    }

    fun stopAll() {
        for (s in SOUNDS.values) {
            s.stop()
        }
    }

    fun unload() {
        for (key in SOUNDS.keys) {
            SOUNDS[key]?.dispose()
        }
    }
}