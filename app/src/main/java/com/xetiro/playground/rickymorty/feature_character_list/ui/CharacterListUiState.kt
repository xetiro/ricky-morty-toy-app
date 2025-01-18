package com.xetiro.playground.rickymorty.feature_character_list.ui

import com.xetiro.playground.rickymorty.feature_character_list.data.model.Character

data class CharacterListUiState(
    val characters: List<Character> = emptyList(),
    val toastMessage: String = ""
)
