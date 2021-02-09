package web.id.wahyou.animeq.ui.manga

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import web.id.wahyou.core.domain.usecase.UseCase

class MangaViewModel (useCase: UseCase) : ViewModel() {
    val manga = useCase.getManga().asLiveData()
}