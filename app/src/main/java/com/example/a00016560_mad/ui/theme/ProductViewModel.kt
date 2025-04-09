package com.example.a00016560_mad.ui.theme
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.a00016560_mad.data.Product
import com.example.a00016560_mad.data.remote.ProductRequest
import com.example.a00016560_mad.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductViewModel(
        private val repository: ProductRepository
    ) : ViewModel() {
        private val _products = MutableStateFlow<List<Product>>(emptyList())
        val products: StateFlow<List<Product>> = _products.asStateFlow()

        private val _isLoading = MutableStateFlow(false)
        val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

        private val _errorMessage = MutableStateFlow<String?>(null)
        val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

        init {
            loadProducts()
        }

        fun loadProducts() {
            viewModelScope.launch {
                _isLoading.value = true
                try {
                    _products.value = repository.getProducts()
                    _errorMessage.value = null
                } catch (e: Exception) {
                    _errorMessage.value = "Failed to load products"
                } finally {
                    _isLoading.value = false
                }
            }
        }

        fun addProduct(product: ProductRequest) {
            viewModelScope.launch {
                _isLoading.value = true
                try {
                    repository.createProduct(product)
                    loadProducts()
                } catch (e: Exception) {
                    _errorMessage.value = "Failed to add product"
                } finally {
                    _isLoading.value = false
                }
            }
        }

        fun updateProduct(id: Int, product: Product) {
            viewModelScope.launch {
                _isLoading.value = true
                try {
                    repository.updateProduct(id, product)
                        loadProducts()
                } catch (e: Exception) {
                    _errorMessage.value = "Failed to update product"
                } finally {
                    _isLoading.value = false
                }
            }
        }

        fun deleteProduct(id: Int) {
            viewModelScope.launch {
                _isLoading.value = true
                try {
                    repository.deleteProduct(id)
                        loadProducts()
                } catch (e: Exception) {
                    _errorMessage.value = "Failed to delete product"
                } finally {
                    _isLoading.value = false
                }
            }
        }

        fun clearErrorMessage() {
            _errorMessage.value = null
        }
    }
