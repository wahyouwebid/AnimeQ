package web.id.wahyou.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import web.id.wahyou.core.data.model.anime.DataAnime
import web.id.wahyou.core.data.model.manga.DataManga
import web.id.wahyou.core.data.network.NetworkBoundResource
import web.id.wahyou.core.data.network.ResponseState
import web.id.wahyou.core.data.repository.local.LocalDataSource
import web.id.wahyou.core.data.repository.remote.RemoteDataSource
import web.id.wahyou.core.domain.model.anime.Anime
import web.id.wahyou.core.domain.model.manga.Manga
import web.id.wahyou.core.domain.repository.Repository
import web.id.wahyou.core.domain.state.MainState
import web.id.wahyou.core.mapper.AnimeMapper
import web.id.wahyou.core.mapper.MangaMapper
import web.id.wahyou.core.utils.AppExecutors

class DataRepository (
        private val localDataSource: LocalDataSource,
        private var remoteDataSource: RemoteDataSource,
        private val appExecutors: AppExecutors
) : Repository {

    override fun getAnime(): Flow<MainState<List<Anime>>> =
            object : NetworkBoundResource<List<Anime>, List<DataAnime>>() {
                override fun loadFromDB(): Flow<List<Anime>> {
                    return localDataSource.getAnime().map {
                        AnimeMapper.mapEntitiesToDomain(it)
                    }
                }

                override fun shouldFetch(data: List<Anime>?): Boolean {
                    return true
                }

                override suspend fun createCall(): Flow<ResponseState<List<DataAnime>>> {
                    return remoteDataSource.getAnime()
                }

                override suspend fun saveCallResult(data: List<DataAnime>) {
                    val animeList = AnimeMapper.mapResponsesToEntities(data)
                    localDataSource.insertAnime(animeList)
                }
            }.asFlow()

    override fun getManga(): Flow<MainState<List<Manga>>> =
        object : NetworkBoundResource<List<Manga>, List<DataManga>>() {
            override fun loadFromDB(): Flow<List<Manga>> {
                return localDataSource.getManga().map {
                    MangaMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Manga>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ResponseState<List<DataManga>>> {
                return remoteDataSource.getManga()
            }

            override suspend fun saveCallResult(data: List<DataManga>) {
                val mangaList = MangaMapper.mapResponsesToEntities(data)
                localDataSource.insertManga(mangaList)
            }
        }.asFlow()

    override fun setAnimeFavorite(anime: Anime, isFavorite: Boolean) {
        val animeEntity = AnimeMapper.mapDomainToEntity(anime)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteAnime(animeEntity, isFavorite)
        }
    }

    override fun setMangaFavorite(anime: Manga, isFavorite: Boolean) {
        val mangaEntity = MangaMapper.mapDomainToEntity(anime)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteManga(mangaEntity, isFavorite)
        }
    }

    override fun getAnimeFavorite(): Flow<List<Anime>> {
        return localDataSource.getFavoriteAnime().map { AnimeMapper.mapEntitiesToDomain(it) }
    }

    override fun getmangaFavorite(): Flow<List<Manga>> {
        return localDataSource.getFavoriteManga().map { MangaMapper.mapEntitiesToDomain(it) }
    }
}