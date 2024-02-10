package com.paypay.xetiro.rickymorty.feature_episode_list

import com.paypay.xetiro.rickymorty.feature_episode_list.data.EpisodeRepository

class EpisodeListViewModel(
    private val episodeRepository: EpisodeRepository
) {

    var uiState = EpisodeListUiState()

    fun init() {
        uiState = EpisodeListUiState()
    }

}
