package com.xetiro.playground.rickymorty.feature_character_list

import com.xetiro.playground.rickymorty.feature_character_list.data.CharacterListRepository
import com.xetiro.playground.rickymorty.feature_character_list.data.DataResult
import com.xetiro.playground.rickymorty.feature_character_list.data.model.Character
import com.xetiro.playground.rickymorty.feature_character_list.ui.CharacterListUiState
import com.xetiro.playground.rickymorty.feature_character_list.ui.CharacterListViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class CharacterListTest {

    @Mock
    private lateinit var mockedRepository: CharacterListRepository

    private lateinit var sut: CharacterListViewModel

    init {
        MockitoAnnotations.openMocks(this)
    }

    @Before
    fun initSut() {
        sut = CharacterListViewModel(mockedRepository)
    }

    @Test
    fun `starts with empty list of characters`() {
        // Given
        val expectedUiState = CharacterListUiState(characters = emptyList())

        // Then
        assertEquals(expectedUiState, sut.uiState.value)
    }

    @Test
    fun `load character list success result with data`() {
        // Given
        val data = listOf(Character(), Character(), Character())
        val expectedUiState = CharacterListUiState(characters = data)
        `when`(mockedRepository.loadCharacters())
            .thenReturn(DataResult.Success(data))

        // When
        sut.loadCharacters()

        // Then
        assertEquals(expectedUiState, sut.uiState.value)
    }

    @Test
    fun `load character list success result with empty data`() {
        // Given
        val expectedUiState = CharacterListUiState(characters = emptyList())
        `when`(mockedRepository.loadCharacters())
            .thenReturn(DataResult.Success(emptyList()))

        // When
        sut.loadCharacters()

        // Then
        assertEquals(expectedUiState, sut.uiState.value)
    }

    @Test
    fun `load character list error keeps existing data`() {
        // Given
        val data = listOf(Character(), Character(), Character())
        val expectedUiState = CharacterListUiState(characters = data)
        `when`(mockedRepository.loadCharacters())
            .thenReturn(DataResult.Success(data))
            .thenReturn(DataResult.Failure(Throwable()))

        // When
        sut.loadCharacters()

        // Then
        assertEquals(expectedUiState, sut.uiState.value)
    }

}