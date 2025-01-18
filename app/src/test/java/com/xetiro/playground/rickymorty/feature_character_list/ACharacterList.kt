package com.xetiro.playground.rickymorty.feature_character_list

import com.xetiro.playground.rickymorty.feature_character_list.ui.CharacterListViewModel
import junit.framework.TestCase.assertTrue
import org.junit.Test

class ACharacterList {

    @Test
    fun `starts with empty list of characters`() {
        // When
        val sut = CharacterListViewModel()
        // Then
        assertTrue(sut.uiState.characters.isEmpty())
    }
}