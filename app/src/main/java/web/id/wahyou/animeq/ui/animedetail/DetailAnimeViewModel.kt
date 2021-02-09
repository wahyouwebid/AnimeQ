package web.id.wahyou.animeq.ui.animedetail

import androidx.lifecycle.ViewModel
import web.id.wahyou.core.domain.model.anime.Anime
import web.id.wahyou.core.domain.usecase.UseCase

class DetailAnimeViewModel (private val useCase: UseCase) : ViewModel() {
    fun setFavoriteAnime(anime: Anime, isFavorite: Boolean) {
        useCase.setAnimeFavorite(anime, isFavorite)
    }
}