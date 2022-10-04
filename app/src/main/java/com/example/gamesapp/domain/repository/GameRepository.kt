package com.example.gamesapp.domain.repository

import com.example.gamesapp.data.remote.dto.GameDto
import com.example.gamesapp.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameRepository {

    suspend fun getGamesFromApi(): List<Game>

    suspend fun getGamesFromDatabase(): List<Game>

    suspend fun getGameById(id: Int): Game?

    suspend fun insertGames(games: List<Game>)

    suspend fun clearGames()
}