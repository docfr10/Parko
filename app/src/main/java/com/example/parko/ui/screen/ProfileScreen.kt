package com.example.parko.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview(showBackground = true)
fun ProfileScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "This is profile screen")
    }
}