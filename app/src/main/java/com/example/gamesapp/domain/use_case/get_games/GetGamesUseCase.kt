package com.example.gamesapp.domain.use_case.get_games

import com.example.gamesapp.common.Utils.Companion.isInternetAvailable
import com.example.gamesapp.domain.model.Game
import com.example.gamesapp.domain.repository.GameRepository
import javax.inject.Inject


class GetGamesUseCase @Inject constructor(
    private val repository: GameRepository
) {

    suspend operator fun invoke(refresh: Boolean): List<Game> {
        val games = repository.getGamesFromDatabase()
        return if ((refresh && isInternetAvailable()) || (!refresh && games.isEmpty() && isInternetAvailable())) {
            val gamesApi = repository.getGamesFromApi()
            repository.clearGames()
            repository.insertGames(gamesApi)
            repository.getGamesFromDatabase()

        } else {
            games
        }
    }

    suspend fun loadMoreGames(): List<Game> {
        return if (isInternetAvailable()) {
            val gamesApi = repository.getGamesFromApi()
            repository.insertGames(gamesApi)
            repository.getGamesFromDatabase()
        } else {
            emptyList()
        }
    }

}