package com.khldaliyeva.mymemory.models

enum class BoardSize(val numTiles: Int) {
    EASY(8),
    MEDIUM(18),
    HARD(24);

    fun getNumColumns(): Int {
        return when (this) {
            EASY -> 2
            MEDIUM -> 3
            HARD -> 4
        }
    }

    fun getNumRows(): Int {
        return numTiles / getNumColumns()
    }

    fun getNumPairs(): Int {
        return numTiles / 2
    }
}