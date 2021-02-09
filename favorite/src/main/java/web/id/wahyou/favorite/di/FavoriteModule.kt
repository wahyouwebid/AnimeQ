package web.id.wahyou.favorite.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import web.id.wahyou.favorite.anime.AnimeFavoriteViewModel
import web.id.wahyou.favorite.manga.MangaFavoriteViewModel

val favoriteModule = module {
    viewModel { AnimeFavoriteViewModel(get()) }
    viewModel { MangaFavoriteViewModel(get()) }
}