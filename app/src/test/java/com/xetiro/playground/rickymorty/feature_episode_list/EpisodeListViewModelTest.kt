package com.xetiro.playground.rickymorty.feature_episode_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.xetiro.playground.rickymorty.feature_episode_list.data.EpisodeRepository
import com.xetiro.playground.rickymorty.feature_episode_list.data.model.Episode
import com.xetiro.playground.rickymorty.feature_episode_list.ui.EpisodeListUiState
import com.xetiro.playground.rickymorty.feature_episode_list.ui.EpisodeListViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


class EpisodeListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    lateinit var mockedEpisodeReposity: EpisodeRepository

    private lateinit var sut: EpisodeListViewModel

    @Before
    fun initSystemUnderTest() {
        MockitoAnnotations.openMocks(this)
        sut = EpisodeListViewModel(episodeRepository = mockedEpisodeReposity)
    }

    @Test
    fun loadEpisodes_success_updatesLoadingState() = runTest {
        // Given
        `when`(mockedEpisodeReposity.getEpisodes()).thenReturn(Result.success(emptyList()))
        val loadingStates = mutableListOf<EpisodeListUiState>()
        // When
        sut.uiState.observeForTesting(stateList = loadingStates) {
            sut.loadEpisodes()
        }
        // Then
        assertEquals(true, loadingStates.first().isLoading)
        assertEquals(false, loadingStates.last().isLoading)
    }

    @Test
    fun loadEpisodes_failure_updatesLoadingState() = runTest {
        // Given
        `when`(mockedEpisodeReposity.getEpisodes()).thenReturn(Result.failure(Throwable("Testing failure")))
        val loadingStates = mutableListOf<EpisodeListUiState>()
        // When
        sut.uiState.observeForTesting(stateList = loadingStates) {
            sut.loadEpisodes()
        }
        // Then
        assertEquals(true, loadingStates.first().isLoading)
        assertEquals(false, loadingStates.last().isLoading)
    }

    @Test
    fun loadEpisodes_success_changesExistingData() = runTest {
        // Given
        `when`(mockedEpisodeReposity.getEpisodes()).thenReturn(
            Result.success(value = listOf(Episode(), Episode(), Episode()))
        )
        val initialEpisodeListSize = sut.uiState.value!!.episodeList.size
        // When
        sut.loadEpisodes()
        // Then
        assertEquals(0, initialEpisodeListSize)
        assertEquals(3, sut.uiState.value!!.episodeList.size)
    }

    @Test
    fun loadEpisodes_failure_doesNotChangeExistingData() = runTest {
        // Given
        `when`(mockedEpisodeReposity.getEpisodes()).thenReturn(Result.failure(Throwable("Testing failure")))
        sut.uiState.value = EpisodeListUiState(
            episodeList = listOf(Episode(), Episode(), Episode())
        )
        // When
        sut.loadEpisodes()
        // Then
        assertEquals(3, sut.uiState.value!!.episodeList.size)
    }

    @Test
    fun loadEpisodes_paginated_success_addsToExistingData() = runTest {
        // Given
        `when`(mockedEpisodeReposity.getEpisodes(page = 2)).thenReturn(
            Result.success(listOf(Episode(), Episode()))
        )
        sut.uiState.value = EpisodeListUiState(
            episodeList = listOf(Episode(), Episode(), Episode())
        )
        // When
        sut.loadEpisodes(page = 2)
        // Then
        assertEquals(5, sut.uiState.value!!.episodeList.size)
    }

    @Test
    fun loadEpisodes_paginated_failure_doesNotChangeExistingData() = runTest {
        // Given
        `when`(mockedEpisodeReposity.getEpisodes(page = 2)).thenReturn(
            Result.failure(Throwable("Testing failure"))
        )
        sut.uiState.value = EpisodeListUiState(
            episodeList = listOf(Episode(), Episode(), Episode())
        )
        // When
        sut.loadEpisodes(page = 2)
        // Then
        assertEquals(3, sut.uiState.value!!.episodeList.size)
    }

    @Test
    fun loadEpisodes_refresh_success_replacesExistingData() = runTest {
        // Given
        `when`(mockedEpisodeReposity.getEpisodes(anyInt())).thenReturn(
            Result.success(listOf(Episode(), Episode()))
        )
        sut.uiState.value = EpisodeListUiState(
            episodeList = listOf(Episode(), Episode(), Episode())
        )
        // When
        sut.refresh()
        // Then
        assertEquals(2, sut.uiState.value!!.episodeList.size)
    }

    @Test
    fun loadEpisodes_refresh_failure_doesNotChangeExistingData() = runTest {
        // Given
        `when`(mockedEpisodeReposity.getEpisodes(anyInt())).thenReturn(
            Result.failure(Throwable("Testing failure"))
        )
        sut.uiState.value = EpisodeListUiState(
            episodeList = listOf(Episode(), Episode(), Episode())
        )
        // When
        sut.refresh()
        // Then
        assertEquals(3, sut.uiState.value!!.episodeList.size)
    }
}