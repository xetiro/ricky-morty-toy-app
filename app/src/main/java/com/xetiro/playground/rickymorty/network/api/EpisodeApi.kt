package com.xetiro.playground.rickymorty.network.api

import com.xetiro.playground.rickymorty.network.api.dto.AllEpisodesDto
import retrofit2.http.GET

/**
 * https://rickandmortyapi.com/documentation/#episode-schema
 * https://rickandmortyapi.com/api/episode
 *
 * Created by xetiro (aka Ruben Geraldes) on 2024/02/11.
 */
interface EpisodeApi {
    @GET("episode")
    suspend fun getAllEpisodes(): AllEpisodesDto
}