package com.htcmvvmcleanarchitecture.di

import com.htcmvvmcleanarchitecture.data.model.remote.ProductApi
import com.htcmvvmcleanarchitecture.data.model.repository.ProductRepositoryImpl
import com.htcmvvmcleanarchitecture.domain.model.repository.ProductRepository
import com.htcmvvmcleanarchitecture.domain.model.usecase.GetProductDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://meijer-maui-test-default-rtdb.firebaseio.com/"

    @Provides
    @Singleton
    fun provideProductApi(): ProductApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepository(
        api: ProductApi
    ): ProductRepository {
        return ProductRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetProductsUseCase(
        repository: ProductRepository
    ): GetProductDetailsUseCase {
        return GetProductDetailsUseCase(repository)
    }
}