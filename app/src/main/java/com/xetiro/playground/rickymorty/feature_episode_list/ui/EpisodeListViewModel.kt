package com.xetiro.playground.rickymorty.feature_episode_list.ui

import com.xetiro.playground.rickymorty.feature_episode_list.data.EpisodeRepository


class EpisodeListViewModel(
    private val episodeRepository: EpisodeRepository
) {

    var uiState = EpisodeListUiState()

    fun loadEpisodes() {
        val result = episodeRepository.getEpisodes()
        if(result.isSuccess) {
            uiState = uiState.copy(
                episodeList = result.getOrDefault(emptyList())
            )
        }
    }
}
