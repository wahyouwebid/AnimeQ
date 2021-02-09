package web.id.wahyou.core.data.repository.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import web.id.wahyou.core.data.model.anime.DataAnime
import web.id.wahyou.core.data.model.manga.DataManga
import web.id.wahyou.core.data.network.ApiService
import web.id.wahyou.core.data.network.ResponseState

class RemoteDataSource (
    private val apiService: ApiService
) {
    fun getAnime(): Flow<ResponseState<List<DataAnime>>> {
        return flow {
            try {
                val response = apiService.getAnime()
                val data = response.data
                if (data.isNotEmpty()) {
                    emit(ResponseState.Success(response.data))
                } else {
                    emit(ResponseState.Empty)
                }
            } catch (e: Exception) {
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getManga(): Flow<ResponseState<List<DataManga>>> {
        return flow {
            try {
                val response = apiService.getManga()
                val data = response.data
                if (data.isNotEmpty()) {
                    emit(ResponseState.Success(response.data))
                } else {
                    emit(ResponseState.Empty)
                }
            } catch (e: Exception) {
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}