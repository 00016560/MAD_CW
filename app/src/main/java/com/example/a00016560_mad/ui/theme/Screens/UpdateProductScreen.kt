package com.example.a00016560_mad.ui.theme.Screens

import android.annotation.SuppressLint
import androidx.benchmark.perfetto.ExperimentalPerfettoTraceProcessorApi
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.material.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController
import com.example.a00016560_mad.R
import com.example.a00016560_mad.ui.theme.ProductViewModel

@OptIn(ExperimentalPerfettoTraceProcessorApi::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun UpdateProductScreen(
    navController: NavController,
    productId: Int,
    viewModel: ProductViewModel,
    paddingValues: PaddingValues
) {
    val product = viewModel.products.value.find { it.id == productId }
    product?.let {
        var size by remember { mutableStateOf(TextFieldValue(it.size.toString())) }
        var brand by remember { mutableStateOf(TextFieldValue(it.brand)) }
        var sample by remember { mutableStateOf(TextFieldValue(it.sample.toString())) }
        var weight by remember { mutableStateOf(TextFieldValue(it.weight.toString())) }
        var category by remember { mutableStateOf(TextFieldValue(it.category)) }
        var sellingPrice by remember { mutableStateOf(TextFieldValue(it.sellingPrice.toString())) }
        var purchasePrice by remember { mutableStateOf(TextFieldValue(it.purchasePrice.toString())) }
        var quantity by remember { mutableStateOf(TextFieldValue(it.quantity.toString())) }
        var description by remember { mutableStateOf(TextFieldValue(it.description)) }

        Column(
            modifier = Modifier.padding(paddingValues).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(value = size, onValueChange = { size = it }, label = { Text("Size") })
            TextField(value = brand, onValueChange = { brand = it }, label = { Text("Brand") })
            TextField(value = sample, onValueChange = { sample = it }, label = { Text("Sample") })
            TextField(value = weight, onValueChange = { weight = it }, label = { Text("Weight") })
            TextField(value = category, onValueChange = { category = it }, label = { Text("Category") })
            TextField(value = sellingPrice, onValueChange = { sellingPrice = it }, label = { Text("Selling price") })
            TextField(value = purchasePrice, onValueChange = {purchasePrice = it}, label = { Text("Purchase price")})
            TextField(value = quantity, onValueChange = {quantity = it}, label = { Text("Stock")})

            Row {
                Button(onClick = {
                    val updatedProduct = it.copy(
                        size = size.text.toInt(),
                        brand = brand.text,
                        sample = sample.text.toDouble(),
                        weight = weight.text.toDouble(),
                        category = category.text,
                        sellingPrice = sellingPrice.text.toDoubleOrNull() ?: 0.0,
                        purchasePrice = purchasePrice.text.toDouble(),
                        quantity = quantity.text.toInt(),
                        description = description.text
                    )
                    viewModel.updateProduct(it.id, updatedProduct)
                    navController.popBackStack()
                }) {
                    Text(stringResource(R.string.update))
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { navController.popBackStack() }) {
                    Text(stringResource(R.string.cancel))
                }
            }
        }
    }
}