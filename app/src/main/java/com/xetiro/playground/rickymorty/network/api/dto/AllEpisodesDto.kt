package com.xetiro.playground.rickymorty.network.api.dto

/**
 * Description...
 *
 * Created by xetiro (aka Ruben Geraldes) on 2024/02/11.
 */
data class AllEpisodesDto(
    val info: ResponseInfo = ResponseInfo(),
    val results: List<EpisodeDto> = emptyList()
)

data class ResponseInfo(
    val count: Int = 0,
    val pages: Int = 0,
    val next: String? = null,
    val prev: String? = null
)