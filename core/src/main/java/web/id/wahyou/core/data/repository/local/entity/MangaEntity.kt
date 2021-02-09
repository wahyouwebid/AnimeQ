package web.id.wahyou.core.data.repository.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "manga")
data class MangaEntity(
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "id") var id: String,

    @ColumnInfo(name = "title") var canonicalTitle: String,

    @ColumnInfo(name = "rating") var averageRating: String?,

    @ColumnInfo(name = "synopsis") var synopsis: String,

    @ColumnInfo(name = "posterImage") var posterImage: String,

    @ColumnInfo(name = "startDate") var startDate: String?,

    @ColumnInfo(name = "type") var type: String,

    @ColumnInfo(name = "serialization") var serialization: String?,

    @ColumnInfo(name = "chapterCount") var chapterCount: Int,

    @ColumnInfo(name = "userCount") var userCount: Int,

    @ColumnInfo(name = "status") var status: String,

    @ColumnInfo(name = "volumeCount") var volumeCount: Int,

    @ColumnInfo(name = "popularityRank") var popularityRank: Int,

    @ColumnInfo(name = "isFavorite") var isFavorite: Boolean = false
)