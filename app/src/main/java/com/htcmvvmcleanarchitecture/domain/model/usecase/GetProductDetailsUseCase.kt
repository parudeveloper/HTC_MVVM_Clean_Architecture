package com.htcmvvmcleanarchitecture.domain.model.usecase

import com.htcmvvmcleanarchitecture.domain.model.ProductDetails
import com.htcmvvmcleanarchitecture.domain.model.repository.ProductRepository
import com.htcmvvmcleanarchitecture.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductDetailsUseCase @Inject constructor(private val repository: ProductRepository) {
    operator fun invoke(id: Int): Flow<Resource<ProductDetails>> {
        return repository.getProductById(id)
    }


}