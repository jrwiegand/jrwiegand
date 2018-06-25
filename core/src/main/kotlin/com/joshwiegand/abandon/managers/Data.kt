package com.joshwiegand.abandon.managers

import java.io.Serializable

class Data internal constructor() : Serializable {

    private val MAX_SCORES = 10
    val highScores: LongArray
    val names: Array<String?>

    var tentativeScore: Long = 0
        private set

    init {
        this.highScores = LongArray(this.MAX_SCORES)
        this.names = arrayOfNulls<String>(this.MAX_SCORES)
    }

    // sets up an empty high scores table
    fun init() {
        for (i in 0 until this.MAX_SCORES) {
            this.highScores[i] = 0
            this.names[i] = "---"
        }
    }

    fun setTenativeScore(i: Long) {
        this.tentativeScore = i
    }

    fun isHighScore(score: Long): Boolean {
        return score > this.highScores[this.MAX_SCORES - 1]
    }

    fun addHighScore(newScore: Long, name: String) {
        if (isHighScore(newScore)) {
            this.highScores[this.MAX_SCORES - 1] = newScore
            this.names[this.MAX_SCORES - 1] = name
            sortHighScores()
        }
    }

    private fun sortHighScores() {
        for (i in 0 until this.MAX_SCORES) {
            val score = this.highScores[i]
            val name = this.names[i]
            var j: Int = i - 1
            while (j >= 0 && this.highScores[j] < score) {
                this.highScores[j + 1] = this.highScores[j]
                this.names[j + 1] = this.names[j]
                j--
            }
            this.highScores[j + 1] = score
            this.names[j + 1] = name
        }
    }

    companion object {

        private const val serialVersionUID: Long = 1
    }
}