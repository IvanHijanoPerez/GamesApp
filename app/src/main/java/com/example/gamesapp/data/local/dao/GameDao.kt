package com.example.gamesapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gamesapp.domain.model.Game

@Dao
interface GameDao {

    @Query("SELECT * FROM games_table ORDER BY name")
    suspend fun getGames(): List<Game>

    @Query("SELECT * FROM games_table WHERE id = :id")
    suspend fun getGameById(id: Int): Game?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllGames(games: List<Game>)

    @Query("DELETE FROM games_table")
    suspend fun deleteAllGames()
}