package com.xetiro.playground.rickymorty.feature_episode_list.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState


/**
 * Description...
 *
 * Created by xetiro (aka Ruben Geraldes) on 2024/02/12.
 */
@Composable
fun EpisodeListScreen(viewModel: EpisodeListViewModel) {
    val uiState = viewModel.uiState.observeAsState().value!!
    if(uiState.isLoading) {
        Text(text = "LOADING...")
    } else {
        Text(text = "Fetched ${uiState.episodeList.size} Episodes")
    }
}