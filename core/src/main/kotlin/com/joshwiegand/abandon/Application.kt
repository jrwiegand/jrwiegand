package com.joshwiegand.abandon;

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL30
import com.badlogic.gdx.graphics.OrthographicCamera
import com.joshwiegand.abandon.managers.Jukebox
import com.joshwiegand.abandon.managers.StateManager
import ktx.app.KtxApplicationAdapter
import ktx.async.enableKtxCoroutines

class Application : KtxApplicationAdapter {

  private lateinit var stateManager: StateManager

  companion object {
    @JvmField var WIDTH = 0.0f
    @JvmField var HEIGHT = 0.0f
    @JvmField var camera = OrthographicCamera()
  }



  override fun create() {
    enableKtxCoroutines(asynchronousExecutorConcurrencyLevel = 1)
    WIDTH = Gdx.graphics.width.toFloat()
    HEIGHT = Gdx.graphics.height.toFloat()

    camera = OrthographicCamera(WIDTH, HEIGHT)
    camera.translate(WIDTH / 2, HEIGHT / 2)
    camera.update()

    this.stateManager = StateManager()

    Jukebox.load()
  }

  override fun render() {
    // clear screen to black
    Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
    Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT)

    this.stateManager.run({
      update(Gdx.graphics.deltaTime)
      draw()
    })
  }

  override fun dispose() {
    Jukebox.unload()
  }
}