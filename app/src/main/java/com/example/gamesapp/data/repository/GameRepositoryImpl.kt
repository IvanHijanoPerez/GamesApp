package com.example.gamesapp.data.repository

import com.example.gamesapp.data.local.dao.GameDao
import com.example.gamesapp.data.remote.GamesApi
import com.example.gamesapp.data.remote.dto.toGame
import com.example.gamesapp.domain.model.Game
import com.example.gamesapp.domain.repository.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val api: GamesApi,
    private val dao: GameDao
) : GameRepository {

    override suspend fun getGamesFromApi(): List<Game> {
        val offset = (0..210637).shuffled().last()
        val games = api.getGames(
            "name, cover.image_id, genres.name, platforms.name, involved_companies.company.name",
            offset.toString(),
            "20"
        )
        return if (games.isNotEmpty()) {
            games.map { it.toGame() }
        } else {
            emptyList()
        }
    }

    override suspend fun getGamesFromDatabase(): List<Game> {
        return dao.getGames()
    }


    override suspend fun getGameById(id: Int): Game? {
        return dao.getGameById(id)
    }

    override suspend fun insertGames(games: List<Game>) {
        dao.insertAllGames(games)
    }

    override suspend fun clearGames() {
        dao.deleteAllGames()
    }

}