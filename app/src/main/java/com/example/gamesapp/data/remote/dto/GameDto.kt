package com.example.gamesapp.data.remote.dto

import com.example.gamesapp.common.Constants
import com.example.gamesapp.domain.model.Game

data class GameDto(
    val cover: Cover,
    val genres: List<Genre>,
    val id: Int,
    val involved_companies: List<InvolvedCompany>,
    val name: String,
    val platforms: List<Platform>
)

fun GameDto.toGame(): Game {

    var coverValue = Constants.NO_COVER
    var companiesValue = emptyList<String>()
    var genresValue = emptyList<String>()
    var platformsValue = emptyList<String>()

    if (cover != null) {
        coverValue = cover.image_id
    }

    if (involved_companies != null) {
        companiesValue = involved_companies.map { it.company.name }
    }

    if (genres != null) {
        genresValue = genres.map { it.name }
    }

    if (platforms != null) {
        platformsValue = platforms.map { it.name }
    }

    return Game(
        id = id,
        cover = coverValue,
        name = name,
        genres = genresValue,
        companies = companiesValue,
        platforms = platformsValue
    )

}

