package com.joshwiegand.pa

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.joshwiegand.pa.game.GameControl
import com.joshwiegand.pa.game.GameModel
import com.joshwiegand.pa.util.Assets

class Application : ApplicationAdapter() {
    private var batch: SpriteBatch? = null
    private var model: com.joshwiegand.pa.game.GameModel? = null

    override fun create() {
        Assets.init()
        batch = SpriteBatch()
        val controller = GameControl()
        model = GameModel(controller)
        Gdx.input.inputProcessor = controller
    }

    override fun dispose() {

    }

    override fun pause() {

    }

    override fun render() {
        model!!.update()
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        batch!!.begin()
        batch!!.disableBlending()
        batch!!.draw(Assets.BG, 0f, 0f)
        batch!!.enableBlending()

        for (s in model!!.sprites)
            s.draw(batch!!)

        val displayPanel = "Score: " + model!!.score + "   Lives: " + model!!.lives
        Assets.FONT.draw(batch, displayPanel, 0f, Gdx.graphics.height.toFloat())
        batch!!.end()
    }

    override fun resize(arg0: Int, arg1: Int) {

    }

    override fun resume() {

    }
}

