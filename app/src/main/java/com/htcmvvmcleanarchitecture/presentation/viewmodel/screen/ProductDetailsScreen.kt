package com.htcmvvmcleanarchitecture.presentation.viewmodel.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.htcmvvmcleanarchitecture.presentation.viewmodel.ProductDetailsViewModel
import com.htcmvvmcleanarchitecture.utils.Resource
import com.htcmvvmcleanarchitecture.utils.shareProduct


@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun ProductDetailsScreen(
    navController: NavHostController,
    productId: Int,
    viewModel: ProductDetailsViewModel = hiltViewModel()
) {
    val productState by viewModel.product.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchProduct(productId)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Product Details") }, navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            })
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            when (productState) {
                is Resource.Loading -> CircularProgressIndicator(Modifier.align(Alignment.Center))
                is Resource.Error -> Text(
                    text = (productState as Resource.Error).message ?: "Error",
                    modifier = Modifier.align(Alignment.Center)
                )

                is Resource.Success -> {
                    val product = (productState as Resource.Success).data!!
                    Column(modifier = Modifier.padding(16.dp)) {
                        /* AsyncImage(
                             model = product.imageUrl,
                             contentDescription = product.title,
                             modifier = Modifier
                                 .fillMaxWidth()
                                 .height(200.dp)
                         )*/
                        GlideImage(
                            model = product.imageUrl,
                            contentDescription = product.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = "Title: ${product.title}",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Text(
                            text = "Description:",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )

                        Text(
                            text = product.summary,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        Text(
                            text = "Price: ${product.price}",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(24.dp))


                        IconButton(onClick = {
                            val message = "${product.title} - ${product.price} from Hyderabad added to list"
                            shareProduct(context, message)
                        }) {
                            Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
                        }
                    }
                }
            }
        }
    }
}