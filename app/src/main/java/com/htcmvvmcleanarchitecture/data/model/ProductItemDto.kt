package com.htcmvvmcleanarchitecture.data.model

import com.google.gson.annotations.SerializedName

data class ProductItemDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("summary") val summary: String,
    @SerializedName("imageUrl") val imageUrl: String
)