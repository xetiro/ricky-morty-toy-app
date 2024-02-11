package com.xetiro.playground.rickymorty.network.api

import com.xetiro.playground.rickymorty.network.api.dto.AllEpisodesDto

/**
 * Description...
 *
 * Created by xetiro (aka Ruben Geraldes) on 2024/02/11.
 */
interface EpisodeApi {
    suspend fun getAllEpisodes(): AllEpisodesDto
}