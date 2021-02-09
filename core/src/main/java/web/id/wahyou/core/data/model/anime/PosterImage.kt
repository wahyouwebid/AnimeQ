package web.id.wahyou.core.data.model.anime

import com.google.gson.annotations.SerializedName

data class PosterImage(
    @field:SerializedName("large") val large: String,
    @field:SerializedName("medium") val medium: String,
    @field:SerializedName("original") val original: String,
    @field:SerializedName("small") val small: String,
    @field:SerializedName("tiny") val tiny: String
)