package com.xetiro.playground.rickymorty.feature_episode_list.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xetiro.playground.rickymorty.feature_episode_list.data.EpisodeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow


class EpisodeListViewModel(
    private val episodeRepository: EpisodeRepository
) {

    var uiState = MutableStateFlow(EpisodeListUiState())

    suspend fun loadEpisodes() {
        uiState.emit(uiState.value.copy(isLoading = true))
      //  uiState.value = uiState.value?.copy(isLoading = true)
        val result = episodeRepository.getEpisodes()
        if(result.isSuccess) {
            uiState.emit(
                uiState.value.copy(
                    isLoading = false,
                    episodeList = result.getOrDefault(emptyList())
                )
            )
        }
    }
}
