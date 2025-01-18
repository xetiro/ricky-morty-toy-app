package com.xetiro.playground.rickymorty.feature_character_list

import com.xetiro.playground.rickymorty.common.data.DataError
import com.xetiro.playground.rickymorty.feature_character_list.data.CharacterListRepository
import com.xetiro.playground.rickymorty.common.data.DataResult
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
        // Then
        val expectedUiState = CharacterListUiState(characters = emptyList())
        assertEquals(expectedUiState, sut.uiState.value)
    }

    @Test
    fun `load character list success result with data`() {
        // Given
        val data = listOf(Character(), Character(), Character())
        `when`(mockedRepository.loadCharacters())
            .thenReturn(DataResult.Success(data))

        // When
        sut.loadCharacters()

        // Then
        val expectedUiState = CharacterListUiState(characters = data)
        assertEquals(expectedUiState, sut.uiState.value)
    }

    @Test
    fun `load character list success result with empty data`() {
        // Given
        `when`(mockedRepository.loadCharacters())
            .thenReturn(DataResult.Success(emptyList()))

        // When
        sut.loadCharacters()

        // Then
        val expectedUiState = CharacterListUiState(characters = emptyList())
        assertEquals(expectedUiState, sut.uiState.value)
    }

    @Test
    fun `load character list failure due to server error keeps existing data`() {
        // Given
        val data = listOf(Character(), Character(), Character())
        `when`(mockedRepository.loadCharacters())
            .thenReturn(DataResult.Success(data))
            .thenReturn(DataResult.Failure(DataError.SERVER_ERROR))
        sut.loadCharacters()

        // When
        sut.loadCharacters()

        // Then
        val expectedUiState = CharacterListUiState(characters = data)
        assertEquals(expectedUiState, sut.uiState.value)
    }

    @Test
    fun `load character list failure due to timeout keeps existing data`() {
        // Given
        val data = listOf(Character(), Character(), Character())
        `when`(mockedRepository.loadCharacters())
            .thenReturn(DataResult.Success(data))
            .thenReturn(DataResult.Failure(DataError.TIMEOUT))
        sut.loadCharacters()

        // When
        sut.loadCharacters()

        // Then
        val expectedUiState = CharacterListUiState(characters = data)
        assertEquals(expectedUiState, sut.uiState.value)
    }

    @Test
    fun `load character list failure due to no network triggers toast message`() {
        // Given
        val expectedUiState = CharacterListUiState(
            toastMessage = "No network. Please connect and try again"
        )
        `when`(mockedRepository.loadCharacters())
            .thenReturn(DataResult.Failure(DataError.NO_NETWORK))

        // When
        sut.loadCharacters()

        // Then
        assertEquals(expectedUiState, sut.uiState.value)
    }

    @Test
    fun `refresh character list success replaces existing data`() {
        // Given
        val data = listOf(Character(), Character(), Character())
        val refreshData = listOf(Character())
        `when`(mockedRepository.loadCharacters())
            .thenReturn(DataResult.Success(data))
            .thenReturn(DataResult.Success(refreshData))
        sut.loadCharacters()

        // When
        sut.loadCharacters()

        // Then
        val expectedUiState = CharacterListUiState(characters = refreshData)
        assertEquals(expectedUiState, sut.uiState.value)
    }

}