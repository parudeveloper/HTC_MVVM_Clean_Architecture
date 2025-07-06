package com.htcmvvmcleanarchitecture.data.model.mapper

import com.htcmvvmcleanarchitecture.data.model.ProductItemDto
import com.htcmvvmcleanarchitecture.domain.model.ProductItem

fun ProductItemDto.toDomain(): ProductItem {
    return ProductItem(
        id = id,
        title = title,
        summary = summary,
        imageUrl = imageUrl
    )
}