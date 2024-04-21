package com.xetiro.playground.rickymorty.network.client

import com.xetiro.playground.rickymorty.network.api.EpisodeApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit client implementation.
 *
 * Created by xetiro (aka Ruben Geraldes) on 2024/02/12.
 */
private const val BASE_URL = "https://rickandmortyapi.com/api/"
object NetworkClient {

    private val client = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun provideEpisodeApi(): EpisodeApi {
        return client.create(EpisodeApi::class.java)
    }
}