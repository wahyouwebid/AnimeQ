package web.id.wahyou.core.data.repository.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "anime")
data class AnimeEntity(
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "title") var canonicalTitle: String,

    @ColumnInfo(name = "rating") var averageRating: String,

    @ColumnInfo(name = "synopsis") var synopsis: String,

    @ColumnInfo(name = "posterImage") var posterImage: String,

    @ColumnInfo(name = "coverImage") var coverImage: String,

    @ColumnInfo(name = "youtubeVideoId") var youtubeVideoId: String,

    @ColumnInfo(name = "episodeCount") var episodeCount: Int,

    @ColumnInfo(name = "startDate") var startDate: String,

    @ColumnInfo(name = "status") var status: String,

    @ColumnInfo(name = "subtype") var subtype: String,

    @ColumnInfo(name = "totalDuration") var totalDuration: Int,

    @ColumnInfo(name = "type") var type: String,

    @ColumnInfo(name = "userCount") var userCount: Int,

    @ColumnInfo(name = "isFavorite") var isFavorite: Boolean = false
)