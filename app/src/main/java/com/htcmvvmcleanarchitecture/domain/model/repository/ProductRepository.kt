package com.htcmvvmcleanarchitecture.domain.model.repository

import com.htcmvvmcleanarchitecture.domain.model.ProductDetails
import com.htcmvvmcleanarchitecture.domain.model.ProductItem
import com.htcmvvmcleanarchitecture.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getAllProducts(): Flow<Resource<List<ProductItem>>>
    fun getProductById(id: Int): Flow<Resource<ProductDetails>>

}