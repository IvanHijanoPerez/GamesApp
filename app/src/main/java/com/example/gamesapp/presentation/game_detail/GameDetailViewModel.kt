package com.example.gamesapp.presentation.game_detail

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamesapp.domain.use_case.get_game.GetGameUseCase
import com.example.gamesapp.presentation.game_list.GameListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val getGameUseCase: GetGameUseCase
) : ViewModel() {

    val gameDetailState = MutableLiveData<GameDetailState>()

    fun onCreate(id: Int) {
        viewModelScope.launch {
            val result = getGameUseCase(id)
            if (result == null) {
                gameDetailState.postValue(GameDetailState())
            } else {
                gameDetailState.postValue(
                    GameDetailState(
                        id = result.id,
                        name = result.name,
                        cover = result.cover,
                        genres = result.genres,
                        platforms = result.platforms,
                        companies = result.companies
                    )
                )
            }
        }
    }
}