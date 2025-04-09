package com.example.a00016560_mad.ui.theme.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.a00016560_mad.ui.theme.ProductViewModel


@Composable
fun DeleteProductScreen(navController: NavController, productId: Int, viewModel: ProductViewModel, paddingValues: PaddingValues) {
    val product = viewModel.products.find { it.id == productId }
    product?.let {
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.product_delete_confirmation, it.name),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Button(onClick = { viewModel.deleteProduct(it.id); navController.popBackStack() }) {
                    Text(stringResource(R.string.yes))
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { navController.popBackStack() }) {
                    Text(stringResource(R.string.no))
                }
            }
        }
    }
}