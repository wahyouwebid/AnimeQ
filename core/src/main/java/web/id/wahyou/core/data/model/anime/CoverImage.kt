package web.id.wahyou.core.data.model.anime

import com.google.gson.annotations.SerializedName

data class CoverImage(
    @field:SerializedName("large") val large: String? = null,
    @field:SerializedName("original") val original: String,
    @field:SerializedName("small") val small: String,
    @field:SerializedName("tiny") val tiny: String
)