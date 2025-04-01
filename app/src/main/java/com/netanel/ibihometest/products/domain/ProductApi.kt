package com.netanel.ibihometest.products.domain

import com.netanel.ibihometest.products.data.model.ProductResponse
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun getProducts(): ProductResponse
}
