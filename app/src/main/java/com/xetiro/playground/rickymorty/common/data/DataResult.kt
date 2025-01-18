package com.xetiro.playground.rickymorty.common.data

sealed class DataResult<out T> {
    class Success<T>(val data: T): DataResult<T>()
    class Failure(val error: DataError): DataResult<Nothing>()
}