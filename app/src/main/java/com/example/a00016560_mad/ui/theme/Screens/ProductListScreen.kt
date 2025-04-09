package com.example.a00016560_mad.ui.theme.Screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import com.example.a00016560_mad.data.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ProductListScreen(navController: NavController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    // State variable to store product list
    var productList by remember { mutableStateOf<List<Product>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    // Fetch products when the screen loads
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                val response = RetrofitClient.apiService.getProducts("00016560_secret") // ✅ Ensure correct student ID
                if (response.isSuccessful) {
                    val products = response.body()
                    if (!products.isNullOrEmpty()) {
                        productList = products
                    } else {
                        Toast.makeText(context, "No products found.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Toast.makeText(context, "Server Error: $errorBody", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Network Error: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
            } finally {
                isLoading = false
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF3E0)) // Light orange background
            .padding(16.dp)
    ) {
        Text("PRODUCT LIST", fontSize = 22.sp, color = Color(0xFFFF6F00))

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            if (productList.isEmpty()) {
                Text("No products available.", color = Color.Gray, modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                LazyColumn {
                    items(productList) { product ->
                        ProductItem(product, navController, coroutineScope)
                    }
                }
            }
        }
    }
}

@Composable
fun ProductItem(product: Product, navController: NavController, coroutineScope: CoroutineScope) {
    var stockQuantity by remember { mutableStateOf(product.quantity.toString()) }
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        backgroundColor = Color.White,
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text("Name: ${product.name}", fontSize = 18.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
            Text("Brand: ${product.brand ?: "N/A"}", fontSize = 16.sp, color = Color.Gray)
            Text("Stock: $stockQuantity", fontSize = 16.sp, color = Color.Gray)

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Update Stock Button
                Button(
                    onClick = {
                        coroutineScope.launch {
                            try {
                                val response = RetrofitClient.apiService.updateStock(
                                    product.id,
                                    "00016560_secret", // ✅ Ensure correct student ID
                                    mapOf("stock_quantity" to stockQuantity.toInt())
                                )
                                if (response.isSuccessful) {
                                    Toast.makeText(context, "Stock updated", Toast.LENGTH_SHORT).show()
                                } else {
                                    val errorBody = response.errorBody()?.string()
                                    Toast.makeText(context, "Failed to update stock: $errorBody", Toast.LENGTH_SHORT).show()
                                }
                            } catch (e: Exception) {
                                Toast.makeText(context, "Error updating stock: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF6A1B9A))
                ) {
                    Text("Update", color = Color.White)
                }

                // Delete Button
                Button(
                    onClick = {
                        navController.navigate("delete_confirmation/${product.id}")
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                ) {
                    Text("Delete", color = Color.White)
                }
            }
        }
    }
}