package com.example.gamesapp.domain.use_case.get_game

import com.example.gamesapp.domain.model.Game
import com.example.gamesapp.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGameUseCase @Inject constructor(
    private val repository: GameRepository
) {

    suspend operator fun invoke(id: Int): Game? {
        return repository.getGameById(id)
    }
}