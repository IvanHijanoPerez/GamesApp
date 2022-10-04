package com.example.gamesapp.presentation.game_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.api.igdb.utils.ImageSize
import com.api.igdb.utils.ImageType
import com.api.igdb.utils.imageBuilder
import com.bumptech.glide.Glide
import com.example.gamesapp.R
import com.example.gamesapp.databinding.ActivityGameDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameDetailActivity : AppCompatActivity() {

    private val viewModel: GameDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityGameDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val gameId = intent.getIntExtra("gameId",0)

        viewModel.onCreate(gameId)

        viewModel.gameDetailState.observe(this, Observer {
            supportActionBar!!.title = it.name
            binding.gameNameDetail.text = it.name
            var genres = it.genres.joinToString(separator = "\n")
            if(genres.isNullOrEmpty()){
                genres = getString(R.string.unknown)
            }
            var platforms = it.platforms.joinToString(separator = "\n")
            if(platforms.isNullOrEmpty()){
                platforms = getString(R.string.unknown)
            }
            var companies = it.companies.joinToString(separator = "\n")
            if(companies.isNullOrEmpty()){
                companies = getString(R.string.unknown)
            }
            binding.gameGenres.text =  genres
            binding.gamePlatforms.text = platforms
            binding.gameCompanies.text = companies
            val urlImage = imageBuilder(it.cover, ImageSize.HD, ImageType.PNG)
            Glide.with(this).load(urlImage).placeholder(R.drawable.ic_error_24).into(binding.gameCoverDetail)
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}