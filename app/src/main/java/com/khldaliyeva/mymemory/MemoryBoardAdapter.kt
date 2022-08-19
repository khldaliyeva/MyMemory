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
import kotlin.math.min

class MemoryBoardAdapter(private val context: Context, private val numTiles: Int) :
    RecyclerView.Adapter<MemoryBoardAdapter.TileViewHolder>() {

    companion object {
        private const val TAG = "MemoryBoardAdapter"

        private const val MARGIN_SIZE = 10
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TileViewHolder {
        val tileWidth = parent.width / 2 - (2 * MARGIN_SIZE)
        val tileHeight = parent.height / 4 - (2 * MARGIN_SIZE)
        val tileSideLength = min(tileWidth, tileHeight)

        val view = LayoutInflater.from(context).inflate(R.layout.tile, parent, false)
        val layoutParams = view.findViewById<CardView>(R.id.cardView).layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.width = tileSideLength
        layoutParams.height = tileSideLength
        layoutParams.setMargins(MARGIN_SIZE)
        return TileViewHolder(view)
    }

    override fun getItemCount() = numTiles

    override fun onBindViewHolder(holder: TileViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class TileViewHolder(tileView: View) : RecyclerView.ViewHolder(tileView) {
        private val imageButton: ImageButton = itemView.findViewById(R.id.imageButton)

        fun bind(position: Int) {
            imageButton.setOnClickListener {
                Log.i(TAG, "Tapped on position: $position")
            }
        }

    }
}