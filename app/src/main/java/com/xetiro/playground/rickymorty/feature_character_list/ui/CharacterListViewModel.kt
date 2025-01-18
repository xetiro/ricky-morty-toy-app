package com.xetiro.playground.rickymorty.feature_character_list.ui

import com.xetiro.playground.rickymorty.feature_character_list.data.CharacterListRepository
import com.xetiro.playground.rickymorty.feature_character_list.data.DataResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CharacterListViewModel(val repository: CharacterListRepository) {

    private val _uiState = MutableStateFlow(CharacterListUiState())
    val uiState: StateFlow<CharacterListUiState> =  _uiState

    fun loadCharacters() {
        val result = repository.loadCharacters()
        when(result) {
            is DataResult.Success -> {
                _uiState.value = CharacterListUiState(
                    characters = result.data
                )
            }
            is DataResult.Failure -> Unit
        }

    }
}
