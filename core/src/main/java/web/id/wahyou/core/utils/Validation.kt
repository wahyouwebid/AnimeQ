package web.id.wahyou.core.utils

import web.id.wahyou.core.data.model.anime.CoverImage
import web.id.wahyou.core.data.model.anime.PosterImage

fun checkNullString(value: String?) = value ?: ""

fun checkNullCoverImage(value: CoverImage?) = value?.original ?: ""

fun checkNullPosterImage(value: PosterImage?) = value?.medium ?: ""