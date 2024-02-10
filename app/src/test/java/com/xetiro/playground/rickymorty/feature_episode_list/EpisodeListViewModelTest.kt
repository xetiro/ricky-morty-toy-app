package com.xetiro.playground.rickymorty.feature_episode_list

import com.xetiro.playground.rickymorty.feature_episode_list.data.EpisodeRepository
import com.xetiro.playground.rickymorty.feature_episode_list.data.model.Episode
import com.xetiro.playground.rickymorty.feature_episode_list.ui.EpisodeListUiState
import com.xetiro.playground.rickymorty.feature_episode_list.ui.EpisodeListViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class EpisodeListViewModelTest {

    @Mock
    lateinit var mockedEpisodeReposity: EpisodeRepository

    private lateinit var sut: EpisodeListViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        sut = EpisodeListViewModel(episodeRepository = mockedEpisodeReposity)
    }
    @Test
    fun loadEpisodes_success_changesExistingList() {
        // Given
        `when`(mockedEpisodeReposity.getEpisodes()).thenReturn(
            Result.success(value = listOf(Episode(), Episode(), Episode()))
        )
        sut.uiState = EpisodeListUiState(
            episodeList = emptyList()
        )
        // When
        sut.loadEpisodes()
        // Then
        assertEquals(3, sut.uiState.episodeList.size)
    }

    @Test
    fun loadEpisodes_failure_doesNotChangeExistingList() {
        // Given
        `when`(mockedEpisodeReposity.getEpisodes()).thenReturn(Result.failure(Throwable("Testing failure")))
        sut.uiState = EpisodeListUiState(
            episodeList = listOf(Episode(), Episode(), Episode())
        )
        // When
        sut.loadEpisodes()
        // Then
        assertEquals(3, sut.uiState.episodeList.size)
    }
}