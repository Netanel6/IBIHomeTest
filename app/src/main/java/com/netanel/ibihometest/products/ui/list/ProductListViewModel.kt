package com.netanel.ibihometest.products.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netanel.ibihometest.products.data.model.Product
import com.netanel.ibihometest.products.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//Shared ViewModel
@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    //List
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun loadProducts() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null

            try {
                val result = repository.fetchProducts()
                _products.value = result
            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "Something went wrong"
            } finally {
                _loading.value = false
            }
        }
    }


    //Details
    private val _selectedProduct = MutableLiveData<Product?>()
    val selectedProduct: LiveData<Product?> get() = _selectedProduct

    fun selectProduct(product: Product) {
        _selectedProduct.value = product
    }


    //Favorites
    private val _favorites = MutableLiveData<List<Product>>()
    val favorites: LiveData<List<Product>> get() = _favorites

    fun loadFavorites() {
        viewModelScope.launch {
            _favorites.value = repository.getFavorites()
        }
    }

    fun toggleFavorite(product: Product) {
        viewModelScope.launch {
            repository.toggleFavorite(product.id, !product.isFavorite)
            loadFavorites()
            loadProducts()
        }
    }

}
