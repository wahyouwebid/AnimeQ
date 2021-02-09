package web.id.wahyou.animeq.ui.anime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import web.id.wahyou.core.domain.usecase.UseCase

class AnimeViewModel (useCase: UseCase): ViewModel() {
    val anime = useCase.getAnime().asLiveData()
}