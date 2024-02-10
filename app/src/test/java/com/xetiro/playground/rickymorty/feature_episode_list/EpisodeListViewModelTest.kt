package com.xetiro.playground.rickymorty.feature_episode_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.xetiro.playground.rickymorty.feature_episode_list.data.EpisodeRepository
import com.xetiro.playground.rickymorty.feature_episode_list.data.model.Episode
import com.xetiro.playground.rickymorty.feature_episode_list.ui.EpisodeListUiState
import com.xetiro.playground.rickymorty.feature_episode_list.ui.EpisodeListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class EpisodeListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockedEpisodeReposity: EpisodeRepository

    private lateinit var sut: EpisodeListViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        sut = EpisodeListViewModel(episodeRepository = mockedEpisodeReposity)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun loadEpisodes_success_triggersLoadingState() = runTest {
        // Given
        `when`(mockedEpisodeReposity.getEpisodes()).thenReturn(Result.success(value = emptyList()))
        val loadingStates = mutableListOf<EpisodeListUiState>()
        backgroundScope.launch(UnconfinedTestDispatcher()) {
            sut.uiState.toList(loadingStates)
        }
        // When
        sut.loadEpisodes()
        // Then
        assertEquals(3, loadingStates.size)
        assertEquals(false, loadingStates[0].isLoading)
        assertEquals(true, loadingStates[1].isLoading)
        assertEquals(false, loadingStates[2].isLoading)
    }

    @Test
    fun loadEpisodes_success_changesExistingList() = runTest {
        // Given
        `when`(mockedEpisodeReposity.getEpisodes()).thenReturn(
            Result.success(value = listOf(Episode(), Episode(), Episode()))
        )
        val initialEpisodeListSize = sut.uiState.value.episodeList.size
        // When
        sut.loadEpisodes()
        // Then
        assertEquals(0, initialEpisodeListSize)
        assertEquals(3, sut.uiState.value.episodeList.size)
    }

    @Test
    fun loadEpisodes_failure_doesNotChangeExistingList() = runTest {
        // Given
        `when`(mockedEpisodeReposity.getEpisodes()).thenReturn(Result.failure(Throwable("Testing failure")))
        sut.uiState.value = EpisodeListUiState(
            episodeList = listOf(Episode(), Episode(), Episode())
        )
        // When
        sut.loadEpisodes()
        // Then
        assertEquals(3, sut.uiState.value.episodeList.size)
    }
}