package com.htcmvvmcleanarchitecture.presentation.viewmodel.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.htcmvvmcleanarchitecture.domain.model.ProductItem
import com.htcmvvmcleanarchitecture.presentation.viewmodel.ProductsViewModel
import com.htcmvvmcleanarchitecture.utils.Resource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    onProductClick: (Int) -> Unit,
    viewModel: ProductsViewModel = hiltViewModel()
) {
    val state by viewModel.products.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Products") })
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (state) {
                is Resource.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is Resource.Success -> {
                    val products = (state as Resource.Success).data ?: emptyList()
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(products) { product ->
                            ProductCard(product, onProductClick)
                        }
                    }
                }

                is Resource.Error -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = (state as Resource.Error).message ?: "Something went wrong")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductCard(product: ProductItem, onProductClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp).clickable { onProductClick(product.id) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            /*AsyncImage(
                model = product.imageUrl,
                contentDescription = product.title,
                modifier = Modifier.size(64.dp)
            )*/
            GlideImage(
                model = product.imageUrl,
                contentDescription = product.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = product.title, style = MaterialTheme.typography.titleMedium)
                Text(text = product.summary, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}