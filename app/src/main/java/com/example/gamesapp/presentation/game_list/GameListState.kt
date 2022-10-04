package com.example.gamesapp.presentation.game_list

import com.example.gamesapp.domain.model.Game

data class GameListState(
    val games: List<Game> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false,
) {
}