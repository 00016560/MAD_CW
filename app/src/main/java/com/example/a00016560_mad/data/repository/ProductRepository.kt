package com.example.a00016560_mad.data.repository
import com.example.a00016560_mad.data.Product
import com.example.a00016560_mad.data.remote.ProductRequest
import com.example.a00016560_mad.data.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ProductRepository {
    private val api = RetrofitInstance.api
    private val studentId = "00016560"

    suspend fun getProducts(): List<Product> = withContext(Dispatchers.IO) {
        api.getProducts(studentId).data.map { product: Product ->
            Product(
                id = product.id,
                size = product.size,
                brand = product.brand,
                sample = product.sample,
                weight = product.weight,
                category = product.category,
                sellingPrice = product.sellingPrice,
                purchasePrice = product.purchasePrice,
                quantity = product.quantity,
                description = product.description
            )
        }
    }

    suspend fun createProduct(product: ProductRequest): Product = withContext(Dispatchers.IO) {
        api.createProduct(studentId, product)
    }

    suspend fun updateProduct(id: Int, product: Product): Product = withContext(Dispatchers.IO) {
        api.updateProduct(id, studentId, product)
    }

    suspend fun deleteProduct(id: Int) = withContext(Dispatchers.IO) {
        api.deleteProduct(id, studentId)
    }
}