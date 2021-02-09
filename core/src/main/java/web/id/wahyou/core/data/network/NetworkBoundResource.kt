package web.id.wahyou.core.data.network

import kotlinx.coroutines.flow.*
import web.id.wahyou.core.domain.state.MainState

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<MainState<ResultType>> = flow {
        emit(MainState.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(MainState.Loading())
            when (val apiResponse = createCall().first()) {
                is ResponseState.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { MainState.Success(it) })
                }
                is ResponseState.Empty -> {
                    emitAll(loadFromDB().map { MainState.Success(it) })
                }
                is ResponseState.Error -> {
                    onFetchFailed()
                    emit(MainState.Error(apiResponse.errorMessage, null))
                }
            }
        } else {
            emitAll(loadFromDB().map { MainState.Success(it) })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ResponseState<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<MainState<ResultType>> = result

}