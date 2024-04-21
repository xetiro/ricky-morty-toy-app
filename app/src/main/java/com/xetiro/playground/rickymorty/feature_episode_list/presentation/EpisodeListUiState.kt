package com.xetiro.playground.rickymorty.feature_episode_list.presentation

import com.xetiro.playground.rickymorty.feature_episode_list.domain.model.Episode

data class EpisodeListUiState(
    val isLoading: Boolean = false,
    val episodeList: List<Episode> = emptyList()
)











