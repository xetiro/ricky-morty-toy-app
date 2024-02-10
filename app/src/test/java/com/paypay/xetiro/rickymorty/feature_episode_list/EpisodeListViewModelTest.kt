package com.paypay.xetiro.rickymorty.feature_episode_list

import com.paypay.xetiro.rickymorty.feature_episode_list.data.EpisodeRepository
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class EpisodeListViewModelTest {

    @Mock
    lateinit var mockedEpisodeReposity: EpisodeRepository

    lateinit var sut: EpisodeListViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }
    @Test
    fun init_loadsFirstPage_success() {
        // Given
        sut = EpisodeListViewModel(
            episodeRepository = mockedEpisodeReposity
        )
        val stateBeforeLoad = sut.uiState
        // When
        sut.init()
        // Then
        assertNotEquals(stateBeforeLoad, sut.uiState)
    }
}