package com.xetiro.playground.rickymorty.feature_episode_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.xetiro.playground.rickymorty.feature_episode_list.data.EpisodeRepository
import com.xetiro.playground.rickymorty.feature_episode_list.data.model.Episode
import com.xetiro.playground.rickymorty.feature_episode_list.ui.EpisodeListUiState
import com.xetiro.playground.rickymorty.feature_episode_list.ui.EpisodeListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
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

@OptIn(ExperimentalCoroutinesApi::class)
class EpisodeListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

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
        backgroundScope.launch(UnconfinedTestDispatcher()) {
            // Drop the initial value, which is always false
            sut.uiState.drop(1).toList(loadingStates)
        }
        // When
        sut.loadEpisodes()
        // Then
        assertEquals(true, loadingStates.first().isLoading)
        assertEquals(false, loadingStates.last().isLoading)
    }

    @Test
    fun loadEpisodes_failure_updatesLoadingState() = runTest {
        // Given
        `when`(mockedEpisodeReposity.getEpisodes()).thenReturn(Result.failure(Throwable("Testing failure")))
        val loadingStates = mutableListOf<EpisodeListUiState>()
        backgroundScope.launch(UnconfinedTestDispatcher()) {
            // Drop the initial value, which is always false
            sut.uiState.drop(1).toList(loadingStates)
        }
        // When
        sut.loadEpisodes()
        // Then
        assertEquals(true, loadingStates.first().isLoading)
        assertEquals(false, loadingStates.last().isLoading)
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