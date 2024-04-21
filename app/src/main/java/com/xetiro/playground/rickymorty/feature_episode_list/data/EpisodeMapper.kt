package com.xetiro.playground.rickymorty.feature_episode_list.data

import com.xetiro.playground.rickymorty.feature_episode_list.data.dto.EpisodeDto
import com.xetiro.playground.rickymorty.feature_episode_list.domain.model.Episode

/**
 * Description...
 *
 * Created by xetiro (aka Ruben Geraldes) on 2024/04/21.
 */
fun EpisodeDto.toDomain(): Episode {
    return Episode(
        id = id ?: 0,
        name = name.orEmpty(),
        airDate = air_date.orEmpty(),
        episode = episode.orEmpty(),
        url = url.orEmpty(),
        characterUrls = characters.orEmpty(),
        created = created.orEmpty()
    )
}