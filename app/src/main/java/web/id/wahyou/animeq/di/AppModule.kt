package web.id.wahyou.animeq.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import web.id.wahyou.animeq.ui.anime.AnimeViewModel
import web.id.wahyou.animeq.ui.animedetail.DetailAnimeViewModel
import web.id.wahyou.animeq.ui.manga.MangaViewModel
import web.id.wahyou.animeq.ui.mangadetail.DetailMangaViewModel
import web.id.wahyou.core.domain.iterator.Iterator
import web.id.wahyou.core.domain.usecase.UseCase

val useCaseModule = module {
    factory<UseCase> { Iterator(get()) }
}

val viewModelModule = module {
    viewModel { AnimeViewModel(get()) }
    viewModel { MangaViewModel(get()) }
    viewModel { DetailAnimeViewModel(get()) }
    viewModel { DetailMangaViewModel(get()) }
}