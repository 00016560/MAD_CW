package com.example.a00016560_mad.ui.theme.Screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.a00016560_mad.R
import com.example.a00016560_mad.data.Product
import com.example.a00016560_mad.ui.theme.navigation.Screen
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.a00016560_mad.ui.theme.ProductViewModel

@Composable
fun ProductCard(
    product: Product,
    onProductClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onUpdateClick: () -> Unit,
    onQuantityChange: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onProductClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        val quantity by remember { mutableIntStateOf(product.quantity) }
        Row (
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(stringResource(R.string.title, product.brand))
                Text(stringResource(R.string.purchasePrice, product.purchasePrice))
                Text(stringResource(R.string.sellingPrice, product.sellingPrice))
            }

            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = {
                        if (quantity > 1)
                            onQuantityChange(product.quantity--)
                    }) { Text("-") }
                    Text(product.quantity.toString())
                    IconButton(onClick = {
                        onQuantityChange(product.quantity++)
                    }) { Text("+") }
                }

                Row {
                    IconButton(onClick = onDeleteClick) {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete")
                    }
                    IconButton(onClick = onUpdateClick) {
                        Icon(Icons.Filled.Edit, contentDescription = "Update")
                    }
                }
            }
        }
    }
}

@Composable
fun ProductListScreen(
    navController: NavController,
    viewModel: ProductViewModel,
    paddingValues: PaddingValues
) {
    val products = viewModel.products
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)) {
        itemsIndexed(products, itemContent = { index, item ->
            ProductCard(
                product = item,
                onProductClick = {
                    navController.navigate(Screen.ProductDetails.createRoute(item.id))
                },
                onDeleteClick = {
                    navController.navigate(Screen.DeleteProduct.createRoute(item.id))
                },
                onUpdateClick = {
                    navController.navigate(Screen.UpdateProduct.createRoute(item.id))
                },
                onQuantityChange = { quantity ->
                    viewModel.updateQuantity(item.id, quantity)
                })
        })
    }
}