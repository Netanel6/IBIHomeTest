package com.netanel.ibihometest.products.data.repository

import com.netanel.ibihometest.localdb.ProductDao
import com.netanel.ibihometest.products.data.model.Product
import com.netanel.ibihometest.products.domain.ProductApi
import com.netanel.ibihometest.utils.PrefsHelper
import com.netanel.ibihometest.utils.toEntity
import com.netanel.ibihometest.utils.toProduct
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: ProductApi,
    private val dao: ProductDao
) : DummyJsonRepository {

    override suspend fun fetchProducts(): List<Product> {
        return try {
            if (!PrefsHelper.hasFetchedProducts()) {
                val remoteProducts = api.getProducts().products
                dao.clearAll()
                dao.insertAll(remoteProducts.map { it.toEntity() })
                PrefsHelper.setProductsFetched(true)
            }

            dao.getAllProducts().map { it.toProduct() }

        } catch (e: Exception) {
            dao.getAllProducts().map { it.toProduct() }
        }
    }

    override suspend fun getFavorites(): List<Product> {
        return dao.getFavorites().map { it.toProduct() }
    }

    override suspend fun toggleFavorite(productId: Int, isFavorite: Boolean) {
        dao.updateFavorite(productId, isFavorite)
    }

}

interface DummyJsonRepository {
    suspend fun fetchProducts(): List<Product>
    suspend fun getFavorites(): List<Product>
    suspend fun toggleFavorite(productId: Int, isFavorite: Boolean)

}