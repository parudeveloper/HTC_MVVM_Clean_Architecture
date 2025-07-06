package com.htcmvvmcleanarchitecture.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.htcmvvmcleanarchitecture.domain.model.ProductItem
import com.htcmvvmcleanarchitecture.domain.model.usecase.GetAllProductsUseCase
import com.htcmvvmcleanarchitecture.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase
) : ViewModel() {

    private val _products = MutableStateFlow<Resource<List<ProductItem>>>(Resource.Loading())
    val products: StateFlow<Resource<List<ProductItem>>> = _products.asStateFlow()

    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            getAllProductsUseCase()
                .catch { exception ->
                    _products.value = Resource.Error(exception.message ?: "Unknown error")
                }
                .collect { result ->
                    _products.value = result
                }
        }
    }
}