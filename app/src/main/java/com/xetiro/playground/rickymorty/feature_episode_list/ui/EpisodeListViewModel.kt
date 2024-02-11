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

    fun loadEpisodes(page: Int = 0) = viewModelScope.launch {
        uiState.value = uiState.value?.copy(isLoading = true)
        val result = episodeRepository.getEpisodes(page = page)
        if (result.isSuccess) {
            val episodeListResult = if(page > 0) {
                uiState.value!!.episodeList + result.getOrDefault(emptyList())
            } else {
                result.getOrDefault(emptyList())
            }
            uiState.value =
                uiState.value?.copy(
                    isLoading = false,
                    episodeList = episodeListResult
                )
        } else {
            uiState.value = uiState.value?.copy(isLoading = false)
        }
    }
}
