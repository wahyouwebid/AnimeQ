package web.id.wahyou.favorite.manga

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import web.id.wahyou.core.domain.usecase.UseCase

class MangaFavoriteViewModel(useCase: UseCase) : ViewModel() {

    val favorite = useCase.getMangaFavorite().asLiveData()
}