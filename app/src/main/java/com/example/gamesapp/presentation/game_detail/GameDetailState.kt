package com.example.gamesapp.presentation.game_detail

data class GameDetailState(
    val id: Int = -1,
    val name: String = "",
    val cover: String = "",
    val genres: List<String> = emptyList(),
    val platforms: List<String> = emptyList(),
    val companies: List<String> = emptyList()
)
