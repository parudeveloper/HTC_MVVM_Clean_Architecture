package com.htcmvvmcleanarchitecture.domain.model.usecase

import com.htcmvvmcleanarchitecture.domain.model.ProductItem
import com.htcmvvmcleanarchitecture.domain.model.repository.ProductRepository
import com.htcmvvmcleanarchitecture.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<Resource<List<ProductItem>>> {
        return repository.getAllProducts()
    }
}