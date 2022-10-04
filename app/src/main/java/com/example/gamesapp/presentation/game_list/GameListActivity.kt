package com.example.gamesapp.presentation.game_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamesapp.R
import com.example.gamesapp.databinding.ActivityGameListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameListActivity : AppCompatActivity() {

    private val viewModel: GameListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityGameListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.gamesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        val swipeRefreshLayout = binding.swipeRefreshLayout

        viewModel.getGames(false)

        viewModel.gameState.observe(this, Observer {
            binding.progressBar.isVisible = it.isLoading
            if (it.error.isNotBlank()) {
                Toast.makeText(this, getString(R.string.check_connection), Toast.LENGTH_SHORT)
                    .show()
            }

            val adapter = GameAdapter(applicationContext, it.games)
            recyclerView.adapter = adapter
        })

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getGames(true)
            swipeRefreshLayout.isRefreshing = false
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val view: View = recyclerView.getChildAt(recyclerView.childCount - 1) as View
                val diff: Int = view.bottom - (recyclerView.height + recyclerView.scrollY)
                if (diff == 0) {
                    viewModel.loadMoreGames()
                }
            }
        })

    }
}