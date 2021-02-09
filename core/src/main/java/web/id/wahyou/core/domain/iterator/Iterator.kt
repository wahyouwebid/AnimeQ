package web.id.wahyou.core.domain.iterator

import kotlinx.coroutines.flow.Flow
import web.id.wahyou.core.domain.model.anime.Anime
import web.id.wahyou.core.domain.model.manga.Manga
import web.id.wahyou.core.domain.repository.Repository
import web.id.wahyou.core.domain.state.MainState
import web.id.wahyou.core.domain.usecase.UseCase

class Iterator constructor(
    private val repository: Repository
) : UseCase {
    override fun getAnime(): Flow<MainState<List<Anime>>> = repository.getAnime()

    override fun getManga(): Flow<MainState<List<Manga>>> = repository.getManga()

    override fun setAnimeFavorite(
        anime: Anime,
        isFavorite: Boolean
    ) = repository.setAnimeFavorite(anime, isFavorite)

    override fun setMangaFavorite(
        manga: Manga,
        isFavorite: Boolean
    ) = repository.setMangaFavorite(manga, isFavorite)

    override fun getAnimeFavorite(): Flow<List<Anime>> = repository.getAnimeFavorite()

    override fun getMangaFavorite(): Flow<List<Manga>> = repository.getmangaFavorite()
}