package com.example.a00016560_mad.data.remote

import com.google.gson.annotations.SerializedName

data class ProductRequest(
    // @SerializedName указывает, что в JSON это поле называется "title"
    @SerializedName("title") val brand: String,
    @SerializedName("size") val size: Int,
    @SerializedName("double_two") val sample: Double,
    @SerializedName("double_list") val weight: Double,
    @SerializedName("type") val category: String,
    @SerializedName("integer_one") val quantity: Int,
    @SerializedName("double_one") val purchasePrice: Double,
    @SerializedName("price") val sellingPrice: Double,
    @SerializedName("description") val description: String? = null
)