package com.xetiro.playground.rickymorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.xetiro.playground.rickymorty.feature_episode_list.data.EpisodeRepositoryImpl
import com.xetiro.playground.rickymorty.feature_episode_list.ui.EpisodeListScreen
import com.xetiro.playground.rickymorty.feature_episode_list.ui.EpisodeListViewModel
import com.xetiro.playground.rickymorty.network.client.NetworkClient

/**
 * Main host activity. The screen(s) are implemented in Compose.
 *
 * Created by xetiro (aka Ruben Geraldes) on 2024/02/12.
 */
class MainActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val episodeRepository = EpisodeRepositoryImpl(episodeApi = NetworkClient.provideEpisodeApi())
            val viewModel = EpisodeListViewModel(episodeRepository = episodeRepository)
            EpisodeListScreen(viewModel)

            viewModel.load()
        }
    }
}