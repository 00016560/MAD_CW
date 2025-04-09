package com.example.a00016560_mad.ui.theme.Screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.a00016560_mad.API.RetrofitClient
import kotlinx.coroutines.launch

@Composable
fun DeleteConfirmationScreen(navController: NavController, productId: Int) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF3E0)) // Light orange background
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("DELETION CONFIRMATION", fontSize = 22.sp, color = Color(0xFFFF6F00))

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            backgroundColor = Color.White,
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "ARE YOU SURE TO DELETE THIS PRODUCT?",
                    fontSize = 18.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // YES Button - Deletes the product
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                val response = RetrofitClient.apiService.deleteProduct(productId)
                                if (response.isSuccessful) {
                                    Toast.makeText(context, "Product Deleted", Toast.LENGTH_SHORT).show()
                                    navController.navigate("product_list") // Navigate back to product list
                                } else {
                                    Toast.makeText(context, "Failed to delete product", Toast.LENGTH_SHORT).show()
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                    ) {
                        Text("YES", color = Color.White)
                    }

                    // NO Button - Goes back to the product list
                    Button(
                        onClick = { navController.navigate("product_list") },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
                    ) {
                        Text("NO", color = Color.White)
                    }
                }
            }
        }
    }
}