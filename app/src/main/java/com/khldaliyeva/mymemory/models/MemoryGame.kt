package com.khldaliyeva.mymemory.models

import com.khldaliyeva.mymemory.utils.DEFAULT_ICONS

class MemoryGame(private val boardSize: BoardSize) {

    val tiles: List<Tile>
    var numPairsMatched = 0

    private var numTileFlips = 0
    private var indexOfSingleSelectedTile: Int? = null

    init {
        val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
        val randomizedImages = (chosenImages + chosenImages).shuffled()

        tiles = randomizedImages.map {
            Tile(it)
        }
    }

    fun flipTile(position: Int): Boolean {
        ++numTileFlips
        val tile = tiles[position]

        var foundMatch = false
        indexOfSingleSelectedTile = if (indexOfSingleSelectedTile == null) {
            restoreTiles()
            position
        } else {
            foundMatch = checkForMatch(indexOfSingleSelectedTile!!, position)
            null
        }

        tile.isFlipped = !tile.isFlipped
        return foundMatch
    }

    private fun checkForMatch(position1: Int, position2: Int): Boolean {
        if (tiles[position1].identifier != tiles[position2].identifier) {
            return false
        }
        tiles[position1].isMatched = true
        tiles[position2].isMatched = true
        ++numPairsMatched
        return true
    }

    private fun restoreTiles() {
        for (tile in tiles) {
            if (!tile.isMatched) {
                tile.isFlipped = false
            }
        }
    }

    fun haveWonGame(): Boolean {
        return numPairsMatched == boardSize.getNumPairs()
    }

    fun isTileFlipped(position: Int): Boolean {
        return tiles[position].isFlipped
    }

    fun getNumMoves(): Int {
        return numTileFlips / 2
    }
}