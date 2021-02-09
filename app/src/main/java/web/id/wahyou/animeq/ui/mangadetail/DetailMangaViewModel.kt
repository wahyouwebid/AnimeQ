package web.id.wahyou.animeq.ui.mangadetail

import androidx.lifecycle.ViewModel
import web.id.wahyou.core.domain.model.manga.Manga
import web.id.wahyou.core.domain.usecase.UseCase

class DetailMangaViewModel (private val useCase: UseCase) : ViewModel() {
    fun setFavoriteAnime(anime: Manga, isFavorite: Boolean) {
        useCase.setMangaFavorite(anime, isFavorite)
    }
}