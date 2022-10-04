package com.example.gamesapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gamesapp.common.Constants
import com.example.gamesapp.data.remote.dto.Cover

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