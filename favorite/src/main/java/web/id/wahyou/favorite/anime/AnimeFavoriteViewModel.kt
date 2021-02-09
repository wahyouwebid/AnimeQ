package web.id.wahyou.favorite.anime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import web.id.wahyou.core.domain.usecase.UseCase

class AnimeFavoriteViewModel(useCase: UseCase) : ViewModel() {

    val favorite = useCase.getAnimeFavorite().asLiveData()
}