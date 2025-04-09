package com.example.a00016560_mad.ui.theme.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.a00016560_mad.R
import com.example.a00016560_mad.ui.theme.ProductViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DeleteProductScreen(navController: NavController, productId: Int, viewModel: ProductViewModel, paddingValues: PaddingValues) {
    val product = viewModel.products.value.find { it.id == productId }
    product?.let {
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.product_delete_confirmation, it.brand),
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