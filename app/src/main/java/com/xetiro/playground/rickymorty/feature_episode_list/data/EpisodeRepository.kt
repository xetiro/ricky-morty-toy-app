package com.xetiro.playground.rickymorty.feature_episode_list.data

import com.xetiro.playground.rickymorty.feature_episode_list.data.model.Episode

interface EpisodeRepository {
    fun getEpisodes(): Result<List<Episode>>
}