package com.xetiro.playground.rickymorty.feature_character_list.ui

import com.xetiro.playground.rickymorty.common.data.DataError
import com.xetiro.playground.rickymorty.feature_character_list.data.CharacterListRepository
import com.xetiro.playground.rickymorty.common.data.DataResult
import com.xetiro.playground.rickymorty.common.ui.ErrorMessages
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
            is DataResult.Failure -> {
                when(result.error) {
                    DataError.NO_NETWORK -> {
                        _uiState.value = CharacterListUiState(
                            toastMessage = ErrorMessages.NO_NETWORK
                        )
                    }

                    DataError.TIMEOUT,
                    DataError.SERVER_ERROR -> Unit
                }
            }
        }
    }
}
