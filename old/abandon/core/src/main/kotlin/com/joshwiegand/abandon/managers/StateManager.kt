package com.joshwiegand.abandon.managers

import com.joshwiegand.abandon.states.GameOverState
import com.joshwiegand.abandon.states.State
import com.joshwiegand.abandon.states.HighScoreState
import com.joshwiegand.abandon.states.MenuState
import com.joshwiegand.abandon.states.PlayState

class StateManager {

    // current game state
    private var gameState: State? = null

    init {
        setState(MENU)
    }

    fun setState(state: Int) {
        if (this.gameState != null) {
            this.gameState!!.dispose()
        }
        if (state == MENU) {
            this.gameState = MenuState(this)
        }
        if (state == PLAY) {
            this.gameState = PlayState(this)
        }
        if (state == HIGH_SCORE) {
            this.gameState = HighScoreState(this)
        }
        if (state == GAME_OVER) {
            this.gameState = GameOverState(this)
        }
    }

    fun update(dt: Float) {
        this.gameState!!.update(dt)
    }

    fun draw() {
        this.gameState!!.draw()
    }

    companion object {

        val MENU = 'M'.toInt() + 'E'.toInt() + 'N'.toInt() + 'U'.toInt()
        val PLAY = 'P'.toInt() + 'L'.toInt() + 'A'.toInt() + 'Y'.toInt()
        val HIGH_SCORE = 'H'.toInt() + 'I'.toInt() + 'G'.toInt() + 'H'.toInt() + '_'.toInt() + 'S'.toInt() + 'C'.toInt() + 'O'.toInt() + 'R'.toInt() + 'E'.toInt()
        val GAME_OVER = 'G'.toInt() + 'A'.toInt() + 'M'.toInt() + 'E'.toInt() + '_'.toInt() + 'O'.toInt() + 'V'.toInt() + 'E'.toInt() + 'R'.toInt()
    }
}