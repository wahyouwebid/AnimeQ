package web.id.wahyou.core.data.model.anime

import com.google.gson.annotations.SerializedName

data class DataAnime(
    @field:SerializedName("attributes") val attributes: Attributes,
    @field:SerializedName("id") val id: String,
    @field:SerializedName("links") val links: Links,
    @field:SerializedName("type") val type: String
)