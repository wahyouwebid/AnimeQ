package web.id.wahyou.core.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import web.id.wahyou.core.data.model.anime.ResponseAnime
import web.id.wahyou.core.data.model.manga.ResponseManga

interface ApiService {

    @GET("anime")
    suspend fun getAnime(
            @Query("page[limit]") page: Int = 20,
    ): ResponseAnime

    @GET("manga")
    suspend fun getManga(
            @Query("page[limit]") page: Int = 20,
    ): ResponseManga
}