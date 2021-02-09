package web.id.wahyou.core.data.network

sealed class ResponseState<out R> {
    data class Success<out T>(val data: T) : ResponseState<T>()
    data class Error(val errorMessage: String) : ResponseState<Nothing>()
    object Empty : ResponseState<Nothing>()
}