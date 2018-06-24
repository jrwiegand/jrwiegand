@file:JvmName("Desktop")

package com.joshwiegand.desktop

import com.badlogic.gdx.Files.FileType
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.joshwiegand.abandon.APP_TITLE
import com.joshwiegand.abandon.APP_HEIGHT
import com.joshwiegand.abandon.APP_RESIZE
import com.joshwiegand.abandon.APP_WIDTH
import com.joshwiegand.abandon.Application

fun main(args: Array<String>) {
  LwjglApplication(Application(), LwjglApplicationConfiguration().apply {
    title = APP_TITLE
    width = APP_WIDTH
    height = APP_HEIGHT
    resizable = APP_RESIZE
    intArrayOf(128, 64, 32, 16).forEach {
      addIcon("libgdx$it.png", FileType.Internal)
    }
  })
}
