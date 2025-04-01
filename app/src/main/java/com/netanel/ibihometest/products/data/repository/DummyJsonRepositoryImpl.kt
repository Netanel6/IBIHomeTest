package com.netanel.ibihometest.products.data.repository

import com.netanel.ibihometest.products.data.model.Product
import com.netanel.ibihometest.products.domain.ProductApi
import javax.inject.Inject

class DummyJsonRepositoryImpl @Inject constructor(
    private val api: ProductApi
) : DummyJsonRepository {
    override suspend fun fetchProducts(): List<Product> {
        return api.getProducts().products
    }
}


interface DummyJsonRepository {
    suspend fun fetchProducts(): List<Product>
}