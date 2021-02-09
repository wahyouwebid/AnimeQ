package web.id.wahyou.core.data.model.manga

import com.google.gson.annotations.SerializedName

data class Attributes(
    @field:SerializedName("ageRating") val ageRating: String,
    @field:SerializedName("ageRatingGuide") val ageRatingGuide: String,
    @field:SerializedName("averageRating") val averageRating: String,
    @field:SerializedName("canonicalTitle") val canonicalTitle: String,
    @field:SerializedName("coverImage") val coverImage: CoverImage?,
    @field:SerializedName("coverImageTopOffset") val coverImageTopOffset: Int,
    @field:SerializedName("description") val description: String,
    @field:SerializedName("episodeCount") val episodeCount: Int,
    @field:SerializedName("episodeLength") val episodeLength: Int,
    @field:SerializedName("favoritesCount") val favoritesCount: Int,
    @field:SerializedName("nextRelease") val nextRelease: String,
    @field:SerializedName("nsfw") val nsfw: Boolean,
    @field:SerializedName("popularityRank") val popularityRank: Int,
    @field:SerializedName("posterImage") val posterImage: PosterImage,
    @field:SerializedName("ratingRank") val ratingRank: Int,
    @field:SerializedName("showType") val showType: String,
    @field:SerializedName("slug") val slug: String,
    @field:SerializedName("status") val status: String,
    @field:SerializedName("subtype") val subtype: String,
    @field:SerializedName("synopsis") val synopsis: String,
    @field:SerializedName("tba") val tba: String,
    @field:SerializedName("totalLength") val totalLength: Int,
    @field:SerializedName("userCount") val userCount: Int,
    @field:SerializedName("youtubeVideoId") val youtubeVideoId: String,
    @field:SerializedName("startDate") val startDate: String,
    @field:SerializedName("serialization") val serialization: String,
    @field:SerializedName("chapterCount") val chapterCount: Int,
    @field:SerializedName("volumeCount") val volumeCount: Int

)