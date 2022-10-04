package com.example.gamesapp.data.remote

import com.example.gamesapp.common.Constants.CLIENT_ID
import com.example.gamesapp.common.Constants.TOKEN
import com.example.gamesapp.data.remote.dto.GameDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface GamesApi {
    @POST("games")
    @Headers("Client-ID: $CLIENT_ID", "Authorization: Bearer $TOKEN")
    suspend fun getGames(@Query("fields") fields: String, @Query("offset") offset: String, @Query("limit") limit: String): List<GameDto>
}