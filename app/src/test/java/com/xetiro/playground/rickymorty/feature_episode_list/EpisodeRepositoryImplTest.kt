package com.xetiro.playground.rickymorty.feature_episode_list

import com.xetiro.playground.rickymorty.feature_episode_list.data.EpisodeRepository
import com.xetiro.playground.rickymorty.feature_episode_list.data.EpisodeRepositoryImpl
import com.xetiro.playground.rickymorty.network.api.EpisodeApi
import com.xetiro.playground.rickymorty.network.api.dto.AllEpisodesDto
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.lang.RuntimeException

/**
 * Description...
 *
 * Created by xetiro (aka Ruben Geraldes) on 2024/02/11.
 */
class EpisodeRepositoryImplTest {

    @Mock
    lateinit var mockedEpisodeApi: EpisodeApi

    private lateinit var sut: EpisodeRepository

    @Before
    fun initSut() {
        MockitoAnnotations.openMocks(this)
        sut = EpisodeRepositoryImpl(episodeApi = mockedEpisodeApi)
    }

    @Test
    fun getEpisodes_firstPage_success_result() = runTest {
        // Given
        `when`(mockedEpisodeApi.getAllEpisodes()).thenReturn(AllEpisodesDto())
        // When
        val expectedResult = sut.getEpisodes(page = 0)
        // Then
        assertEquals(true, expectedResult.isSuccess)
        assertNotNull(expectedResult.getOrNull())
    }

    @Test
    fun getEpisodes_firstPage_failure_result() = runTest {
        // Given
        `when`(mockedEpisodeApi.getAllEpisodes()).thenThrow(RuntimeException("Test failure"))
        // When
        val expectedResult = sut.getEpisodes(page = 0)
        // Then
        assertEquals(true, expectedResult.isFailure)
        assertNull(expectedResult.getOrNull())
    }
}