package com.example.a00016560_mad.data

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id") val id: Int = 0,
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