package com.khldaliyeva.mymemory

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import androidx.core.view.setMargins
import androidx.recyclerview.widget.RecyclerView
import com.khldaliyeva.mymemory.models.BoardSize
import com.khldaliyeva.mymemory.models.Tile
import kotlin.math.min

class MemoryBoardAdapter(
    private val context: Context,
    private val boardSize: BoardSize,
    private val tiles: List<Tile>,
    private val tileClickListener: TileClickListener
) :
    RecyclerView.Adapter<MemoryBoardAdapter.TileViewHolder>() {

    companion object {
        private const val TAG = "MemoryBoardAdapter"

        private const val MARGIN_SIZE = 10
    }

    interface TileClickListener {
        fun onTileClicked(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TileViewHolder {
        val tileWidth = parent.width / boardSize.getNumColumns() - (2 * MARGIN_SIZE)
        val tileHeight = parent.height / boardSize.getNumRows() - (2 * MARGIN_SIZE)
        val tileSideLength = min(tileWidth, tileHeight)

        val view = LayoutInflater.from(context).inflate(R.layout.tile, parent, false)
        val layoutParams =
            view.findViewById<CardView>(R.id.cardView).layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.width = tileSideLength
        layoutParams.height = tileSideLength
        layoutParams.setMargins(MARGIN_SIZE)
        return TileViewHolder(view)
    }

    override fun getItemCount() = boardSize.numTiles

    override fun onBindViewHolder(holder: TileViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class TileViewHolder(tileView: View) : RecyclerView.ViewHolder(tileView) {
        private val imageButton: ImageButton = itemView.findViewById(R.id.imageButton)

        fun bind(position: Int) {
            val tile = tiles[position]
            imageButton.setImageResource(if (tile.isFlipped) tile.identifier else R.drawable.ic_launcher_background)
            imageButton.setOnClickListener {
                Log.i(TAG, "Tapped on position: $position")
                tileClickListener.onTileClicked(position)
            }
        }

    }
}