package web.id.wahyou.core.data.model.manga

import com.google.gson.annotations.SerializedName

data class ResponseManga (
    @field:SerializedName("data")
    val data: List<DataManga>
)