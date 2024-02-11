package com.xetiro.playground.rickymorty.feature_episode_list.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xetiro.playground.rickymorty.feature_episode_list.data.EpisodeRepository
import kotlinx.coroutines.launch

class EpisodeListViewModel(
    private val episodeRepository: EpisodeRepository
): ViewModel() {

    var uiState = MutableLiveData(EpisodeListUiState())

    fun loadEpisodes() = viewModelScope.launch {
        uiState.value = uiState.value?.copy(isLoading = true)
        val result = episodeRepository.getEpisodes()
        if (result.isSuccess) {
            uiState.value =
                uiState.value?.copy(
                    isLoading = false,
                    episodeList = result.getOrDefault(emptyList())
                )
        } else {
            uiState.value = uiState.value?.copy(isLoading = false)
        }
    }
}
