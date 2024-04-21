package com.xetiro.playground.rickymorty.feature_episode_list.domain

import com.xetiro.playground.rickymorty.feature_episode_list.domain.model.Episode

interface EpisodeRepository {
    suspend fun getEpisodes(page: Int = 0): Result<List<Episode>>
}