package web.id.wahyou.core.domain.state

sealed class MainState<out T>(val data: T? = null, val message: String? = null) {
    class Success<out T>(data: T) : MainState<T>(data)
    class Loading<out T>(data: T? = null) : MainState<T>(data)
    class Error<out T>(message: String, data: T? = null) : MainState<T>(data, message)
}
