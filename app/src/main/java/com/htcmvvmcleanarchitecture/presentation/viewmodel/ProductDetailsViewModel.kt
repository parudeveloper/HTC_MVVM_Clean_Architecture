package com.htcmvvmcleanarchitecture.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.htcmvvmcleanarchitecture.domain.model.ProductDetails
import com.htcmvvmcleanarchitecture.domain.model.usecase.GetProductDetailsUseCase
import com.htcmvvmcleanarchitecture.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductDetailsUseCase: GetProductDetailsUseCase
) : ViewModel() {

    private val _product = MutableStateFlow<Resource<ProductDetails>>(Resource.Loading())
    val product: StateFlow<Resource<ProductDetails>> = _product.asStateFlow()

    fun fetchProduct(id: Int) {
        viewModelScope.launch {
            getProductDetailsUseCase(id)
                .catch { _product.value = Resource.Error(it.message ?: "Error") }
                .collect { _product.value = it }
        }
    }
}