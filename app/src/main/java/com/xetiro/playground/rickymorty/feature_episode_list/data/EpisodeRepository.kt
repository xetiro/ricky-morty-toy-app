package com.xetiro.playground.rickymorty.feature_episode_list.data

import com.xetiro.playground.rickymorty.feature_episode_list.data.model.Episode

interface EpisodeRepository {
    suspend fun getEpisodes(page: Int = 0): Result<List<Episode>>
}