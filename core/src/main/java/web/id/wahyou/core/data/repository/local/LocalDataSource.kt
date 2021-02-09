package web.id.wahyou.core.data.repository.local

import kotlinx.coroutines.flow.Flow
import web.id.wahyou.core.data.repository.local.database.dao.AnimeQDao
import web.id.wahyou.core.data.repository.local.entity.AnimeEntity
import web.id.wahyou.core.data.repository.local.entity.MangaEntity

class LocalDataSource (
        private val animeQDao: AnimeQDao
) {
    fun getAnime(): Flow<List<AnimeEntity>> = animeQDao.getAnime()

    fun getManga(): Flow<List<MangaEntity>> = animeQDao.getManga()

    fun getFavoriteAnime(): Flow<List<AnimeEntity>> = animeQDao.getFavoriteAnime()

    fun getFavoriteManga(): Flow<List<MangaEntity>> = animeQDao.getFavoriteManga()

    suspend fun insertAnime(anime: List<AnimeEntity>) = animeQDao.insertAnime(anime)

    suspend fun insertManga(manga: List<MangaEntity>) = animeQDao.insertManga(manga)

    fun setFavoriteAnime(anime: AnimeEntity, isFavorite: Boolean) {
        anime.isFavorite = isFavorite
        animeQDao.updateFavoriteAnime(anime)
    }
    fun setFavoriteManga(manga: MangaEntity, isFavorite: Boolean) {
        manga.isFavorite = isFavorite
        animeQDao.updateFavoriteManga(manga)
    }
}