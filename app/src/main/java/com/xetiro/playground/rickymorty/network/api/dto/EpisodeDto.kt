package com.xetiro.playground.rickymorty.network.api.dto

import com.xetiro.playground.rickymorty.feature_episode_list.data.model.Episode

/**
 * Description...
 *
 * Created by xetiro (aka Ruben Geraldes) on 2024/02/11.
 */
data class EpisodeDto(
    val id: Int? = null,
    val name: String? = null,
    val air_date: String? = null,
    val episode: String? = null,
    val characters: List<String>? = null,
    val url: String? = null,
    val created: String? = null
)

fun EpisodeDto.toDomain(): Episode {
    return Episode(
        id = id ?: 0,
        name = name.orEmpty(),
        airDate = air_date.orEmpty(),
        episode = episode.orEmpty(),
        url = url.orEmpty(),
        created = created.orEmpty()
    )
}