package com.netanel.ibihometest.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<ProductEntity>)

    @Query("DELETE FROM products")
    suspend fun clearAll()

    @Query("SELECT * FROM products WHERE isFavorite = 1")
    suspend fun getFavorites(): List<ProductEntity>

    @Query("UPDATE products SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavorite(id: Int, isFavorite: Boolean)

}
