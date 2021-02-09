package web.id.wahyou.core.domain.model.manga

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Manga(
    var id: String,
    var canonicalTitle: String,
    var averageRating: String,
    var synopsis: String,
    var posterImage: String,
    var startDate: String?,
    var type: String,
    var serialization: String,
    var chapterCount: Int,
    var userCount: Int,
    var status: String,
    var volumeCount: Int,
    var popularityRank: Int,
    var isFavorite: Boolean = false
) : Parcelable