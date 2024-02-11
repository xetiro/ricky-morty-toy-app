package com.xetiro.playground.rickymorty.network.api.dto

import com.xetiro.playground.rickymorty.feature_episode_list.data.model.Episode

/**
 * Description...
 *
 * Created by xetiro (aka Ruben Geraldes) on 2024/02/11.
 */
data class EpisodeDto(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
)

fun EpisodeDto.toDomain(): Episode {
    return Episode(
        id = id,
        name = name,
        airDate = air_date,
        episode = episode,
        url = url,
        created = created
    )
}