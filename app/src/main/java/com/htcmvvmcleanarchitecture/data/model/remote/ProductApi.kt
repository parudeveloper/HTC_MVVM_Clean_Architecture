package com.htcmvvmcleanarchitecture.data.model.remote

import com.htcmvvmcleanarchitecture.data.model.ProductDetailsDto
import com.htcmvvmcleanarchitecture.data.model.ProductItemDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET("products.json")
    suspend fun getAllProducts(): List<ProductItemDto>

    @GET("product-details/{productId}.json")
    suspend fun getProductById(@Path("productId") id: Int): ProductDetailsDto


}