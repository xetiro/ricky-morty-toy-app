package com.xetiro.playground.rickymorty.feature_episode_list.data

import com.xetiro.playground.rickymorty.feature_episode_list.data.model.Episode
import com.xetiro.playground.rickymorty.network.api.EpisodeApi
import com.xetiro.playground.rickymorty.network.api.dto.toDomain

/**
 * A repository for the Episode resource.
 *
 * Created by xetiro (aka Ruben Geraldes) on 2024/02/11.
 */
class EpisodeRepositoryImpl(
    val episodeApi: EpisodeApi
): EpisodeRepository {
    override suspend fun getEpisodes(page: Int): Result<List<Episode>> {
        return try {
            val response = episodeApi.getAllEpisodes()
            val data = response.results.map { it.toDomain() }
            Result.success(data)
        } catch(throwable: Throwable) {
            Result.failure(throwable)
        }
    }
}