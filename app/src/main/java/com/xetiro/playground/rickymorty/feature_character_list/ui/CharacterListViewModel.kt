package com.xetiro.playground.rickymorty.feature_character_list.ui

import com.xetiro.playground.rickymorty.feature_character_list.data.model.Character
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CharacterListViewModel {

    private val _uiState = MutableStateFlow(CharacterListUiState())
    val uiState: StateFlow<CharacterListUiState> =  _uiState

    fun loadCharacters() {
        _uiState.value = CharacterListUiState(
            characters = listOf(Character(), Character(), Character())
        )
    }
}
