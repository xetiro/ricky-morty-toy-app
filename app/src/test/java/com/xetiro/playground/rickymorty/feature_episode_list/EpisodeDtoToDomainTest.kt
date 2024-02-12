package com.xetiro.playground.rickymorty.feature_episode_list

import com.xetiro.playground.rickymorty.feature_episode_list.data.model.Episode
import com.xetiro.playground.rickymorty.network.api.dto.EpisodeDto
import com.xetiro.playground.rickymorty.network.api.dto.toDomain
import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * Description...
 *
 * Created by xetiro (aka Ruben Geraldes) on 2024/02/12.
 */
class EpisodeDtoToDomainTest {

    @Test
    fun dtoToDomain_maps_defaultValues() {
        // Given
        val dtoModel = EpisodeDto()
        val expectedDomainModel = Episode()
        // When
        val actualDomainModel = dtoModel.toDomain()
        // Then
        assertEquals(expectedDomainModel, actualDomainModel)
    }

    @Test
    fun dtoToDomain_maps_allValues() {
        // Given
        val dtoModel = EpisodeDto(
            id = 123,
            name = "Some Name",
            air_date = "12/02/2024",
            episode = "S0E0",
            url = "www.somelink.com",
            created = "01/01/2000"
        )
        val expectedDomainModel = Episode(
            id = 123,
            name = "Some Name",
            airDate = "12/02/2024",
            episode = "S0E0",
            url = "www.somelink.com",
            created = "01/01/2000"
        )
        // When
        val actualDomainModel = dtoModel.toDomain()
        // Then
        assertEquals(expectedDomainModel, actualDomainModel)
    }

    @Test
    fun dtoToDomain_maps_definedAndDefaultValues() {
        // Given
        val dtoModel = EpisodeDto(
            id = 123,
            name = "Some Name",
            created = "01/01/2000"
        )
        val expectedDomainModel = Episode(
            id = 123,
            name = "Some Name",
            airDate = "",
            episode = "",
            url = "",
            created = "01/01/2000"
        )
        // When
        val actualDomainModel = dtoModel.toDomain()
        // Then
        assertEquals(expectedDomainModel, actualDomainModel)
    }
}