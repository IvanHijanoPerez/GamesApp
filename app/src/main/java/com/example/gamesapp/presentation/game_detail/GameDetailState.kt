package com.example.gamesapp.presentation.game_detail

import com.example.gamesapp.domain.model.Game

data class GameDetailState(
    val id: Int = -1,
    val name: String = "",
    val cover: String = "",
    val genres: List<String> = emptyList(),
    val platforms: List<String> = emptyList(),
    val companies: List<String> = emptyList()
)
