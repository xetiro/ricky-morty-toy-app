package com.xetiro.playground.rickymorty.feature_episode_list.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xetiro.playground.rickymorty.feature_episode_list.data.EpisodeRepository
import kotlinx.coroutines.launch

private const val DEBUG_TAG = "EpisodeListViewModel"
class EpisodeListViewModel(
    private val episodeRepository: EpisodeRepository
): ViewModel() {

    var uiState = MutableLiveData(EpisodeListUiState())

    private var nextPage = 0

    fun load() = loadEpisodes(page = 0)

    fun loadMore() {
        nextPage += 1
        loadEpisodes(page = nextPage)
    }

    fun refresh() {
        nextPage = 0
        loadEpisodes(page = 0)
    }

    private fun loadEpisodes(page: Int) = viewModelScope.launch {
        Log.d(DEBUG_TAG, "loadEpisodes = ${uiState.value}")
        uiState.value = uiState.value?.copy(isLoading = true)
        val result = episodeRepository.getEpisodes(page = page)
        if (result.isSuccess) {
            val episodeListResult = if(page == 0) {
                result.getOrDefault(emptyList())
            } else {
                uiState.value!!.episodeList + result.getOrDefault(emptyList())
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