package web.id.wahyou.core.domain.model.anime

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Anime(
    var id: String,
    var canonicalTitle: String,
    var averageRating: String,
    var synopsis: String,
    var posterImage: String,
    var coverImage: String,
    var youtubeVideoId: String,
    var episodeCount: Int,
    var startDate: String,
    var status: String,
    var subtype: String,
    var totalDuration: Int,
    var type: String,
    var userCount: Int,
    var isFavorite: Boolean = false
) :Parcelable