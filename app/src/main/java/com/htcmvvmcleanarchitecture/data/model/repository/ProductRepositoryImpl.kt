package com.htcmvvmcleanarchitecture.data.model.repository

import com.htcmvvmcleanarchitecture.data.model.mapper.toDomain
import com.htcmvvmcleanarchitecture.data.model.remote.ProductApi
import com.htcmvvmcleanarchitecture.domain.model.ProductDetails
import com.htcmvvmcleanarchitecture.domain.model.ProductItem
import com.htcmvvmcleanarchitecture.domain.model.repository.ProductRepository
import com.htcmvvmcleanarchitecture.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productApi: ProductApi
) : ProductRepository {

    override fun getAllProducts(): Flow<Resource<List<ProductItem>>> = flow {
        emit(Resource.Loading())

        try {
            val response = productApi.getAllProducts()
            val domainData = response.map { it.toDomain() }
            emit(Resource.Success(domainData))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An unexpected error occurred"))
        }
    }.flowOn(Dispatchers.IO)


    override fun getProductById(id: Int): Flow<Resource<ProductDetails>> = flow {
        emit(Resource.Loading())
        try {
            val response = productApi.getProductById(id)
            emit(Resource.Success(response.toDomain()))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error fetching product"))
        }
    }.flowOn(Dispatchers.IO)

}