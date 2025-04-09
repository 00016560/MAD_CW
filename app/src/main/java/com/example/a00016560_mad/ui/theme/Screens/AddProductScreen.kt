package com.example.a00016560_mad.ui.theme.Screens
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.a00016560_mad.R
import com.example.a00016560_mad.data.remote.ProductRequest
import com.example.a00016560_mad.ui.theme.ProductViewModel
import kotlinx.coroutines.launch


    @Composable
    fun AddProductScreen(
        viewModel: ProductViewModel = viewModel(),
        navController: NavController,
        paddingValues: PaddingValues
    ) {
        val localContext = LocalContext.current
        val productAdditionScope = rememberCoroutineScope()

        // State variables for form input fields
        var productName by remember { mutableStateOf("") }
        var productSize by remember { mutableStateOf("") }
        var productBrand by remember { mutableStateOf("") }
        var productSample by remember { mutableStateOf("") }
        var productWeight by remember { mutableStateOf("") }
        var productCategory by remember { mutableStateOf("") }
        var productMaterial by remember { mutableStateOf("") }
        var productQuantity by remember { mutableStateOf("") }
        var productPurchasePrice by remember { mutableStateOf("") }
        var productSellingPrice by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .background(Color(0xFFF3E5F5)) // Light purple background
                .padding(64.dp)
                .verticalScroll(rememberScrollState()), // Add this line for scrolling
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("PRODUCT DESCRIPTION", fontSize = 22.sp, color = Color(0xFF6A1B9A))


            Spacer(modifier = Modifier.height(32.dp))

            // Input fields
            OutlinedTextField(
                value = productName,
                onValueChange = { productName = it },
                label = { Text(stringResource(R.string.product_name)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = productSize,
                onValueChange = { productSize = it },
                label = { Text(stringResource(R.string.size)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = productBrand,
                onValueChange = { productBrand = it },
                label = { Text(stringResource(R.string.brand_name)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = productSample,
                onValueChange = { productSample = it },
                label = { Text(stringResource(R.string.sample)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = productWeight,
                onValueChange = { productWeight = it },
                label = { Text(stringResource(R.string.weight)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = productCategory,
                onValueChange = { productCategory = it },
                label = { Text(stringResource(R.string.category)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = productMaterial,
                onValueChange = { productMaterial = it },
                label = { Text(stringResource(R.string.material)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = productQuantity,
                onValueChange = { productQuantity = it },
                label = { Text(stringResource(R.string.quantity)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = productPurchasePrice,
                onValueChange = { productPurchasePrice = it },
                label = { Text(stringResource(R.string.purchasePrice)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = productSellingPrice,
                onValueChange = { productSellingPrice = it },
                label = { Text(stringResource(R.string.sellingPrice)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Button to add a new product
            Button(
                onClick = {
                    productAdditionScope.launch {
                        val newProduct = ProductRequest(
                            size = productSize.toIntOrNull() ?: 0,
                            brand = productBrand,
                            sample = productSample.toDoubleOrNull() ?: 0.0,
                            weight = productWeight.toDoubleOrNull() ?: 0.0,
                            category = productCategory,
                            quantity = productQuantity.toIntOrNull() ?: 0,
                            purchasePrice = productPurchasePrice.toDoubleOrNull() ?: 0.0,
                            sellingPrice = productSellingPrice.toDoubleOrNull() ?: 0.0,
                            description = productName
                        )
                        try {
                            viewModel.addProduct(newProduct)
                            navController.navigate("product_list")
                            Toast.makeText(localContext, "Product added successfully", Toast.LENGTH_SHORT).show()
                        } catch (networkException: Exception) {
                            Toast.makeText(
                                localContext,
                                "Network Error: ${networkException.localizedMessage}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFCBBC1C)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("ADD NEW PRODUCT", color = Color.White, fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("product_list") },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFCBBC1C)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.view_product_list), color = Color.White, fontSize = 18.sp)
            }
        }
    }
