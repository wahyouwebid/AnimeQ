package web.id.wahyou.core.data.model.categories

import com.google.gson.annotations.SerializedName

data class DataCategories(
        @field:SerializedName("attributes") val attributes: Attributes,
        @field:SerializedName("id") val id: String,
        @field:SerializedName("links") val links: Links,
        @field:SerializedName("type") val type: String
)