package web.id.wahyou.core.data.model.categories

import com.google.gson.annotations.SerializedName

data class ResponseCategories (
    @field:SerializedName("data") val data: List<DataCategories>
)