package com.arcore.mymemory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arcore.mymemory.model.BoardClass
import com.arcore.mymemory.model.MemoryGame
import com.arcore.mymemory.utils.DEFAULT_ICONS

class MainActivity : AppCompatActivity() {


    private lateinit var clRoot: CoordinatorLayout
    private lateinit var rvBoard: RecyclerView
    private lateinit var tvNumMoves: TextView
    private lateinit var tvNumPairs: TextView

    private lateinit var adapter: MemoryBoardAdapter
    private lateinit var memoryGame: MemoryGame

    private var boardSize: BoardClass = BoardClass.MEDIUM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setViews()
        setRecyclerView()

    }

    private fun setRecyclerView() {
        val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getPairs())
        val randomizedImages = (chosenImages + chosenImages).shuffled()

      adapter = MemoryBoardAdapter(this, boardSize, memoryGame.cards,
            object : MemoryBoardAdapter.CardClickListener {
                override fun onCardClick(position: Int) {
                    updateCardFlip(position)
                }
            })

        rvBoard.hasFixedSize()
        rvBoard.layoutManager = GridLayoutManager(this, boardSize.getWidth())
    }

    private fun updateCardFlip(position: Int) {

    }

    private fun setViews() {
        clRoot = findViewById(R.id.clRoot)
        rvBoard = findViewById(R.id.rvBoard)
        tvNumMoves = findViewById(R.id.tvNumMoves)
        tvNumPairs = findViewById(R.id.tvNumPairs)
        memoryGame = MemoryGame(boardSize)
    }

}