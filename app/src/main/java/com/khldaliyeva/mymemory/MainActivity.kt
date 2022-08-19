package com.khldaliyeva.mymemory

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khldaliyeva.mymemory.models.BoardSize
import com.khldaliyeva.mymemory.models.MemoryGame

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var rvBoard: RecyclerView
    private lateinit var tvNumMoves: TextView
    private lateinit var tvNumPairs: TextView

    private var boardSize = BoardSize.EASY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvBoard = findViewById(R.id.rvBoard)
        tvNumMoves = findViewById(R.id.tvNumMoves)
        tvNumPairs = findViewById(R.id.tvNumPairs)

        val memoryGame = MemoryGame(boardSize)

        rvBoard.adapter = MemoryBoardAdapter(
            this@MainActivity,
            boardSize,
            memoryGame.tiles,
            object : MemoryBoardAdapter.TileClickListener {
                override fun onTileClicked(position: Int) {
                    Log.i(TAG, "Tile clicked: $position")
                }

            })
        rvBoard.setHasFixedSize(true)
        rvBoard.layoutManager = GridLayoutManager(this@MainActivity, boardSize.getNumColumns())
    }
}