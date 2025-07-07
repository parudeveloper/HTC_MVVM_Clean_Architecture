package com.htcmvvmcleanarchitecture.data.model.remote

import com.htcmvvmcleanarchitecture.data.model.ProductDetailsDto
import com.htcmvvmcleanarchitecture.data.model.ProductItemDto
import com.htcmvvmcleanarchitecture.utils.PRODUCTS_END_URL
import com.htcmvvmcleanarchitecture.utils.PRODUCT_DETAILS_END_URL
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET(PRODUCTS_END_URL)
    suspend fun getAllProducts(): List<ProductItemDto>

    @GET(PRODUCT_DETAILS_END_URL)
    suspend fun getProductById(@Path("productId") id: Int): ProductDetailsDto


}