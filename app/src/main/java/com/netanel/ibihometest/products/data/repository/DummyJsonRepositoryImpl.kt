package com.netanel.ibihometest.products.data.repository

import com.netanel.ibihometest.localdb.ProductDao
import com.netanel.ibihometest.products.data.model.Product
import com.netanel.ibihometest.products.domain.ProductApi
import com.netanel.ibihometest.utils.PrefsHelper
import com.netanel.ibihometest.utils.toEntity
import com.netanel.ibihometest.utils.toProduct
import javax.inject.Inject

class DummyJsonRepositoryImpl @Inject constructor(
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

}

interface DummyJsonRepository {
    suspend fun fetchProducts(): List<Product>
}