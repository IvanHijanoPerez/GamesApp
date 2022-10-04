package com.example.gamesapp.domain.use_case.get_games

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import com.example.gamesapp.GamesApp
import com.example.gamesapp.common.Utils.Companion.isInternetAvailable
import com.example.gamesapp.domain.model.Game
import com.example.gamesapp.domain.repository.GameRepository
import javax.inject.Inject


class GetGamesUseCase @Inject constructor(
    private val repository: GameRepository
) {

    suspend operator fun invoke(refresh: Boolean): List<Game> {
        val games = repository.getGamesFromDatabase()
        return if ((refresh && isInternetAvailable()) || (!refresh && games.isNullOrEmpty() && isInternetAvailable())) {
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