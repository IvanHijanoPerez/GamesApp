package com.example.gamesapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gamesapp.common.Constants

@Entity(tableName = Constants.TABLE_NAME)
data class Game(
    @PrimaryKey val id: Int,
    val name: String,
    val cover: String,
    val genres: List<String>,
    val platforms: List<String>,
    val companies: List<String>
) {
}