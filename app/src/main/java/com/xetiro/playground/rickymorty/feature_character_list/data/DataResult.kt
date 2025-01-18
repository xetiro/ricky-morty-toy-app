package com.xetiro.playground.rickymorty.feature_character_list.data

sealed class DataResult<out T> {
    class Success<T>(val data: T): DataResult<T>()
    class Failure(val throwable: Throwable): DataResult<Nothing>()
}