package com.example.gamesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gamesapp.common.Converters
import com.example.gamesapp.data.local.dao.GameDao
import com.example.gamesapp.domain.model.Game

@Database(
    entities = [Game::class],
    version = 4,
)
@TypeConverters(Converters::class)
abstract class GameDatabase: RoomDatabase() {
    abstract  val gameDao: GameDao
}