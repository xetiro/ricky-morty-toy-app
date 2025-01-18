package com.xetiro.playground.rickymorty.feature_character_list

import com.xetiro.playground.rickymorty.feature_character_list.data.model.Character
import com.xetiro.playground.rickymorty.feature_character_list.ui.CharacterListUiState
import com.xetiro.playground.rickymorty.feature_character_list.ui.CharacterListViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CharacterListTest {

    @Test
    fun `starts with empty list of characters`() {
        // Given
        val sut = CharacterListViewModel()
        // Then
        assertEquals(true, sut.uiState.value.characters.isEmpty())
    }

    @Test
    fun `load character list with data success`() {
        // Given
        val sut = CharacterListViewModel()
        val expectedData = listOf(Character(), Character(), Character())
        val expectedUiState = CharacterListUiState(characters = expectedData)
        // When
        sut.loadCharacters()
        // Then
        assertEquals(expectedUiState, sut.uiState.value)
    }
}