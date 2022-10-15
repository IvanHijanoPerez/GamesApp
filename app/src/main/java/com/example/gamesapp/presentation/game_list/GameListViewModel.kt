package com.example.gamesapp.presentation.game_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamesapp.domain.use_case.get_games.GetGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase
) : ViewModel() {

    val gameState = MutableLiveData<GameListState>()

    init {
        getGames(false)
    }

    fun getGames(refresh: Boolean) {
        viewModelScope.launch {
            gameState.postValue(GameListState(isLoading = true))
            val result = getGamesUseCase(refresh)

            if (result.isNotEmpty()) {
                gameState.postValue(GameListState(games = result))
            } else {
                gameState.postValue(GameListState(error = "Error"))
            }
        }
    }

    fun loadMoreGames() {
        viewModelScope.launch {

            val result = getGamesUseCase.loadMoreGames()
            if (result.isNotEmpty()) {
                gameState.postValue(GameListState(isLoading = true))
                delay(1000)
                gameState.postValue(GameListState(games = result))
            }
        }
    }
}

