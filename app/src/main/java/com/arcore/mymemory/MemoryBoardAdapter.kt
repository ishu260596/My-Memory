package com.arcore.mymemory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.arcore.mymemory.model.BoardClass
import com.arcore.mymemory.model.MemoryCard
import kotlin.math.min

class MemoryBoardAdapter(
    private val context: Context,
    private val boardSize: BoardClass,
    private val cards: List<MemoryCard>,
    private val cardClickListener: CardClickListener
) : RecyclerView.Adapter<MemoryBoardAdapter.MyViewHolder>() {

    companion object {
        private const val TAG = "MemoryBoardAdapter"
        private const val MARGIN_SIZE = 10
    }

    interface CardClickListener {
        fun onCardClick(position: Int)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val cardWidth = parent.width / boardSize.getWidth() - (2 * MARGIN_SIZE)
        val cardHeight = parent.height / boardSize.getHeight() - (2 * MARGIN_SIZE)
        val cardSideLength = min(cardWidth, cardHeight)
        val view = LayoutInflater.from(context).inflate(R.layout.memory_card, parent, false)

        val layoutParams =
            view.findViewById<CardView>(R.id.cardView).layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.width = cardSideLength
        layoutParams.height = cardSideLength
        layoutParams.setMargins(MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return boardSize.numCards
    }


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageButton = itemView.findViewById<ImageButton>(R.id.imageButton)
        fun bind(position: Int) {
            val memoryCard = cards[position]
            imageButton.setImageResource(
                if (memoryCard.isFaceUp) memoryCard.id
                else R.drawable.ic_launcher_background
            )

            imageButton.alpha = if (memoryCard.isMatched) .4f else 1f
            val colorStateList = if (memoryCard.isFaceUp) {
                ContextCompat.getColorStateList(
                    context,
                    R.color.design_default_color_grey
                )
            } else null
            ViewCompat.setBackgroundTintList(imageButton, colorStateList)
        }

    }
}