package com.htcmvvmcleanarchitecture.data.model.mapper

import com.htcmvvmcleanarchitecture.data.model.ProductDetailsDto
import com.htcmvvmcleanarchitecture.domain.model.ProductDetails

fun ProductDetailsDto.toDomain(): ProductDetails {
    return ProductDetails(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl,
        price = price,
        summary = summary
    )
}