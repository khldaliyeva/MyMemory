package com.khldaliyeva.mymemory.models

import com.khldaliyeva.mymemory.utils.DEFAULT_ICONS

class MemoryGame(private val boardSize: BoardSize) {
    val tiles: List<Tile>
    val numPairsMatched = 0

    init {
        val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
        val randomizedImages = (chosenImages + chosenImages).shuffled()

        tiles = randomizedImages.map {
            Tile(it)
        }
    }
}