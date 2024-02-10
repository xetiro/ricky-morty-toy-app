package com.paypay.xetiro.rickymorty.feature_episode_list.data

import com.paypay.xetiro.rickymorty.feature_episode_list.data.model.Episode

interface EpisodeRepository {
    fun getEpisodes(): Result<List<Episode>>
}