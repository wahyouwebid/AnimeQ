package web.id.wahyou.core.domain.repository

import kotlinx.coroutines.flow.Flow
import web.id.wahyou.core.domain.model.anime.Anime
import web.id.wahyou.core.domain.model.manga.Manga
import web.id.wahyou.core.domain.state.MainState

interface Repository {
    fun getAnime(): Flow<MainState<List<Anime>>>
    fun getManga(): Flow<MainState<List<Manga>>>
    fun setAnimeFavorite(anime: Anime, isFavorite: Boolean)
    fun setMangaFavorite(anime: Manga, isFavorite: Boolean)
    fun getAnimeFavorite(): Flow<List<Anime>>
    fun getmangaFavorite(): Flow<List<Manga>>
}