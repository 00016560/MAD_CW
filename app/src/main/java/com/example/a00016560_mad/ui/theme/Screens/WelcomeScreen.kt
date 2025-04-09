package com.example.a00016560_mad.ui.theme.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.a00016560_mad.R

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3E5F5)), // Light purple background
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Title
        Text(
            text = "MANICURE APPLIANCES",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6A1B9A), // Deep purple color
            modifier = Modifier.padding(16.dp)
        )

        // Display Image (Change to your resource file)
        Image(
            painter = painterResource(R.drawable.img_welcome),
            contentDescription = "Jewellery Appliances",
            modifier = Modifier
                .height(200.dp)
                .padding(16.dp)
        )

        // Description
        Text(
            text = "Welcome to the app! Your go-to place for the best manicure appliances. Explore top-quality tools to perfect your nail care routine!",
            fontSize = 16.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )

        // Shop Online Button
        Button(
            onClick = { navController.navigate("add_product") }, // Navigates to Add Product screen
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF6A1B9A)), // Deep purple button
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "SHOP ONLINE", fontSize = 18.sp, color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(navController = rememberNavController())
}