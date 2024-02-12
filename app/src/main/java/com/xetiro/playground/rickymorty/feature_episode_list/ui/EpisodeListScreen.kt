package com.xetiro.playground.rickymorty.feature_episode_list.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xetiro.playground.rickymorty.feature_episode_list.data.model.Episode


/**
 * UI of Episode List Screen
 *
 * Created by xetiro (aka Ruben Geraldes) on 2024/02/12.
 */
@Composable
fun EpisodeListScreen(viewModel: EpisodeListViewModel) {
    val uiState = viewModel.uiState.observeAsState().value!!
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.padding(128.dp)
            )
        } else {
            EpisodeListView(uiState.episodeList)
        }
    }
}

@Composable
fun EpisodeListView(episodeList: List<Episode>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(episodeList.size) {
            EpisodeCardView(episodeList[it])
        }
    }
}

@Composable
fun EpisodeCardView(episode: Episode) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = episode.name,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = episode.episode,
            modifier = Modifier.padding(8.dp)
        )
    }
}