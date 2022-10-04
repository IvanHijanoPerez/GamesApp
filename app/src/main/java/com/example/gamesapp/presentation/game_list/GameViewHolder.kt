package com.example.gamesapp.presentation.game_list

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.api.igdb.utils.ImageSize
import com.api.igdb.utils.ImageType
import com.api.igdb.utils.imageBuilder
import com.bumptech.glide.Glide
import com.example.gamesapp.R
import com.example.gamesapp.databinding.GameItemBinding
import com.example.gamesapp.domain.model.Game
import com.example.gamesapp.presentation.game_detail.GameDetailActivity

class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = GameItemBinding.bind(itemView)

    fun bind(game: Game, context: Context) {
        binding.gameName.text = game.name
        val urlImage = imageBuilder(game.cover, ImageSize.LOGO_MEDIUM, ImageType.PNG)
        Glide.with(context).load(urlImage).placeholder(R.drawable.ic_error_24).into(binding.gameCover)
        binding.gameCardView.setOnClickListener {
            val intent = Intent(context, GameDetailActivity::class.java)
            intent.putExtra("gameId", game.id)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
            context.startActivity(intent)
        }
    }
}