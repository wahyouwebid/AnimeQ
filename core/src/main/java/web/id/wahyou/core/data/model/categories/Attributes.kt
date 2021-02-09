package web.id.wahyou.core.data.model.categories

import com.google.gson.annotations.SerializedName

data class Attributes(
        @field:SerializedName("childCount") val childCount: Int,
        @field:SerializedName("createdAt") val createdAt: String,
        @field:SerializedName("description") val description: Any,
        @field:SerializedName("image") val image: Any,
        @field:SerializedName("nsfw") val nsfw: Boolean,
        @field:SerializedName("slug") val slug: String,
        @field:SerializedName("title") val title: String,
        @field:SerializedName("totalMediaCount") val totalMediaCount: Int,
        @field:SerializedName("updatedAt") val updatedAt: String
)