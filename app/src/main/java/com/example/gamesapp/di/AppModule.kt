package com.example.gamesapp.di

import android.content.Context
import androidx.room.Room
import com.example.gamesapp.common.Constants
import com.example.gamesapp.common.Constants.DATABASE_NAME
import com.example.gamesapp.data.local.GameDatabase
import com.example.gamesapp.data.remote.GamesApi
import com.example.gamesapp.data.repository.GameRepositoryImpl
import com.example.gamesapp.domain.repository.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGamesApi(): GamesApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(GamesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, GameDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideGameRepository(api: GamesApi, db: GameDatabase): GameRepository {
        return GameRepositoryImpl(api, db.gameDao)
    }
}