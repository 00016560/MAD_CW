package com.example.a00016560_mad.data.remote


import com.example.a00016560_mad.data.Product
import com.example.a00016560_mad.data.remote.ProductRequest
import com.example.a00016560_mad.data.remote.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    @GET("records/all") // Функция возвращает объект Response и является suspend-функцией (для корутин)
    suspend fun getProducts(
        @Query("student_id") studentId: String
    ): Response

    @POST("records")
    suspend fun createProduct(
        @Query("student_id") studentId: String,
        @Body product: ProductRequest
    ): Product

    @PUT("records/{id}")   // "records/{id}" - endpoint, где {id} заменяется на actual ID продукта
    suspend fun updateProduct(
        @Path("id") id: Int,
        @Query("student_id") studentId: String,
        @Body product: Product
    ): Product // Возвращает обновленный Product

    @DELETE("records/{id}")
    suspend fun deleteProduct(
        @Path("id") id: Int, @Query("student_id") studentId: String)
}