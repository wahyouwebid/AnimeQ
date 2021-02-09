package web.id.wahyou.core.data.model.anime

import com.google.gson.annotations.SerializedName

data class ResponseAnime (
    @field:SerializedName("data") val data: List<DataAnime>
)