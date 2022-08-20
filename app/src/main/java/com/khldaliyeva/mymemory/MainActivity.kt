package com.khldaliyeva.mymemory

import android.animation.ArgbEvaluator
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.khldaliyeva.mymemory.models.BoardSize
import com.khldaliyeva.mymemory.models.MemoryGame

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var clRoot: ConstraintLayout
    private lateinit var rvBoard: RecyclerView
    private lateinit var tvNumMoves: TextView
    private lateinit var tvNumPairs: TextView

    private lateinit var memoryGame: MemoryGame
    private lateinit var adapter: MemoryBoardAdapter

    private var boardSize = BoardSize.EASY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clRoot = findViewById(R.id.clRoot)
        rvBoard = findViewById(R.id.rvBoard)
        tvNumMoves = findViewById(R.id.tvNumMoves)
        tvNumPairs = findViewById(R.id.tvNumPairs)

        tvNumPairs.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.color_progress_none))

        memoryGame = MemoryGame(boardSize)

        adapter = MemoryBoardAdapter(
            this@MainActivity,
            boardSize,
            memoryGame.tiles,
            object : MemoryBoardAdapter.TileClickListener {
                override fun onTileClicked(position: Int) {
                    updateGameWithFlip(position)
                }

            }
        )

        rvBoard.adapter = adapter
        rvBoard.setHasFixedSize(true)
        rvBoard.layoutManager = GridLayoutManager(this@MainActivity, boardSize.getNumColumns())
    }

    private fun updateGameWithFlip(position: Int) {
        if (memoryGame.haveWonGame()) {
            Snackbar.make(clRoot, "You already won!", Snackbar.LENGTH_LONG).show()
            return
        }

        if (memoryGame.isTileFlipped(position)) {
            Snackbar.make(clRoot, "Invalid move!", Snackbar.LENGTH_SHORT).show()
            return
        }

        if (memoryGame.flipTile(position)) {
            Log.i(TAG, "Found a match! Num pairs found: ${memoryGame.numPairsMatched}")
            val color = ArgbEvaluator().evaluate(
                memoryGame.numPairsMatched.toFloat() / boardSize.getNumPairs(),
                ContextCompat.getColor(this@MainActivity, R.color.color_progress_none),
                ContextCompat.getColor(this@MainActivity, R.color.color_progress_full)
            ) as Int
            tvNumPairs.setTextColor(color)
            tvNumPairs.text = "Pairs: ${memoryGame.numPairsMatched} / ${boardSize.getNumPairs()}"
            if (memoryGame.haveWonGame()) {
                Snackbar.make(clRoot, "You won! Congratulations!", Snackbar.LENGTH_LONG).show()
            }
        }

        tvNumMoves.text = "Moves: ${memoryGame.getNumMoves()}"
        adapter.notifyDataSetChanged()
    }
}