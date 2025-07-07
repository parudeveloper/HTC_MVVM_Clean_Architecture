package com.htcmvvmcleanarchitecture.data.model

import com.google.gson.annotations.SerializedName

data class ProductDetailsDto(
    @SerializedName("description") val description: String,
    @SerializedName("id") val id: Int,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("price") val price: String,
    @SerializedName("summary") val summary: String,
    @SerializedName("title") val title: String
)