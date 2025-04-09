package com.example.a00016560_mad.data.remote

//import ProductApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://wiutmadcw.uz/api/v1/"

    val api: ProductApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            // Создание экземпляра Retrofit
            .build()
            // Создание реализации интерфейса ProductApi
            // Retrofit генерирует необходимый код во время выполнения
            .create(ProductApi::class.java)
    }
}