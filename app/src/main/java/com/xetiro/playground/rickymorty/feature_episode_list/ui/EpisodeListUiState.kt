package com.xetiro.playground.rickymorty.feature_episode_list.ui

import com.xetiro.playground.rickymorty.feature_episode_list.data.model.Episode

data class EpisodeListUiState(
    val isLoading: Boolean = false,
    val episodeList: List<Episode> = emptyList()
)











